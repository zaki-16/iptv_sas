package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddListVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonBusinessAddVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.model.BusinessComparisonRelation;
import com.hgys.iptv.model.CpOrderBusinessComparison;
import com.hgys.iptv.model.OrderBusinessComparison;
import com.hgys.iptv.repository.BusinessComparisonRelationRepository;
import com.hgys.iptv.repository.CpOrderBusinessComparisonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderBusinessComparisonControllerAssemlber {

    @Autowired
    private CpOrderBusinessComparisonRepository cpOrderBusinessComparisonRepository;

    @Autowired
    private BusinessComparisonRelationRepository businessComparisonRelationRepository;

    public OrderBusinessComparisonQueryVM getListVM(OrderBusinessComparison sc){
        OrderBusinessComparisonQueryVM vm = new OrderBusinessComparisonQueryVM();
        BeanUtils.copyProperties(sc,vm);

        //查询业务信息
        List<BusinessComparisonRelation> relations = businessComparisonRelationRepository.findByMasterCode(sc.getCode());
        List<OrderBusinessComparisonBusinessAddVM> list = new ArrayList<>();
        for (BusinessComparisonRelation r : relations){
            OrderBusinessComparisonBusinessAddVM addVM = new OrderBusinessComparisonBusinessAddVM();
            BeanUtils.copyProperties(r,addVM);

            //查询业务下Cp
            List<CpOrderBusinessComparison> byMasterCode = cpOrderBusinessComparisonRepository.findByMasterCode(r.getCode());
            List<OrderBusinessComparisonAddListVM> vms = new ArrayList<>();
            for (CpOrderBusinessComparison f : byMasterCode){
                OrderBusinessComparisonAddListVM o = new OrderBusinessComparisonAddListVM();
                BeanUtils.copyProperties(f,o);
                vms.add(o);
                addVM.setList(vms);
            }
            list.add(addVM);
        }
        vm.setList(list);

        return vm;
    }
}
