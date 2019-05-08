package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddLIstVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.model.CpOrderBusinessComparison;
import com.hgys.iptv.model.OrderBusinessComparison;
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

    public OrderBusinessComparisonQueryVM getListVM(OrderBusinessComparison sc){
        OrderBusinessComparisonQueryVM vm = new OrderBusinessComparisonQueryVM();
        BeanUtils.copyProperties(sc,vm);
        List<CpOrderBusinessComparison> byMasterCode = cpOrderBusinessComparisonRepository.findByMasterCode(sc.getCode().trim());

        List<OrderBusinessComparisonAddLIstVM> list = new ArrayList<>();
        for (CpOrderBusinessComparison f : byMasterCode){
            OrderBusinessComparisonAddLIstVM s = new OrderBusinessComparisonAddLIstVM();
            BeanUtils.copyProperties(f,s);
            list.add(s);
        }
        vm.setList(list);
        return vm;
    }
}
