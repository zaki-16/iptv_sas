package com.hgys.iptv.service;


import com.hgys.iptv.controller.vm.OrderCPAddVM;
import com.hgys.iptv.controller.vm.OrderCPWithCPListVM;
import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionAddVM;
import com.hgys.iptv.model.OrderCp;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderCpService {




    /**
     * 通过id查询结算组合数据
     * @param id
     * @return
     */
    OrderCPWithCPListVM findById(String id);




    /**
     * 新增结算组合维度
     *
     * @param vo
     */
    ResultVO<?> addOrderCp(OrderCPAddVM vo);

    /*** 通过Id,批量逻辑删除*/
    ResultVO<?> batchDeleteoc(String ids);

    /**
     * 通过code查询结算组合数据
     * @param code
     * @return
     */
    OrderCPWithCPListVM getOrderCp(String code);
    /**
     * 根据条件分页查询
     * @return
     */
    Page<OrderCPWithCPListVM> findByConditions(String name, String code, String status, String settleaccounts,Pageable pageable);

    /**
     * 修改
     * @param vo
     * @return
     */
    ResultVO<?> updateOrderCp(OrderCPAddVM vo);


}
