package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.OrderBusiness;
import com.hgys.iptv.model.OrderBusinessCp;
import com.hgys.iptv.model.OrderBusinessWithCp;
import com.hgys.iptv.repository.OrderBuinessWithCpRepository;
import com.hgys.iptv.repository.SmallOrderCpRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderBusinessControllerAssemlber {

    @Autowired
    private OrderBuinessWithCpRepository orderBuinessWithCpRepository;

    @Autowired
    private SmallOrderCpRepository smallOrderCpRepository;


    public OrderBusinessWithCPAddVM getListVM(OrderBusiness sc) {
        OrderBusinessWithCPAddVM vm = new OrderBusinessWithCPAddVM();
        BeanUtils.copyProperties(sc, vm);
        //查询业务信息
        List<OrderBusinessWithCp> relations = orderBuinessWithCpRepository.findByMasterCode(sc.getCode().trim());
        List<SmallOrderBusinessVM> list = new ArrayList<>();
        for (OrderBusinessWithCp r : relations){
            SmallOrderBusinessVM addVM = new SmallOrderBusinessVM();
            BeanUtils.copyProperties(r,addVM);
            //查询业务下Cp
            List<OrderBusinessCp> byMasterCodes = smallOrderCpRepository.findByMasterCodes(addVM.getBucode().trim());
            List<SmallOrderBusinessVM.SmallOrderBusinessCPVM> vms = new ArrayList<>();
            for (OrderBusinessCp f : byMasterCodes){
                SmallOrderBusinessVM.SmallOrderBusinessCPVM o = new SmallOrderBusinessVM.SmallOrderBusinessCPVM();
                BeanUtils.copyProperties(f,o);
                vms.add(o);
                addVM.setLists(vms);
            }
            list.add(addVM);
        }
        vm.setList(list);

        return vm;
    }
}
