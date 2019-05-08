package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderBusinessComparisonService {

    ResultVO<?> addOrderBusinessComparison(OrderBusinessComparisonAddVM vm);

    ResultVO<?> batchLogicDelete(String ids);

    OrderBusinessComparisonQueryVM getOrderBusinessComparison(String code);

    Page<OrderBusinessComparisonQueryVM> findByConditions(String name, String code, String businessCode,String businessName,String status, String mode,Pageable pageable);

    ResultVO<?> updateOrderBusinessComparison(OrderBusinessComparisonAddVM vm);

    ResultVO<?> getBusinessList();
}
