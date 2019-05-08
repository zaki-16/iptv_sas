package com.hgys.iptv.service;


import com.hgys.iptv.controller.vm.OrderCPAddVM;
import com.hgys.iptv.controller.vm.OrderCPWithCPListVM;
import com.hgys.iptv.model.OrderCp;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderCpService {

    /*** 通过Id查询*/
    OrderCp findById(Integer id);

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
    Page<OrderCPWithCPListVM> findByConditions(String name, String code, String status, Pageable pageable);

    /**
     * 修改
     * @param vo
     * @return
     */
    ResultVO<?> updateOrderCp(OrderCPWithCPListVM vo);


}
