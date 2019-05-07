package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.OrderBusinessControllerListVM;
import com.hgys.iptv.model.OrderBusiness;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class OrderBusinessControllerAssemlber {



    public OrderBusinessControllerListVM getListVM(OrderBusiness od){
        OrderBusinessControllerListVM odControllListVM = new OrderBusinessControllerListVM();
        BeanUtils.copyProperties(od,odControllListVM);
        return odControllListVM;
    }
}
