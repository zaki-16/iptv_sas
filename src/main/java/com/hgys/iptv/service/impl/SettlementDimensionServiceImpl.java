package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.SettlementDimensionControllerAssemlber;
import com.hgys.iptv.controller.vm.SettlementDimensionAddVM;
import com.hgys.iptv.controller.vm.SettlementDimensionControllerListVM;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.QSettlementDimension;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.SettlementDimensionRepository;
import com.hgys.iptv.service.SettlementDimensionService;
import com.hgys.iptv.util.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SettlementDimensionServiceImpl implements SettlementDimensionService {
    @Autowired
    private SettlementDimensionRepository settlementDimensionRepository;

    @Autowired
    private SettlementDimensionControllerAssemlber settlementDimensionControllAssemlber;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    @Override
    public ResultVO<?> insterSettlementDimension(SettlementDimensionAddVM vm) {

        //校验名称是否已经存在
        SettlementDimension byName = settlementDimensionRepository.findByName(vm.getName().trim());
        if (null != byName && byName.getIsdelete().equals(0)){
            return ResultVOUtil.error("1",vm.getName() + "名称已经存在");
        }
        SettlementDimension vo = new SettlementDimension();
        vo.setCode(CodeUtil.getOnlyCode("SDS",5));
        vo.setInputTime(new Timestamp(System.currentTimeMillis()));
        vo.setIsdelete(0);
        vo.setName(vm.getName());
        vo.setRemarks(vm.getRemarks());
        vo.setStatus(Integer.parseInt(vm.getStatus()));
        settlementDimensionRepository.save(vo);

        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public Optional<SettlementDimension> findByCode(String code) {
        return settlementDimensionRepository.findByCode(code);
    }

    @Override
    public Optional<SettlementDimension> findById(String id) {
        return settlementDimensionRepository.findById(Integer.parseInt(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> batchLogicDelete(String ids) {
        try{
            List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists){
                settlementDimensionRepository.batchLogicDelete(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public ResultVO<?> updateSettlementDimension(SettlementDimension vo) {
        if (null == vo.getId()){
            ResultVOUtil.error("1","结算维度主键不能为空");
        }

        try{
            //验证名称是否已经存在
            if (StringUtils.isNotBlank(vo.getName())){
                SettlementDimension byName = settlementDimensionRepository.findByName(vo.getName().trim());
                if (null != byName && !byName.getId().equals(vo.getId()) ){
                    ResultVOUtil.error("1","结算维度名称已经存在");
                }
            }
            SettlementDimension byId = settlementDimensionRepository.findById(vo.getId()).orElseThrow(()-> new IllegalArgumentException("为查询到ID为:" + vo.getId() + "结算维度信息"));
            vo.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(byId,vo);
            settlementDimensionRepository.saveAndFlush(vo);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public Page<SettlementDimensionControllerListVM> findByConditions(String name, String code, String status, Pageable pageable) {

        return settlementDimensionRepository.findAll(((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)){
                Predicate condition = builder.like(root.get("name"), "%"+name+"%");
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(code)){
                Predicate condition = builder.like(root.get("code"), "%"+code+"%");
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(status)){
                Predicate condition = builder.equal(root.get("status"), Integer.parseInt(status));
                predicates.add(condition);
            }

            Predicate condition = builder.equal(root.get("isdelete"), 0);
            predicates.add(condition);

            if (!predicates.isEmpty()){
                return builder.and(predicates.toArray(new Predicate[0]));
            }
            return builder.conjunction();
        }),pageable).map(settlementDimensionControllAssemlber::getListVM);
    }

    @Override
    public SettlementDimension save(SettlementDimension settlementDimension) {
        return settlementDimensionRepository.save(settlementDimension);
    }

    @Override
    public List<SettlementDimension> findAll() {
        return settlementDimensionRepository.findByIsdelete(0);
    }


    @Override
    public Page<SettlementDimension> test(){
        QSettlementDimension dimension = QSettlementDimension.settlementDimension;
        QueryResults<SettlementDimension> fetch = jpaQueryFactory.select(
                Projections.bean(
                        SettlementDimension.class,
                        dimension.code,
                        dimension.id,
                        dimension.name
                )
        ).from(dimension).offset(0).limit(10).fetchResults();
        Pageable pageable = PageRequest.of(0 ,10);
        Page<SettlementDimension> pageImpianto = new PageImpl<>(fetch.getResults(), pageable, fetch.getTotal());
        return pageImpianto;
    }
}
