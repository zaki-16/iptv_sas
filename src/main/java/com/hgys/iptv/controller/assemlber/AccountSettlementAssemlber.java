package com.hgys.iptv.controller.assemlber;


import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.*;
import com.hgys.iptv.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AccountSettlementAssemlber {

    @Autowired
    private SettlementOrderRepository settlementOrderRepository;

    @Autowired
    private SettlementDimensionRepository settlementDimensionRepository;

    @Autowired
    private AccountSettlementRepository accountSettlementRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private SettlementMoneyRepository settlementMoneyRepository;

    @Autowired
    private SettlementProductSingleRepository settlementProductSingleRepository;

    @Autowired
    private SettlementProductManyRepository settlementProductManyRepository;

    public AccountSettlementAddVM getListVM(AccountSettlement od){

        AccountSettlementAddVM vm = new AccountSettlementAddVM();
        BeanUtils.copyProperties(od, vm);
        if (1 == vm.getSet_type()) {   //1:订购量结算源数据
            List<SettlementOrder> settlementOrders = settlementOrderRepository.findByMasterCode(od.getCode());
            List<CpOrderCpAddVM> list = new ArrayList<>();
            for (SettlementOrder f : settlementOrders) {
                CpOrderCpAddVM s = new CpOrderCpAddVM();
                BeanUtils.copyProperties(f, s);
                list.add(s);
                vm.setCpAddVMS(list);
            }
        } else if (2 == vm.getSet_type()) {   //2:业务级结算
            List<SettlementMoney> settlementMonies = settlementMoneyRepository.findByMasterCode(od.getCode()); //通过code查询
            List<BusinessBelielAddVM> list = new ArrayList<>();
            for (SettlementMoney f : settlementMonies) {
                BusinessBelielAddVM s = new BusinessBelielAddVM();
                BeanUtils.copyProperties(f, s);
                list.add(s);
                vm.setBelielAddVMS(list);
            }
        } else if (3 == vm.getSet_type()) {   //3:产品级结算
            int mode = orderProductRepository.findBymode(od.getSet_ruleCode()); //通过规则code查询属于单维度还是多维度
            if (mode == 1) {//1:按单维度结算
                List<SettlementProductSingle> settlementProductSingles = settlementProductSingleRepository.findByMasterCode(od.getCode()); //通过code查询
                List<OrderProductDimensionAddVM> list = new ArrayList<>();
                for (SettlementProductSingle f : settlementProductSingles) {
                    OrderProductDimensionAddVM s = new OrderProductDimensionAddVM();
                    BeanUtils.copyProperties(f, s);
                    list.add(s);
                    vm.setDimensionAddVM(list);
                }
            } else if (mode == 2) {//;2:多维度结算
                List<SettlementProductMany> settlementProductManies = settlementProductManyRepository.findByMasterCode(od.getCode()); //通过code查询
                List<OrderProductDimensionListAddVM> list = new ArrayList<>();
                for (SettlementProductMany f : settlementProductManies) {
                    OrderProductDimensionListAddVM s = new OrderProductDimensionListAddVM();
                    BeanUtils.copyProperties(f, s);
                    list.add(s);
                    vm.setDimensionListAddVMS(list);
                }
            }
        } else if (4 == vm.getSet_type()) {//4:CP定比例结算
            List<SettlementMoney> settlementMonies = settlementMoneyRepository.findByMasterCode(od.getCode()); //通过code查询
            List<BusinessBelielAddVM> list = new ArrayList<>();
            for (SettlementMoney f : settlementMonies) {
                BusinessBelielAddVM s = new BusinessBelielAddVM();
                BeanUtils.copyProperties(f, s);
                list.add(s);
                vm.setBelielAddVMS(list);
            }
        } else if (5 == vm.getSet_type()) { //5:业务定比例结算
            List<SettlementMoney> settlementMonies = settlementMoneyRepository.findByMasterCode(od.getCode()); //通过code查询
            List<BusinessBelielAddVM> list = new ArrayList<>();
            for (SettlementMoney f : settlementMonies) {
                BusinessBelielAddVM s = new BusinessBelielAddVM();
                BeanUtils.copyProperties(f, s);
                list.add(s);
                vm.setBelielAddVMS(list);
            }
        }
        return vm;

}
}
