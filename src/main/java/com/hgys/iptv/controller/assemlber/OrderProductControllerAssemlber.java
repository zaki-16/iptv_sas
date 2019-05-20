package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.*;
import com.hgys.iptv.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
public class OrderProductControllerAssemlber {

    @Autowired
    private OrderProductWithSCDRepository orderProductWithSCDRepository;

    @Autowired
    private SettlementCombinatorialDimensionFromRepository settlementCombinatorialDimensionFromRepository;

    @Autowired
    private SettlementDimensionRepository settlementDimensionRepository;

    @Autowired
    private CpProductRepository cpProductRepository;

    @Autowired
    private CpRepository cpRepository;


    @Autowired
    private ProductRepository productRepository;


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
            Integer productid = productRepository.findByMasterCodesid(f.getPcode().trim());
            List<CpProduct> cpProducts = cpProductRepository.findByMastercplists(productid);
            List<OrderProductWithSettlementfindVM.OrderProductWithSCDAddLIstVMs.OrderProductCPWithSCDAddLIstVMs> list = new ArrayList<>();
            for (CpProduct fs : cpProducts) {
                Integer  cpid=fs.getCpid();
                String cpname =cpRepository.findByMastercpname(cpid);
                OrderProductWithSettlementfindVM.OrderProductWithSCDAddLIstVMs.OrderProductCPWithSCDAddLIstVMs ss = new OrderProductWithSettlementfindVM.OrderProductWithSCDAddLIstVMs.OrderProductCPWithSCDAddLIstVMs();
                BeanUtils.copyProperties(fs, ss);
                ss.setCpname(cpname);
                list.add(ss);
                s.setListcp(list);
            }
            if (vm.getMode()==2){  //多维度
            List<SettlementCombinatorialDimensionFrom> scd=settlementCombinatorialDimensionFromRepository.findByMasterCode(sc.getScdcode());
            List<OrderProductWithSettlementfindVM.SettlementCombinatorialDimensionFrom> listdc = new ArrayList<>();
              for (SettlementCombinatorialDimensionFrom fs : scd) {
                  OrderProductWithSettlementfindVM.SettlementCombinatorialDimensionFrom ss = new OrderProductWithSettlementfindVM.SettlementCombinatorialDimensionFrom();
                  BeanUtils.copyProperties(fs, ss);
                  listdc.add(ss);
                  vm.setListscd(listdc);
              }
          }else{ //单维度
                List<SettlementDimension> sd=settlementDimensionRepository.findBydCode(sc.getSdcode());
                List<OrderProductWithSettlementfindVM.SettlementDimension> listd = new ArrayList<>();
                for (SettlementDimension fs : sd) {
                    OrderProductWithSettlementfindVM.SettlementDimension ss = new OrderProductWithSettlementfindVM.SettlementDimension();
                    BeanUtils.copyProperties(fs, ss);
                    listd.add(ss);
                    vm.setListsd(listd);
                }

            }
        }
        return vm;
    }

}
























