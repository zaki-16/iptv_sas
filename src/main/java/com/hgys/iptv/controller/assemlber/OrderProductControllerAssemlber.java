package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.*;
import com.hgys.iptv.repository.CpOrderBusinessComparisonRepository;
import com.hgys.iptv.repository.OrderProductWithSCDRepository;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionFromRepository;
import com.hgys.iptv.repository.SettlementDimensionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderProductControllerAssemlber {

    @Autowired
    private OrderProductWithSCDRepository orderProductWithSCDRepository;

    @Autowired
    private SettlementCombinatorialDimensionFromRepository settlementCombinatorialDimensionFromRepository;

    @Autowired
    private SettlementDimensionRepository settlementDimensionRepository;


    public OrderProductWithSettlementfindVM getListVM(OrderProduct sc){
        OrderProductWithSettlementfindVM vm = new OrderProductWithSettlementfindVM();
        BeanUtils.copyProperties(sc,vm);
        List<OrderProductWithSCD> byMasterCode = orderProductWithSCDRepository.findByMasterCode(sc.getCode().trim());
        List<OrderProductWithSettlementfindVM.OrderProductWithSCDAddLIstVMs> lists = new ArrayList<>();
        for (OrderProductWithSCD f : byMasterCode) {
            OrderProductWithSettlementfindVM.OrderProductWithSCDAddLIstVMs s = new OrderProductWithSettlementfindVM.OrderProductWithSCDAddLIstVMs();
            BeanUtils.copyProperties(f, s);
            lists.add(s);
            vm.setList(lists);
            if (vm.getMode()==2){  //多维度
            List<SettlementCombinatorialDimensionFrom> scd=settlementCombinatorialDimensionFromRepository.findByMasterCode(sc.getScdcode());
            List<OrderProductWithSettlementfindVM.SettlementCombinatorialDimensionFrom> list = new ArrayList<>();
              for (SettlementCombinatorialDimensionFrom fs : scd) {
                  OrderProductWithSettlementfindVM.SettlementCombinatorialDimensionFrom ss = new OrderProductWithSettlementfindVM.SettlementCombinatorialDimensionFrom();
                  BeanUtils.copyProperties(fs, ss);
                  list.add(ss);
                  vm.setListscd(list);
              }
          }else{ //单维度
                List<SettlementDimension> sd=settlementDimensionRepository.findBydCode(sc.getSdcode());
                List<OrderProductWithSettlementfindVM.SettlementDimension> list = new ArrayList<>();
                for (SettlementDimension fs : sd) {
                    OrderProductWithSettlementfindVM.SettlementDimension ss = new OrderProductWithSettlementfindVM.SettlementDimension();
                    BeanUtils.copyProperties(fs, ss);
                    list.add(ss);
                    vm.setListsd(list);
                }

            }
        }
        return vm;
    }

}
























