package com.hgys.iptv.service.impl;

import com.hgys.iptv.common.AbstractBaseServiceImpl;
import com.hgys.iptv.controller.assemlber.CpProductListAssemlber;
import com.hgys.iptv.controller.vm.CpAddVM;
import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.controller.vm.CpVM;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.CpService;
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
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Auther: wangz
 * @Date: 2019/5/6 14:44
 * @Description:
 */
@Service
public class CpServiceImpl extends AbstractBaseServiceImpl implements CpService {
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
    CpProductListAssemlber assemlber;


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
            //校验必填字段是否填写
            String[] cols = {vm.getName(), vm.getStatus().toString(),vm.getContactNm()};
            if (!Validator.validEmptyPass(cols))//必填字段不为空则插入
                return ResultVOUtil.error("1", "有必填字段未填写！");

            //1.存cp主表并返回
            Cp cp = new Cp();
            BeanUtils.copyProperties(vm, cp);
            cp.setRegisTime(new Timestamp(System.currentTimeMillis()));//注册时间
            cp.setModifyTime(new Timestamp(System.currentTimeMillis()));//最后修改时间
            cp.setCode(CodeUtil.getOnlyCode("SDS", 5));//cp编码
            cp.setIsdelete(0);//删除状态
            Cp cp_ = cpRepository.save(cp);
            //------------------------处理关系
            List<String> pidLists = Arrays.asList(StringUtils.split(vm.getPids(), ","));
            //2.插cp-product中间表
            List<CpProduct> cpProds =new ArrayList<>();
            pidLists.forEach(pid->{
                CpProduct cpProduct = new CpProduct();
                cpProduct.setCpid(cp_.getId());
                cpProduct.setPid(Integer.parseInt(pid));
                cpProds.add(cpProduct);
            });
            cpProductRepository.saveAll(cpProds);
            //------------------------------------------
            //3.插cp-business中间表
            List<CpBusiness> cpBizs =new ArrayList<>();
            List<String> bidLists = Arrays.asList(StringUtils.split(vm.getBids(), ","));
            bidLists.forEach(bid->{
                CpBusiness cpBusiness = new CpBusiness();
                cpBusiness.setBid(Integer.parseInt(bid));
                cpBusiness.setCpid(cp_.getId());
                cpBizs.add(cpBusiness);
            });
            cpBusinessRepository.saveAll(cpBizs);
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
//            //验证名称是否已经存在
//            if (StringUtils.isNotBlank(cp.getName())){
//                Product byName = productRepository.findByName(cp.getName().trim());
//                if (null != byName && !byName.getId().equals(cp.getId()) ){
//                    ResultVOUtil.error("1","名称已经存在");
//                }
//            }
            //注销==4
            if(cp.getStatus()!=null && cp.getStatus()==4){
                cp.setCancelTime(new Timestamp(System.currentTimeMillis()));
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
     * cp批量逻辑删除--但要物理删除中间表的关系mapping
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> batchLogicDelete(String ids){
        List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
        Set<Integer> pidSets = new HashSet<>();
        idLists.forEach(cpid->{
            pidSets.add(Integer.parseInt(cpid));
        });
        for (Integer cpid : pidSets){
            cpRepository.logicDelete(cpid);
            //删除cp_product关系映射
            cpProductRepository.deleteAllByCpid(cpid);
           //删除cp_business关系映射
            cpBusinessRepository.deleteAllByCpid(cpid);
        }
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
        //查关联的产品--先按cpid查cp_product中间表查出pid集合-->按pid去 findAllById
        Set<Integer> pidSet = cpProductRepository.findAllPid(id);
        List<Product> pList = productRepository.findAllById(pidSet);
        cpVM.setPList(pList);
        //查关联的业务表
        Set<Integer> bidSet = cpBusinessRepository.findAllBid(id);
        List<Business> bList = businessRepository.findAllById(bidSet);
        cpVM.setBList(bList);
        if(cp!=null)
            return ResultVOUtil.success(cpVM);
        return ResultVOUtil.error("1","所查询的cp不存在!");
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
     * cp列表查询--不查关联关系--对其他业务提供该接口
     * @return
     */
    @Override
    public ResultVO<?> findAll() {
        Map<String,Object> vm = new HashMap<>();
        vm.put("isdelete",0);
        List<?> cps =findByCriteria(Cp.class,vm);
        if(cps!=null&&cps.size()>0)
            return ResultVOUtil.success(cps);
        return ResultVOUtil.error("1","所查询的cp列表不存在!");
    }

    @Override
    public Page<CpControllerListVM> findByConditions(String name, String code, String cpAbbr, Integer status, Pageable pageable) {
        return cpRepository.findAll(((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)){
                Predicate condition = builder.like(root.get("name"), "%"+name+"%");
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(code)){
                Predicate condition = builder.like(root.get("code"), "%"+code+"%");
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(cpAbbr)){
                Predicate condition = builder.like(root.get("cpAbbr"), "%"+cpAbbr+"%");
                predicates.add(condition);
            }

            if (status!=null && status>0){
                Predicate condition = builder.equal(root.get("status"), status);
                predicates.add(condition);
            }
            Predicate condition = builder.equal(root.get("isdelete"), 0);
            predicates.add(condition);
            if (!predicates.isEmpty()){
                return builder.and(predicates.toArray(new Predicate[0]));
            }
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
}
