package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.OrderCpControllerListVM;
import com.hgys.iptv.model.OrderCp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class OrderCpControllerAssemlber {



    public OrderCpControllerListVM getListVM(OrderCp od){
        OrderCpControllerListVM odControllListVM = new OrderCpControllerListVM();
        BeanUtils.copyProperties(od,odControllListVM);
        return odControllListVM;
    }
}
