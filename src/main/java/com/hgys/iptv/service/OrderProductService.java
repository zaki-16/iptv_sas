package com.hgys.iptv.service;


import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.controller.vm.OrderProductWithSettlementAddVM;
import com.hgys.iptv.model.OrderProduct;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderProductService {

    /** 通过Id查询*/
    OrderProduct findById(Integer id);

    /*** 通过Id,批量逻辑删除*/
    ResultVO<?> batchDeleteop(String ids);


    ResultVO<?> addOrderBusinessComparison(OrderProductWithSettlementAddVM vm);

    Page<OrderProductWithSettlementAddVM> findByConditions(String name, String code, String productcode, String productname, String status, String mode, Pageable pageable);

    ResultVO<?> updateOrderproduct(OrderProductWithSettlementAddVM vm);

}
