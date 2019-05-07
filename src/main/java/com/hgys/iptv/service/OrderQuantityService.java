package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.OrderQuantityControllerListVM;
import com.hgys.iptv.controller.vm.SettlementDimensionControllerListVM;
import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface OrderQuantityService {
    /**通过Id查询*/
    OrderQuantity findById(Integer id);
    /*** 添加*/
    ResultVO<?> insterOrderQuantity(String name, String status, String note);


    /*** 通过Id,批量逻辑删除*/
    ResultVO<?> batchDelete(String ids);


    /**
     * 根据条件分页查询
     * @return
     */
    Page<OrderQuantityControllerListVM> findByConditions(String name, String code, String status, Pageable pageable);


    /**
     * 修改
     * @param
     * @return
     */
    ResultVO<?> updateOrderQuantity(OrderQuantity oq);


}
