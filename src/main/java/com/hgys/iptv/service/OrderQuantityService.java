package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.OrderQuantityAddVM;
import com.hgys.iptv.controller.vm.OrderQuantityControllerListVM;
import com.hgys.iptv.controller.vm.OrderQuantityWithCPListVM;
import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface OrderQuantityService {


    /**
     * 通过code查询
     * @param code
     * @return
     */
    Optional<OrderQuantity> findByCode(String code);

    /**
     * 新增
     *
     * @param vo
     */
    ResultVO<?> addOrderQuantity(OrderQuantityAddVM vo);


    /*** 通过Id,批量逻辑删除*/
    ResultVO<?> batchDelete(String ids);


    /**
     * 根据条件分页查询
     * @return
     */
    Page<OrderQuantityWithCPListVM> findByConditions(String name, String code, String status, Pageable pageable);

    /**
     * 修改
     * @param vo
     * @return
     */
    ResultVO<?> updateOrderQuantity(OrderQuantityAddVM vo);


    /**
     * 通过code查询结算组合数据
     * @param code
     * @return
     */
    OrderQuantityWithCPListVM getOrderQuantityWithCp(String code);


}
