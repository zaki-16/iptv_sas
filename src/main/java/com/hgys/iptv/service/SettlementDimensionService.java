package com.hgys.iptv.service;


import com.hgys.iptv.controller.vm.SettlementDimensionAddVM;
import com.hgys.iptv.controller.vm.SettlementDimensionControllerListVM;
import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SettlementDimensionService {
    /**
     * 新增
     */
    ResultVO<?> insterSettlementDimension(SettlementDimensionAddVM vo);

    /**
     * 通过code查询
     * @param code
     * @return
     */
    Optional<SettlementDimension> findByCode(String code);

    /**
     * 通过code查询
     * @param id
     * @return
     */
    Optional<SettlementDimension> findById(String id);

    /**
     * 通过Id,批量逻辑删除
     * @param ids
     * @return
     */
    ResultVO<?> batchLogicDelete(String ids);

    /**
     * 修改结算维度信息
     * @param vo
     * @return
     */
    ResultVO<?> updateSettlementDimension(SettlementDimension vo);

    /**
     * 根据条件分页查询
     * @return
     */
    Page<SettlementDimensionControllerListVM> findByConditions(String name, String code, String status, Pageable pageable);

    /**
     * 保存结算维度
     * @param settlementDimension
     * @return
     */
    SettlementDimension save(SettlementDimension settlementDimension);

    /**
     * 查询维度列表不分页
     * @return
     */
    List<SettlementDimension> findAll();

    Page<SettlementDimension> a();
}
