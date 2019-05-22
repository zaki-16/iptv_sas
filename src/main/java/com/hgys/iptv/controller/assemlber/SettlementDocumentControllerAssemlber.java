package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.SettlementDocumentCPListVM;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.CpSettlementMoney;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SettlementDocumentControllerAssemlber {
    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;

    public SettlementDocumentQueryListVM getListVM(AccountSettlement accountSettlement){
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
        return s;
    }
}
