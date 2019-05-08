package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.controller.vm.SettlementDimensionControllerListVM;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.SettlementDimension;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName CpControllerAssemlber
 * @Auther: wangz
 * @Date: 2019/5/8 15:44
 * @Description: TODO
 */
@Component
public class CpControllerAssemlber {
    public CpControllerListVM getListVM(Cp cp){
        CpControllerListVM cpvm = new CpControllerListVM();
        BeanUtils.copyProperties(cp,cpvm);
        return cpvm;
    }
}
