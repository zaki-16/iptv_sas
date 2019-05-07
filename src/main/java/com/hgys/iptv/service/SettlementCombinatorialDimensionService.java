package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionAddVM;
import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionControllerListVM;
import com.hgys.iptv.model.vo.ResultVO;

public interface SettlementCombinatorialDimensionService {

    /**
     * 新增结算组合维度
     *
     * @param vo
     */
    ResultVO<?> addSettlementCombinatorialDimension(SettlementCombinatorialDimensionAddVM vo);

    /**
     * 批量逻辑删除
     *
     * @param ids
     * @return
     */
    ResultVO<?> batchLogicDelete(String ids);

    /**
     * 通过code查询结算组合数据
     * @param code
     * @return
     */
    SettlementCombinatorialDimensionControllerListVM getSettlementCombinatorialDimension(String code);
}
