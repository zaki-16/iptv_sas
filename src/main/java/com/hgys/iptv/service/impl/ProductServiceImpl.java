package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.ProductBusinessListAssemlber;
import com.hgys.iptv.controller.vm.ProductAddVM;
import com.hgys.iptv.controller.vm.ProductVM;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.ProductService;
import com.hgys.iptv.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Auther: wangz
 * @Date: 2019/5/5 17:15
 * @Description:/
 */
@Service
public class ProductServiceImpl extends AbstractBaseServiceImpl implements ProductService {
    @Autowired
    private CpRepository cpRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    CpProductRepository cpProductRepository;
    @Autowired
    CpBusinessRepository cpBusinessRepository;
    @Autowired
    ProductBusinessRepository productBusinessRepository;

    @Autowired
    ProductBusinessListAssemlber assemlber;
//    @Autowired
//    private Logger logger;
    //操作对象
    private static final String menuName = "产品管理";
    /**
     * 新增
     * @param vm
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> save(ProductAddVM vm){
        try {
            //        //校验名称是否已经存在
//        Product byName = productRepository.findByName(vm.getName());
//        if (null != byName){
//            return ResultVOUtil.error("1",byName + "名称已经存在");
//        }
            String[] cols = {vm.getName(),vm.getPrice(),vm.getStatus().toString()};
            if(!Validator.validEmptyPass(cols))//必填字段不为空则插入
                return ResultVOUtil.error("1","有必填字段未填写！");
            Product prod = new Product();
            BeanUtils.copyProperties(vm,prod);
            prod.setModifyTime(new Timestamp(System.currentTimeMillis()));//最后修改时间
            prod.setInputTime(new Timestamp(System.currentTimeMillis()));//录入时间
            prod.setCode(CodeUtil.getOnlyCode("PROD",5));//产品编码
            prod.setIsdelete(0);//删除状态
            //插产品表--立即返回该对象
            Product prod_add = productRepository.save(prod);
            //处理product关联的中间表的映射关系
            handleRelation(vm,prod_add.getId());

//            logger.log_add_success(menuName,"ProductServiceImpl.save");

        }catch (Exception e){
            e.printStackTrace();
//            logger.log_add_fail(menuName,"ProductServiceImpl.save");
            return ResultVOUtil.error("1","新增失败！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 处理product关联的中间表的映射关系
     * @param vm--维护数据来源
     * @param id--要维护的产品id
     */
    @Transactional(rollbackFor = Exception.class)
    protected void handleRelation(ProductAddVM vm,Integer id){
        try {
            //------------------------处理关系
            List<String> cpidLists = Arrays.asList(StringUtils.split(vm.getCpids(), ","));
            //2.插cp-product中间表
            if(cpidLists.size()>0){
                List<CpProduct> cpProds =new ArrayList<>();
                cpidLists.forEach(cpid->{
                    CpProduct cpProduct = new CpProduct();
                    cpProduct.setCpid(Integer.parseInt(cpid));
                    cpProduct.setPid(id);
                    cpProds.add(cpProduct);
                });
                cpProductRepository.saveAll(cpProds);
            }
            //------------------------------------------
            //3.插product-business中间表
            List<ProductBusiness> pbList =new ArrayList<>();
            List<String> bidLists = Arrays.asList(StringUtils.split(vm.getBids(), ","));
            if(bidLists.size()>0){
                bidLists.forEach(bid->{
                    ProductBusiness pb = new ProductBusiness();
                    pb.setPid(id);
                    pb.setBid(Integer.parseInt(bid));
                    pbList.add(pb);
                });
                productBusinessRepository.saveAll(pbList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 修改
     * @param vm
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> update(ProductAddVM vm) {
        if (null == vm.getId()){
            ResultVOUtil.error("1","主键不能为空");
        }
        try{
            Product prod = new Product();
            BeanUtils.copyProperties(vm,prod);
            //验证名称是否已经存在
//            if (StringUtils.isNotBlank(prod.getName())){
//                Product byName = productRepository.findByName(prod.getName().trim());
//                if (null != byName && !byName.getId().equals(prod.getId()) ){
//                    ResultVOUtil.error("1","名称已经存在");
//                }
//            }
            Product byId = productRepository.findById(vm.getId()).orElseThrow(()-> new IllegalArgumentException("为查询到ID为:" + prod.getId() + "产品信息"));
            prod.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(byId,prod);
            Product product_up = productRepository.saveAndFlush(prod);
            //先删除后插入
                productBusinessRepository.deleteAllByPid(prod.getId());
                cpProductRepository.deleteAllByPid(prod.getId());
            //处理product关联的中间表的映射关系
            handleRelation(vm,product_up.getId());

//            logger.log_up_success(menuName,"ProductServiceImpl.update");
        }catch (Exception e){
            e.printStackTrace();
//            logger.log_up_fail(menuName,"ProductServiceImpl.update");
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }


//    /**
//     * 逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public ResultVO<?> logicDelete(Integer id){
//        try {
//            productRepository.logicDelete(id);
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
//        }
//        return ResultVOUtil.success(Boolean.TRUE);
//    }

    /**
     * 批量逻辑删除
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> batchLogicDelete(String ids){
        try {
            List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
            if(idLists.size()>0){
                Set<Integer> idSets = new HashSet<>();
                idLists.forEach(cpid->{
                    idSets.add(Integer.parseInt(cpid));
                });
                for (Integer id : idSets){
                    productRepository.logicDelete(id);
                    //删除cp_product关系映射
                    cpProductRepository.deleteAllByPid(id);
                    //删除product_business关系映射
                    productBusinessRepository.deleteAllByPid(id);
                }

//                logger.log_rm_success(menuName,"ProductServiceImpl.batchLogicDelete");
            }
        }catch (Exception e){
//            logger.log_rm_fail(menuName,"ProductServiceImpl.batchLogicDelete");
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 单查询--根据pid查所有产品和关联的业务列表
     * @param id
     * @return
     */
    @Override
    public ResultVO<?> findById(Integer id) {
        try {
            Product prod = productRepository.findById(id).orElse(null);
            if(prod==null)
                return ResultVOUtil.error("1","所查产品不存在");
            ProductVM productListVM = new ProductVM();
            BeanUtils.copyProperties(prod,productListVM);
            //查关联cp
            Set<Integer> cpidSet = cpProductRepository.findAllCpid(id);
            List<Cp> cpList = cpRepository.findAllById(cpidSet);
            ArrayList<Cp> CPList = new ArrayList<>();
            cpList.forEach(cp->{
                if(cp.getIsdelete()==0&&(cp.getStatus()==1||cp.getStatus()==2))
                    CPList.add(cp);
            });
            productListVM.setCpList(CPList);
            //查关联业务：先查中间表->bidSet->findAll
            Set<Integer> bidSet = productBusinessRepository.findAllBid(id);
            List<Business> bList = businessRepository.findAllById(bidSet);

            ArrayList<Business> BList = new ArrayList<>();
            //筛除已停用、删除的产品
            bList.forEach(p->{
                if(p.getIsdelete()==0&&p.getStatus()==0)
                    BList.add(p);
            });
            productListVM.setbList(BList);
            return ResultVOUtil.success(productListVM);
        }catch (Exception e){
            return ResultVOUtil.error("1","所查产品不存在");
        }
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
     * 列表查询所有未停用未删除的产品
     * @return
     */
    @Override
    public ResultVO<?> findAll() {
        Map<String,Object> vm = new HashMap<>();
        vm.put("isdelete",0);
        vm.put("status",0);
        List<Product> prods =findByCriteria(Product.class,vm);
        if(prods!=null&&prods.size()>0)
            return ResultVOUtil.success(prods);
        return ResultVOUtil.error("1","所查询的产品列表不存在!");
    }

    @Override
    public Page<ProductVM> findByConditions(String name,String code, Integer status, Pageable pageable) {
        return productRepository.findAll(((root, query,builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)){
                Predicate condition = builder.like(root.get("name").as(String.class), "%"+name+"%");
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(code)){
                Predicate condition = builder.like(root.get("code").as(String.class), "%"+code+"%");
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
    @Override
    public ResultVO<?> findplist() {
        List<Product> cps =productRepository.findplist();
        if(cps!=null)
            return ResultVOUtil.success(cps);
        return ResultVOUtil.error("1","所查询的不存在!");
    }
}
