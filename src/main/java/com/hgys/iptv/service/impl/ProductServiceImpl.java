package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.ProductBusinessListAssemlber;
import com.hgys.iptv.controller.vm.ProductAddVM;
import com.hgys.iptv.controller.vm.ProductControllerListVM;
import com.hgys.iptv.controller.vm.ProductListVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.ProductBusiness;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.BusinessRepository;
import com.hgys.iptv.repository.CpProductRepository;
import com.hgys.iptv.repository.ProductBusinessRepository;
import com.hgys.iptv.repository.ProductRepository;
import com.hgys.iptv.service.ProductService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import com.hgys.iptv.util.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Auther: wangz
 * @Date: 2019/5/5 17:15
 * @Description:/
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ProductBusinessListAssemlber assemlber;
    @Autowired
    CpProductRepository cpProductRepository;
    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    ProductBusinessRepository productBusinessRepository;
    @Autowired
    private EntityManager entityManager;
    /**
     * 新增
     * @param vm
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> save(ProductAddVM vm){
        //校验名称是否已经存在
        Product byName = productRepository.findByName(vm.getName());
        if (null != byName){
            return ResultVOUtil.error("1",byName + "名称已经存在");
        }
        String[] cols = {vm.getName(),vm.getPrice().toString(),vm.getStatus().toString()};
        if(!Validator.validEmptyPass(cols))//必填字段不为空则插入
            return ResultVOUtil.error("1","有必填字段未填写！");
        Product prod = new Product();
        BeanUtils.copyProperties(vm,prod);

        prod.setModifyTime(new Timestamp(System.currentTimeMillis()));//最后修改时间
        prod.setInputTime(new Timestamp(System.currentTimeMillis()));//录入时间
        prod.setCode(CodeUtil.getOnlyCode("SDS",5));//产品编码
        prod.setIsdelete(0);//删除状态
        //处理 product、business、product_business关系
        List<String> idLists  =Arrays.asList(StringUtils.split(vm.getIds(),","));
        Set<Integer> idSet = new HashSet();
        for(String s:idLists){
            idSet.add(Integer.parseInt(s));
        }
        List<Business> bussList =businessRepository.findAllById(idSet);
        //插产品表--立即返回该对象
        Product prod_add = productRepository.save(prod);
        //将所有中间表对象 收集 ->saveAll()
        List<ProductBusiness> pbs =new ArrayList<>();
        for(Business business:bussList){
            //中间表对象--维护双方主键
            ProductBusiness pb = new ProductBusiness();
            pb.setPid(prod_add.getId());
            pb.setBid(business.getId());
            pbs.add(pb);
        }
        //插业务表
        businessRepository.saveAll(bussList);
        //插中间表
        productBusinessRepository.saveAll(pbs);

        if(prod_add !=null)
            return ResultVOUtil.success(Boolean.TRUE);
        return ResultVOUtil.error("1","新增失败！");
    }

    /**
     * 修改
     * @param prod
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> update(Product prod) {
        if (null == prod.getId()){
            ResultVOUtil.error("1","主键不能为空");
        }
        try{
            //验证名称是否已经存在
            if (StringUtils.isNotBlank(prod.getName())){
                Product byName = productRepository.findByName(prod.getName().trim());
                if (null != byName && !byName.getId().equals(prod.getId()) ){
                    ResultVOUtil.error("1","名称已经存在");
                }
            }
            Product byId = productRepository.findById(prod.getId()).orElseThrow(()-> new IllegalArgumentException("为查询到ID为:" + prod.getId() + "产品信息"));
            prod.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(byId,prod);
            productRepository.saveAndFlush(prod);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }


    /**
     * 逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> logicDelete(Integer id){
        try {
            productRepository.logicDelete(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 批量逻辑删除
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> batchLogicDelete(String ids){
        List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
        for (String s : idLists)
            productRepository.logicDelete(Integer.parseInt(s));
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 单查询--根据pid查所有产品和关联的业务列表
     * @param id
     * @return
     */
    @Override
    public ResultVO<?> findById(Integer id) {
        Product prod = productRepository.findById(id).get();
        ProductListVM productListVM = new ProductListVM();
        BeanUtils.copyProperties(prod,productListVM);

        List <ProductBusiness> PB = findProductBusinessListBy(id);
        Set<Integer> bidSet = new HashSet<>();
        for(ProductBusiness productBusiness:PB){
            bidSet.add(productBusiness.getBid());
        }
        List<Business> bList = businessRepository.findAllById(bidSet);
        productListVM.setList(bList);
        if(prod!=null)
            return ResultVOUtil.success(productListVM);
        return ResultVOUtil.error("1","所查询的cp不存在!");
    }

    public List<ProductBusiness> findProductBusinessListBy(Integer pid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductBusiness> query = cb.createQuery(ProductBusiness.class);
        Root<ProductBusiness> cpRoot = query.from(ProductBusiness.class);
        query.select(cpRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(cpRoot.get("pid"), pid));
        //where
        query.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<ProductBusiness> typedQuery = entityManager.createQuery(query);
        List <ProductBusiness> content = typedQuery.getResultList();
        return content;
    }



    /**
     * 单查询--根据code
     * @param code
     * @return
     */
    @Override
    public ResultVO<?> findByCode(String code) {
        Product prod = productRepository.findByCode(code);
        if(prod!=null)
            return ResultVOUtil.success(prod);
        return ResultVOUtil.error("1","所查询的产品不存在!");
    }

    /**
     * 列表查询
     * @return
     */
    @Override
    public ResultVO<?> findAll() {
        List<Product> prods = productRepository.findAll();
        if(prods!=null&&prods.size()>0)
            return ResultVOUtil.success(prods);
        return ResultVOUtil.error("1","所查询的产品列表不存在!");
    }

    @Override
    public Page<ProductControllerListVM> findByConditions(String name,String code, Integer status, Pageable pageable) {
        return productRepository.findAll(((root, query,builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)){
                Predicate condition = builder.equal(root.get("name").as(String.class), "%"+name+"%");
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(code)){
                Predicate condition = builder.equal(root.get("code").as(String.class), "%"+code+"%");
                predicates.add(condition);
            }
            if (status!=null && status>0){
                Predicate condition = builder.equal(root.get("status").as(Integer.class), status);
                predicates.add(condition);
            }
            Predicate condition = builder.equal(root.get("isdelete").as(Integer.class), 0);
            predicates.add(condition);
            if (!predicates.isEmpty()){
                return builder.and(predicates.toArray(new Predicate[0]));
            }
            return builder.conjunction();
        }),pageable).map(assemlber::getListVM);
    }

}
