package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionControllerListVM;
import com.hgys.iptv.model.SettlementCombinatorialDimensionFrom;
import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionFromRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SettlementCombinatorialDimensionControllerAssemlber {

    @Autowired
    private SettlementCombinatorialDimensionFromRepository settlementCombinatorialDimensionFromRepository;

    public SettlementCombinatorialDimensionControllerListVM getListVM(SettlementCombinatorialDimensionMaster od){
        SettlementCombinatorialDimensionControllerListVM vm = new SettlementCombinatorialDimensionControllerListVM();
        BeanUtils.copyProperties(od,vm);
        List<SettlementCombinatorialDimensionFrom> byMasterCode = settlementCombinatorialDimensionFromRepository.findByMasterCode(od.getCode().trim());

        List<SettlementCombinatorialDimensionControllerListVM.SettlementDimension> list = new ArrayList<>();
        for (SettlementCombinatorialDimensionFrom f : byMasterCode){
            SettlementCombinatorialDimensionControllerListVM.SettlementDimension s = new SettlementCombinatorialDimensionControllerListVM.SettlementDimension();
            BeanUtils.copyProperties(f,s);
            list.add(s);
        }
        vm.setList(list);
        return vm;
    }

}
