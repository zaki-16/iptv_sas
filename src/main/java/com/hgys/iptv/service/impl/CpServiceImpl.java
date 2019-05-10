package com.hgys.iptv.service.impl;

import com.hgys.iptv.common.AbstractBaseRepositoryImpl;
import com.hgys.iptv.controller.assemlber.CpControllerAssemlber;
import com.hgys.iptv.controller.assemlber.CpProductListAssemlber;
import com.hgys.iptv.controller.vm.CpAddVM;
import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.controller.vm.CpVM;
import com.hgys.iptv.controller.vm.ProductListVM;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.CpProductRepository;
import com.hgys.iptv.repository.CpRepository;
import com.hgys.iptv.repository.ProductRepository;
import com.hgys.iptv.service.CpService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import com.hgys.iptv.util.Validator;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
 * @Date: 2019/5/6 14:44
 * @Description:
 */
@Service
public class CpServiceImpl extends AbstractBaseRepositoryImpl implements CpService {
    @Autowired
    private CpRepository cpRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CpProductRepository cpProductRepository;
    @Autowired
    CpProductListAssemlber assemlber;

    @Autowired
    private EntityManager entityManager;

    private static Map<String,List <CpProduct>> CpProductCathe_ = new HashMap<>();
    private static Map<String,List <Cp>> CpCathe_ = new HashMap<>();
    static{

        CpProductCathe_.clear();

        CpCathe_.clear();
    }

    /**
     * cp 新增-插cp，product，cp_product表
     * @param vm
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> save(CpAddVM vm){
        try {
            //校验cp名称是否已经存在
            Cp byName = cpRepository.findByName(vm.getName());
            if (null != byName) {
                return ResultVOUtil.error("1", byName.getName() + "名称已经存在");
            }
            String[] cols = {vm.getName(), vm.getStatus().toString()};
            if (!Validator.validEmptyPass(cols))//必填字段不为空则插入
                return ResultVOUtil.error("1", "有必填字段未填写！");
            Cp cp = new Cp();
            BeanUtils.copyProperties(vm, cp);
            cp.setRegisTime(new Timestamp(System.currentTimeMillis()));//注册时间
            cp.setModifyTime(new Timestamp(System.currentTimeMillis()));//最后修改时间
            cp.setCode(CodeUtil.getOnlyCode("SDS", 5));//cp编码
            cp.setIsdelete(0);//删除状态
            //处理关系
//        cpid对应的产品列表
            List<String> idLists = Arrays.asList(StringUtils.split(vm.getIds(), ","));
            Set<Integer> idSet = new HashSet();
            for(String s:idLists){
                idSet.add(Integer.parseInt(s));
            }

//            Iterator<Integer> it =idset.iterator();
//            List <Product> prods= new ArrayList<>(idLists.size());
//            while(it.hasNext()){
//                Product product = productRepository.findById(it.next()).get();
//                //关联cp到Product
//                prods.add(product);
//                cp.getProductList().add(product);
//            }
            List<Product> prods =productRepository.findAllById(idSet);
            Cp cp_ = cpRepository.save(cp);
            List<CpProduct> cpProds =new ArrayList<>();
            for(Product product:prods){
                CpProduct cpProduct = new CpProduct();
                cpProduct.setCpid(cp_.getId());
                cpProduct.setPid(product.getId());
                cpProds.add(cpProduct);
            }
            productRepository.saveAll(prods);

            //插中间表
            cpProductRepository.saveAll(cpProds);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","新增失败！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }


    /**
     * cp 修改
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> update(Cp cp) {
        if (null == cp.getId()){
            ResultVOUtil.error("1","主键不能为空");
        }
        try{
            //验证名称是否已经存在
            if (StringUtils.isNotBlank(cp.getName())){
                Product byName = productRepository.findByName(cp.getName().trim());
                if (null != byName && !byName.getId().equals(cp.getId()) ){
                    ResultVOUtil.error("1","名称已经存在");
                }
            }
            Cp byId = cpRepository.findById(cp.getId()).orElseThrow(()-> new IllegalArgumentException("为查询到ID为:" + cp.getId() + "cp信息"));
            cp.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(byId,cp);
            cpRepository.saveAndFlush(cp);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }
    /**
     * cp删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> logicDelete(Integer id){
        try {
            cpRepository.logicDelete(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * cp批量逻辑删除
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> batchLogicDelete(String ids){
        List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
        for (String s : idLists)
            cpRepository.logicDelete(Integer.parseInt(s));
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * cp单查询--根据id返回单个实例
     * @param id
     * @return
     */
    @Override
    public ResultVO<?> findById(Integer id) {
        Cp cp = cpRepository.findById(id).get();
        CpVM cpVM = new CpVM();
        BeanUtils.copyProperties(cp,cpVM);

        List <CpProduct> PB = findCpProductListBy(id);
        Set<Integer> pidSet = new HashSet<>();
        for(CpProduct cpProduct:PB){
            pidSet.add(cpProduct.getPid());
        }
        List<Product> pList = productRepository.findAllById(pidSet);
        cpVM.setList(pList);
        if(cp!=null)
            return ResultVOUtil.success(cpVM);
        return ResultVOUtil.error("1","所查询的cp不存在!");
    }

