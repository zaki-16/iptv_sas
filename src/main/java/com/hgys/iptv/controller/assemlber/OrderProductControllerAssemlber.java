package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.OrderProductControllerListVM;
import com.hgys.iptv.model.OrderProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class OrderProductControllerAssemlber {



    public OrderProductControllerListVM getListVM(OrderProduct od){
        OrderProductControllerListVM odControllListVM = new OrderProductControllerListVM();
        BeanUtils.copyProperties(od,odControllListVM);
        return odControllListVM;
    }
}
