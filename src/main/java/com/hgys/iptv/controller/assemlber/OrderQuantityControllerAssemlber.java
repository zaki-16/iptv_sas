package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.OrderQuantityWithCPListVM;
import com.hgys.iptv.model.OrderCpWithCp;
import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.OrderQuantityWithCp;
import com.hgys.iptv.repository.OrderQuantityWithCpRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderQuantityControllerAssemlber {

    @Autowired
    private OrderQuantityWithCpRepository orderQuantityWithCpRepository;

    public OrderQuantityWithCPListVM getListVM(OrderQuantity od){
        OrderQuantityWithCPListVM odControllListVM = new OrderQuantityWithCPListVM();
        BeanUtils.copyProperties(od,odControllListVM);
        List<OrderQuantityWithCp> byMasterCode = orderQuantityWithCpRepository.findByMasterCode(od.getCode().trim());

        List<OrderQuantityWithCPListVM.OrderQuantityWithCp> list = new ArrayList<>();
        for (OrderQuantityWithCp f : byMasterCode){
            OrderQuantityWithCPListVM.OrderQuantityWithCp s = new OrderQuantityWithCPListVM.OrderQuantityWithCp();
            BeanUtils.copyProperties(f,s);
            list.add(s);
        }
        odControllListVM.setList(list);
        return odControllListVM;
    }

}