    public List<CpProduct> findCpProductListBy(Integer pid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CpProduct> query = cb.createQuery(CpProduct.class);
        Root<CpProduct> cpRoot = query.from(CpProduct.class);
        query.select(cpRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(cpRoot.get("cpid"), pid));
        //where
        query.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<CpProduct> typedQuery = entityManager.createQuery(query);
        List <CpProduct> content = typedQuery.getResultList();
        return content;
    }


    public Page<Cp> findAllOfPage(CpVM vm){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cp> query = cb.createQuery(Cp.class);
        Root<Cp> cpRoot = query.from(Cp.class);
        query.select(cpRoot);
        List<Predicate> predicates = new ArrayList<>();

        //where
        query.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Cp> typedQuery = entityManager.createQuery(query);
        List <Cp> content = typedQuery.getResultList();
//        return content;
//        CriteriaQuery <Long> countQuery = cb.createQuery(Long.class);
//        countQuery.select(cb.count(countQuery.from(Product.class)));查数量
//        countQuery.where(predicates.toArray(new Predicate[]{}));
//        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();
        Page page =new PageImpl<Cp>(content, PageRequest.of(vm.getPageNum(), vm.getPageSize()),content.size());
        return page;
    }

    /**
     * cp单查询--根据code
     * @param code
     * @return
     */
    @Override
    public ResultVO<?> findByCode(String code) {
        Cp cp = cpRepository.findByCode(code);
        if(cp!=null)
            return ResultVOUtil.success(cp);
        return ResultVOUtil.error("1","所查询的cp不存在!");
    }

    /**
     * cp列表查询
     * @return
     */
    @Override
    public ResultVO<?> findAll() {
        List<Cp> cps = cpRepository.findAll();
        if(cps!=null&&cps.size()>0)
            return ResultVOUtil.success(cps);
        return ResultVOUtil.error("1","所查询的cp列表不存在!");
    }

    @Override
    public Page<CpControllerListVM> findByConditions(String name, String code, String cpAbbr, Integer status, Pageable pageable) {
        return cpRepository.findAll(((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)){
                Predicate condition = builder.equal(root.get("name").as(String.class), "%"+name+"%");
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(code)){
                Predicate condition = builder.equal(root.get("code").as(String.class), "%"+code+"%");
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(cpAbbr)){
                Predicate condition = builder.equal(root.get("cpAbbr").as(String.class), "%"+cpAbbr+"%");
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
//            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            return builder.conjunction();
        }),pageable).map(assemlber::getListVM);
    }

    @Override
    public ResultVO<?> findcplist() {
        List<Cp> cps =cpRepository.findcplist();
        if(cps!=null)
            return ResultVOUtil.success(cps);
        return ResultVOUtil.error("1","所查询的cp不存在!");
    }


    public static boolean isId(Integer id) {
        if (id == null || id == 0) {
            return false;
        }
        return true;
    }
    public List <Cp> findBy(CpVM request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cp> query = cb.createQuery(Cp.class);
        Root<Cp> cpRoot = query.from(Cp.class);
        query.select(cpRoot);
        List<Predicate> predicates = new ArrayList<>();
        if (isId(request.getId())) {
            predicates.add(cb.equal(cpRoot.get("id"), request.getId()));
        }
        //where
        query.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Cp> typedQuery = entityManager.createQuery(query);
        List <Cp> content = typedQuery.getResultList();
        return content;

    }
}
