package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.OrderQuantityControllerListVM;
import com.hgys.iptv.model.OrderQuantity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class OrderQuantityControllerAssemlber {



    public OrderQuantityControllerListVM getListVM(OrderQuantity od){
        OrderQuantityControllerListVM odControllListVM = new OrderQuantityControllerListVM();
        BeanUtils.copyProperties(od,odControllListVM);
        return odControllListVM;
    }
}
