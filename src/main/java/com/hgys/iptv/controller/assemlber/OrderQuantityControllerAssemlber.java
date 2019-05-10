package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.OrderQuantityAddVM;
import com.hgys.iptv.controller.vm.OrderQuantityWithCPListVM;
import com.hgys.iptv.controller.vm.SmallOrderCpVM;
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

    public OrderQuantityAddVM getListVM(OrderQuantity od){
        OrderQuantityAddVM odControllListVM = new OrderQuantityAddVM();
        BeanUtils.copyProperties(od,odControllListVM);
        List<OrderQuantityWithCp> byMasterCode = orderQuantityWithCpRepository.findByMasterCode(od.getCode().trim());

        List<SmallOrderCpVM> list = new ArrayList<>();
        for (OrderQuantityWithCp f : byMasterCode){
            SmallOrderCpVM s = new SmallOrderCpVM();
            BeanUtils.copyProperties(f,s);
            list.add(s);
        }
        odControllListVM.setList(list);
        return odControllListVM;
    }

}
