package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.SettlementDocumentControllerAssemlber;
import com.hgys.iptv.controller.vm.SettlementDocumentCPListExcelVM;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.CpSettlementMoney;
import com.hgys.iptv.model.QAccountSettlement;
import com.hgys.iptv.model.QCpSettlementMoney;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AccountSettlementRepository;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import com.hgys.iptv.repository.SettlementDocumentRepository;
import com.hgys.iptv.service.SettlementDocumentService;
import com.hgys.iptv.util.ResultVOUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

           /** 1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回;7:已结算*/
           Predicate condition = builder.equal(root.get("status"), 7);
           predicates.add(condition);

           if (!predicates.isEmpty()){
               return builder.and(predicates.toArray(new Predicate[0]));
           }
           return builder.conjunction();
        }),pageable).map(settlementDocumentControllerAssemlber :: getListVM);
    }

    @Override
    public ResultVO<?> findByIdQueryCpList(Integer id) {
        Optional<AccountSettlement> byId = accountSettlementRepository.findById(id);
        List<SettlementDocumentCPListExcelVM> vms = new ArrayList<>();
        if (byId.isPresent()){
            AccountSettlement accountSettlement = byId.get();
            List<CpSettlementMoney> list = cpSettlementMoneyRepository.findByMasterCode(accountSettlement.getCode());
            for (CpSettlementMoney cp : list){
                SettlementDocumentCPListExcelVM vm = new SettlementDocumentCPListExcelVM();
                BeanUtils.copyProperties(cp,vm);
                vm.setMasterId(id);
                vm.setSetStartTime(accountSettlement.getSetStartTime());
                vm.setSetEndTime(accountSettlement.getSetEndTime());
                vm.setStatus(accountSettlement.getStatus());
                vms.add(vm);
            }
        }else {
            return ResultVOUtil.error("1","未查询到分账结算信息");
        }
        return ResultVOUtil.success(vms);
    }

    @Override
    public ResultVO<?> settlementDocumentQueryCpMySelfList(String cpCode) {
        QAccountSettlement qAccountSettlement = QAccountSettlement.accountSettlement;
        QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;
        List<SettlementDocumentCPListExcelVM> masterId = jpaQueryFactory.select(Projections.bean(
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
                .where(qCpSettlementMoney.cpcode.eq(cpCode)).fetch();
        return ResultVOUtil.success(masterId);
    }
}
