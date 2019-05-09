package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.OrderBusiness;
import com.hgys.iptv.model.OrderBusinessWithCp;
import com.hgys.iptv.repository.OrderBuinessWithCpRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderBusinessControllerAssemlber {

    @Autowired
    private OrderBuinessWithCpRepository orderBuinessWithCpRepository;

    public OrderBusinessWithCPAddVM getListVM(OrderBusiness sc){
        OrderBusinessWithCPAddVM vm = new OrderBusinessWithCPAddVM();
        BeanUtils.copyProperties(sc,vm);
        List<OrderBusinessWithCp> byMasterCode = orderBuinessWithCpRepository.findByMasterCode(sc.getCode().trim());

        List<SmallOrderBusinessVM> list = new ArrayList<>();
        for (OrderBusinessWithCp f : byMasterCode){
            SmallOrderBusinessVM s = new SmallOrderBusinessVM();
            BeanUtils.copyProperties(f,s);
            list.add(s);
        }
        vm.setList(list);
        return vm;
    }
}
