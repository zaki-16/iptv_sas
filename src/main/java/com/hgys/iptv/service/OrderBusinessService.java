package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.controller.vm.OrderBusinessWithCPAddVM;
import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionAddVM;
import com.hgys.iptv.model.OrderBusiness;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface OrderBusinessService {


    /** 通过Id查询*/
    OrderBusiness findById(Integer id);

    Page<OrderBusinessWithCPAddVM> findByConditions(String name, String code,  String status, Pageable pageable);

    /**
     * 通过Id逻辑删除
     * @param ids
     * @return
     */
    ResultVO<?> batchDeleteob(String ids);


    /**
     * 新增结算组合维度
     *
     * @param vo
     */
    ResultVO<?> addOrderBusiness(OrderBusinessWithCPAddVM vo);

    ResultVO<?> getBusinessList();


    ResultVO<?> updateOrderBusiness(OrderBusinessWithCPAddVM vm);

    OrderBusinessWithCPAddVM getOrderBusiness(String code);
}

