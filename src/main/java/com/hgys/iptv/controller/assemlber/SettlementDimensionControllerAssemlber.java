package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.SettlementDimensionControllerListVM;
import com.hgys.iptv.model.SettlementDimension;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class SettlementDimensionControllerAssemlber {



    public SettlementDimensionControllerListVM getListVM(SettlementDimension settlementDimension){
        SettlementDimensionControllerListVM settlementDimensionControllListVM = new SettlementDimensionControllerListVM();
        BeanUtils.copyProperties(settlementDimension,settlementDimensionControllListVM);
        return settlementDimensionControllListVM;
    }
}
