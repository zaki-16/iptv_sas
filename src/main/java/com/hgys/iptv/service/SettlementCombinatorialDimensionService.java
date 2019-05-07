package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionAddVM;
import com.hgys.iptv.model.vo.ResultVO;

public interface SettlementCombinatorialDimensionService {

    /**
     * 新增结算组合维度
     * @param vo
     */
    ResultVO<?> addSettlementCombinatorialDimension(SettlementCombinatorialDimensionAddVM vo);
}
