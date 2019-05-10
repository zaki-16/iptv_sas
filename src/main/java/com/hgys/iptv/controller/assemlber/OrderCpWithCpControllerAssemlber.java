package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.OrderCPWithCPListVM;
import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionControllerListVM;
import com.hgys.iptv.model.OrderCp;
import com.hgys.iptv.model.OrderCpWithCp;
import com.hgys.iptv.model.SettlementCombinatorialDimensionFrom;
import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import com.hgys.iptv.repository.OrderCpWithCpRepository;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionFromRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderCpWithCpControllerAssemlber {

    @Autowired
    private OrderCpWithCpRepository orderCpWithCpRepository;

    public OrderCPWithCPListVM getListVM(OrderCp od){
        OrderCPWithCPListVM vm = new OrderCPWithCPListVM();
        BeanUtils.copyProperties(od,vm);
        List<OrderCpWithCp> byMasterCode = orderCpWithCpRepository.findByMasterCode(od.getCode().trim());

        List<OrderCPWithCPListVM.OrderCpWithCp> list = new ArrayList<>();
        for (OrderCpWithCp f : byMasterCode){
            OrderCPWithCPListVM.OrderCpWithCp s = new OrderCPWithCPListVM.OrderCpWithCp();
            BeanUtils.copyProperties(f,s);
            list.add(s);
        }
        vm.setList(list);
        return vm;
    }










}
