package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.AccountSettlementAddVM;
import com.hgys.iptv.model.vo.ResultVO;

public interface SettleByExcelService {
    /**
     * 业务级结算
     */
    ResultVO<?> settleByBusiness(AccountSettlementAddVM settleDTO);
    /**
     * 业务级结算--按金额
     */
    ResultVO<?> settleByBusinessWithAmount(AccountSettlementAddVM settleDTO);
    /**
     * 业务级结算--按比例
     */
    ResultVO<?> settleByBusinessWithRatio(AccountSettlementAddVM settleDTO);
    /**
     * cp.settleType = 1:定比例结算, 2：定金额结算
     */
    ResultVO<?> settleByCp(AccountSettlementAddVM settleDTO);
    /**
     * 产品级结算--单维度
     */
    ResultVO<?> settleByProdWithSingleDime(AccountSettlementAddVM settleDTO);
    /**
     * 产品级结算--组合维度
     */
    ResultVO<?> settleByProdWithCombDime(AccountSettlementAddVM settleDTO);
    /**
     * 订购量结算
     */
    ResultVO<?> settleByQuantity(AccountSettlementAddVM settleDTO);
}
