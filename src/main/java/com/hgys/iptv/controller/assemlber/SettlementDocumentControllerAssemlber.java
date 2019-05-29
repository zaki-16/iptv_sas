package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.SettlementDocumentCPListVM;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.CpSettlementMoney;
import com.hgys.iptv.model.QCpSettlementMoney;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SettlementDocumentControllerAssemlber {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public SettlementDocumentQueryListVM getListVM(AccountSettlement accountSettlement){
        SettlementDocumentQueryListVM s = new SettlementDocumentQueryListVM();
        BeanUtils.copyProperties(accountSettlement,s);
        //查询该分账结算下所有的cp信息
        QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;
        List<CpSettlementMoney> fetch = jpaQueryFactory.selectFrom(qCpSettlementMoney)
                .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                .groupBy(qCpSettlementMoney.cpcode).fetch();


        List<SettlementDocumentCPListVM> vms = new ArrayList<>();
        for (CpSettlementMoney cp : fetch){
            SettlementDocumentCPListVM vm = new SettlementDocumentCPListVM();
            BeanUtils.copyProperties(cp,vm);
            vms.add(vm);
        }
        s.setCpList(vms);
        return s;
    }
}
