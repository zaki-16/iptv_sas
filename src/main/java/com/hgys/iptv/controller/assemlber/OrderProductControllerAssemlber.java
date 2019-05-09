package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.CpOrderBusinessComparison;
import com.hgys.iptv.model.OrderBusinessComparison;
import com.hgys.iptv.model.OrderProduct;
import com.hgys.iptv.model.OrderProductWithSCD;
import com.hgys.iptv.repository.CpOrderBusinessComparisonRepository;
import com.hgys.iptv.repository.OrderProductWithSCDRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderProductControllerAssemlber {

    @Autowired
    private OrderProductWithSCDRepository orderProductWithSCDRepository;

    public OrderProductWithSettlementAddVM getListVM(OrderProduct sc){
        OrderProductWithSettlementAddVM vm = new OrderProductWithSettlementAddVM();
        BeanUtils.copyProperties(sc,vm);
        List<OrderProductWithSCD> byMasterCode = orderProductWithSCDRepository.findByMasterCode(sc.getCode().trim());

        List<OrderProductWithSCDAddLIstVM> list = new ArrayList<>();
        for (OrderProductWithSCD f : byMasterCode){
            OrderProductWithSCDAddLIstVM s = new OrderProductWithSCDAddLIstVM();
            BeanUtils.copyProperties(f,s);
            list.add(s);
        }
        vm.setList(list);
        return vm;
    }

}
