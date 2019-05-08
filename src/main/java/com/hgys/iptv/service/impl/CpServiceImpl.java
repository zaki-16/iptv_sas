package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.CpControllerAssemlber;
import com.hgys.iptv.controller.vm.CpSaveAndUpdateVM;
import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.CpRepository;
import com.hgys.iptv.service.CpService;
import com.hgys.iptv.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
 * @Date: 2019/5/6 14:44
 * @Description:
 */
@Service
public class CpServiceImpl implements CpService {
    @Autowired
    private CpRepository cpRepository;
    @Autowired
    CpControllerAssemlber assemlber;

    /**
     * cp 新增
     * @param vm
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> save(CpSaveAndUpdateVM vm){
        //校验cp名称是否已经存在
        Cp byName = cpRepository.findByName(vm.getName());
        if (null != byName){
            return ResultVOUtil.error("1",byName.getName() + "名称已经存在");
        }
        String[] cols = {vm.getName(),vm.getStatus().toString()};
        if(!Validator.validEmptyPass(cols))//必填字段不为空则插入
            return ResultVOUtil.error("1","有必填字段未填写！");
        Cp cp = new Cp();
        /**
         * 所属产品（必填，列表展示）、
         */
        cp.setName(vm.getName());
        cp.setCpAbbr(vm.getCpAbbr());
        cp.setStatus(vm.getStatus());
        cp.setContactNm(vm.getContactNm());
        cp.setContactTel(vm.getContactTel());
        cp.setContactMail(vm.getContactMail());
        cp.setNote(vm.getNote());
        cp.setRegisTime(new Timestamp(System.currentTimeMillis()));//注册时间
        cp.setModifyTime(new Timestamp(System.currentTimeMillis()));//最后修改时间
        cp.setCode(CodeUtil.getOnlyCode("SDS",5));//cp编码
        cp.setIsdelete(0);//删除状态

        Cp cp_add = cpRepository.save(cp);
        if(cp_add !=null)
            return ResultVOUtil.success(Boolean.TRUE);
        return ResultVOUtil.error("1","新增失败！");
    }


    /**
     * cp 修改
     * @param vo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> update(Cp vo) {
        if (null == vo.getId()) {
            ResultVOUtil.error("1", "结算维度主键不能为空");
        }
        try {
            //验证名称是否已经存在
            if (StringUtils.isNotBlank(vo.getName())) {
                Cp byName = cpRepository.findByName(vo.getName().trim());
                if (null != byName && !byName.getId().equals(vo.getId())) {
                    ResultVOUtil.error("1", "结算维度名称已经存在");
                }
            }
            Cp byId = cpRepository.findById(vo.getId()).orElseThrow(() -> new IllegalArgumentException("为查询到ID为:" + vo.getId() + "cp信息"));
            vo.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(byId, vo);
            cpRepository.saveAndFlush(vo);
        } catch (Exception e) {
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
    public ResultVO<?> batchLogicDelete(String ids){
        List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
        for (String s : idLists)
            cpRepository.logicDelete(Integer.parseInt(s));
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * cp单查询--根据id
     * @param id
     * @return
     */
    @Override
    public ResultVO<?> findById(Integer id) {
        Cp cp = cpRepository.findById(id).get();
        if(cp!=null)
            return ResultVOUtil.success(cp);
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
    public Page<CpControllerListVM> findByConditions(String name, String code, String cpAbbr, String status, Pageable pageable) {
        return cpRepository.findAll(((Root<Cp> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)){
                Predicate condition = builder.equal(root.get("name").as(String.class), "%"+name+"%");
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(code)){
                Predicate condition = builder.equal(root.get("code").as(String.class), code);
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(cpAbbr)){
                Predicate condition = builder.equal(root.get("cpAbbr").as(String.class), "%"+cpAbbr+"%");
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(status)){
                Predicate condition = builder.equal(root.get("status").as(String.class), status);
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
