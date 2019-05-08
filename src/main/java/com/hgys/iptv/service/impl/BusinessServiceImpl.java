package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.BusinessControllerAssemlber;
import com.hgys.iptv.controller.assemlber.CpControllerAssemlber;
import com.hgys.iptv.controller.vm.BusinessControllerListVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.BusinessRepository;
import com.hgys.iptv.repository.CpRepository;
import com.hgys.iptv.repository.ProductRepository;
import com.hgys.iptv.service.BusinessService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: wangz
 * @Date: 2019/5/5 18:05
 * @Description:
 */
@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    BusinessControllerAssemlber assemlber;
    /**
     * 新增
     * @param business
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> save(Business business){
        //校验名称是否已经存在
        Business byName = businessRepository.findByName(business.getName());
        if (null != byName){
            return ResultVOUtil.error("1",byName + "名称已经存在");
        }
        //必填字段：业务名臣，业务类型，结算类型，状态
        String[] cols = {business.getName(),business.getBizType().toString(),
                business.getSettleType().toString(),business.getStatus().toString()};
        if(!Validator.validEmptyPass(cols))//必填字段不为空则插入
            return ResultVOUtil.error("1","有必填字段未填写！");
        business.setModifyTime(new Timestamp(System.currentTimeMillis()));//最后修改时间
        business.setInputTime(new Timestamp(System.currentTimeMillis()));//注册时间
        business.setCode(CodeUtil.getOnlyCode("SDS",5));//cp编码
        business.setIsdelete(0);//删除状态
        Business buss_add = businessRepository.save(business);
        if(buss_add !=null)
            return ResultVOUtil.success(Boolean.TRUE);
        return ResultVOUtil.error("1","新增失败！");
    }

    /**
     * 修改
     * @param business
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> update(Business business) {
        Business buss_up = businessRepository.save(business);
        if(buss_up !=null)
            return ResultVOUtil.success(Boolean.TRUE);
        return ResultVOUtil.error("1","新增失败！");//jpa会调用isNew()方法判定对象是否已存在
    }

    /**
     * 逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> logicDelete(Integer id){
        try {
            businessRepository.logicDelete(id);
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
    public ResultVO<?> batchLogicDelete(String ids){
        List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
        for (String s : idLists)
            businessRepository.logicDelete(Integer.parseInt(s));
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 单查询--根据id
     * @param id
     * @return
     */
    @Override
    public ResultVO<?> findById(Integer id) {
        Business business = businessRepository.findById(id).get();
        if(business!=null)
            return ResultVOUtil.success(business);
        return ResultVOUtil.error("1","所查询的cp不存在!");
    }

    /**
     * 单查询--根据code
     * @param code
     * @return
     */
    @Override
    public ResultVO<?> findByCode(String code) {
        Business business = businessRepository.findByCode(code);
        if(business!=null)
            return ResultVOUtil.success(business);
        return ResultVOUtil.error("1","所查询的产品不存在!");
    }

    /**
     * 列表查询
     * @return
     */
    @Override
    public ResultVO<?> findAll() {
        List<Business> buss = businessRepository.findAll();
        if(buss!=null&&buss.size()>0)
            return ResultVOUtil.success(buss);
        return ResultVOUtil.error("1","所查询的产品列表不存在!");
    }

    @Override
    public Page<BusinessControllerListVM> findByConditions(String name, String code, String bizType, String settleType, String status, Pageable pageable) {
        return businessRepository.findAll(((root, query,builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)){
                Predicate condition = builder.equal(root.get("name").as(String.class), "%"+name+"%");
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(code)){
                Predicate condition = builder.equal(root.get("code").as(String.class), code);
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(bizType)){
                Predicate condition = builder.equal(root.get("bizType").as(String.class), bizType);
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(status)){
                Predicate condition = builder.equal(root.get("status").as(String.class), status);
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(status)){
                Predicate condition = builder.equal(root.get("settleType").as(String.class), settleType);
                predicates.add(condition);
            }
            Predicate condition = builder.equal(root.get("isdelete").as(String.class), 0);
            predicates.add(condition);
            if (!predicates.isEmpty()){
                return builder.and(predicates.toArray(new Predicate[0]));
            }
//            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            return builder.conjunction();
        }),pageable).map(assemlber::getListVM);
    }

}
