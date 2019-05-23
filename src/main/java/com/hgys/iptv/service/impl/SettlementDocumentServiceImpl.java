package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.SettlementDocumentControllerAssemlber;
import com.hgys.iptv.controller.vm.SettlementDocumentCPListExcelVM;
import com.hgys.iptv.controller.vm.SettlementDocumentCPListVM;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.QAccountSettlement;
import com.hgys.iptv.model.QCpSettlementMoney;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AccountSettlementRepository;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import com.hgys.iptv.repository.SettlementDocumentRepository;
import com.hgys.iptv.service.SettlementDocumentService;
import com.hgys.iptv.util.ResultVOUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SettlementDocumentServiceImpl implements SettlementDocumentService {

    @Autowired
    private SettlementDocumentRepository settlementDocumentRepository;

    @Autowired
    private SettlementDocumentControllerAssemlber settlementDocumentControllerAssemlber;

    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;

    @Autowired
    private AccountSettlementRepository accountSettlementRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    /**
     * 查询结算文档列表信息
     * @param name
     * @param code
     * @param pageable
     * @return
     */
    @Override
    public Page<SettlementDocumentQueryListVM> findByConditions(String name, String code, Pageable pageable) {
       return settlementDocumentRepository.findAll(((root, query, builder) -> {
           List<Predicate> predicates = new ArrayList<>();
           if (StringUtils.isNotBlank(name)){
               Predicate condition = builder.like(root.get("name"), "%"+name+"%");
               predicates.add(condition);
           }
           if (StringUtils.isNotBlank(code)){
               Predicate condition = builder.like(root.get("code"), "%"+code+"%");
               predicates.add(condition);
           }

           /** 1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回*/
           Predicate condition = builder.notEqual(root.get("status"), 1);
           predicates.add(condition);

           if (!predicates.isEmpty()){
               return builder.and(predicates.toArray(new Predicate[0]));
           }
           return builder.conjunction();
        }),pageable).map(settlementDocumentControllerAssemlber :: getListVM);
    }

    @Override
    public Page<SettlementDocumentCPListExcelVM> documentQueryHistoryCpMySelfList(Integer masterId,String cpCode,String pageNum, String pageSize) {
        QAccountSettlement qAccountSettlement = QAccountSettlement.accountSettlement;
        QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;
        //查询分账结算信息
        AccountSettlement accountSettlement = jpaQueryFactory.selectFrom(qAccountSettlement).where(qAccountSettlement.id.eq(masterId)).fetchOne();
        QueryResults<SettlementDocumentCPListExcelVM> fetch = jpaQueryFactory.select(Projections.bean(
                SettlementDocumentCPListExcelVM.class,
                qAccountSettlement.id.as("masterId"),
                qAccountSettlement.setStartTime,
                qAccountSettlement.setEndTime,
                qAccountSettlement.status,
                qCpSettlementMoney.id,
                qCpSettlementMoney.masterCode,
                qCpSettlementMoney.masterName,
                qCpSettlementMoney.cpcode,
                qCpSettlementMoney.cpname,
                qCpSettlementMoney.productCode,
                qCpSettlementMoney.productName,
                qCpSettlementMoney.businessCode,
                qCpSettlementMoney.businessName,
                qCpSettlementMoney.settlementMoney,
                qCpSettlementMoney.createTime
        )).from(qCpSettlementMoney)
                .innerJoin(qAccountSettlement).on(qCpSettlementMoney.masterCode.eq(qAccountSettlement.code))
                .where(qCpSettlementMoney.cpcode.eq(cpCode))
                .where(qAccountSettlement.setStartTime.lt(accountSettlement.getSetStartTime()))
                .orderBy(qAccountSettlement.setStartTime.desc())
                .offset(Integer.parseInt(pageNum) - 1).limit(Integer.parseInt(pageSize)).fetchResults();

        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) - 1 ,Integer.parseInt(pageSize));
        Page<SettlementDocumentCPListExcelVM> pageImpianto = new PageImpl<>(fetch.getResults(), pageable, fetch.getTotal());
        return pageImpianto;
    }

    @Override
    public Page<SettlementDocumentQueryListVM> documentHistoryQueryCpList(Integer masterId, String pageNum, String pageSize) {
        Optional<AccountSettlement> byId = accountSettlementRepository.findById(masterId);
        AccountSettlement accountSettlement = byId.get();
        //查询当前账期以前账期的数据
        QAccountSettlement qAccountSettlement = QAccountSettlement.accountSettlement;

        QueryResults<AccountSettlement> fetch = jpaQueryFactory.selectFrom(qAccountSettlement)
                .where(qAccountSettlement.setStartTime.lt(accountSettlement.getSetStartTime()))
                .orderBy(qAccountSettlement.setStartTime.desc())
                .offset(Integer.parseInt(pageNum) - 1).limit(Integer.parseInt(pageSize)).fetchResults();

        List<AccountSettlement> list =fetch.getResults();
        List<SettlementDocumentQueryListVM> vms = new ArrayList<>();
        for (AccountSettlement a : list){
            SettlementDocumentQueryListVM s = new SettlementDocumentQueryListVM();
            BeanUtils.copyProperties(accountSettlement,s);
            //查询该分账结算下所有的cp信息
            List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findByMasterCode(a.getCode());
            List<SettlementDocumentCPListVM> listVMS = new ArrayList<>();
            for (CpSettlementMoney cp : byMasterCode){
                SettlementDocumentCPListVM vm = new SettlementDocumentCPListVM();
                BeanUtils.copyProperties(cp,vm);
                listVMS.add(vm);
            }
            s.setCpList(listVMS);
            vms.add(s);
        }
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) - 1 ,Integer.parseInt(pageSize));
        Page<SettlementDocumentQueryListVM> pageImpianto = new PageImpl<>(vms, pageable, fetch.getTotal());
        return pageImpianto;
    }


    @Override
    public ResultVO<?> findByIdQueryCpList(Integer id) {
        //查询分账结算信息
        Optional<AccountSettlement> byId = accountSettlementRepository.findById(id);
        AccountSettlement accountSettlement = byId.get();
        SettlementDocumentQueryListVM s = new SettlementDocumentQueryListVM();
        BeanUtils.copyProperties(accountSettlement,s);
        //查询该分账结算下所有的cp信息
        List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findByMasterCode(accountSettlement.getCode());
        List<SettlementDocumentCPListVM> vms = new ArrayList<>();
        for (CpSettlementMoney cp : byMasterCode){
            SettlementDocumentCPListVM vm = new SettlementDocumentCPListVM();
            BeanUtils.copyProperties(cp,vm);
            vms.add(vm);
        }
        s.setCpList(vms);

        return ResultVOUtil.success(s);
    }

    @Override
    public ResultVO<?> settlementDocumentQueryCpMySelfList(Integer id) {
        QAccountSettlement qAccountSettlement = QAccountSettlement.accountSettlement;
        QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;
        SettlementDocumentCPListExcelVM vm = jpaQueryFactory.select(Projections.bean(
                SettlementDocumentCPListExcelVM.class,
                qAccountSettlement.id.as("masterId"),
                qAccountSettlement.setStartTime,
                qAccountSettlement.setEndTime,
                qAccountSettlement.status,
                qCpSettlementMoney.id,
                qCpSettlementMoney.masterCode,
                qCpSettlementMoney.masterName,
                qCpSettlementMoney.cpcode,
                qCpSettlementMoney.cpname,
                qCpSettlementMoney.productCode,
                qCpSettlementMoney.productName,
                qCpSettlementMoney.businessCode,
                qCpSettlementMoney.businessName,
                qCpSettlementMoney.settlementMoney,
                qCpSettlementMoney.createTime
        )).from(qCpSettlementMoney)
                .innerJoin(qAccountSettlement).on(qCpSettlementMoney.masterCode.eq(qAccountSettlement.code))
                .where(qCpSettlementMoney.id.eq(id)).fetchOne();
        return ResultVOUtil.success(vm);
    }

    @Override
    public SettlementDocumentCPListExcelVM settlementCpExcel(Integer id) {
        QAccountSettlement qAccountSettlement = QAccountSettlement.accountSettlement;
        QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;

        SettlementDocumentCPListExcelVM vm = jpaQueryFactory.select(Projections.bean(
                SettlementDocumentCPListExcelVM.class,
                qAccountSettlement.id.as("masterId"),
                qAccountSettlement.setStartTime,
                qAccountSettlement.setEndTime,
                qAccountSettlement.status,
                qAccountSettlement.set_type.as("type"),
                qCpSettlementMoney.id,
                qCpSettlementMoney.masterCode,
                qCpSettlementMoney.masterName,
                qCpSettlementMoney.cpcode,
                qCpSettlementMoney.cpname,
                qCpSettlementMoney.productCode,
                qCpSettlementMoney.productName,
                qCpSettlementMoney.businessCode,
                qCpSettlementMoney.businessName,
                qCpSettlementMoney.settlementMoney,
                qCpSettlementMoney.createTime
        )).from(qCpSettlementMoney)
                .innerJoin(qAccountSettlement).on(qCpSettlementMoney.masterCode.eq(qAccountSettlement.code))
                .where(qCpSettlementMoney.id.eq(id)).fetchOne();
        return vm;
    }
}
