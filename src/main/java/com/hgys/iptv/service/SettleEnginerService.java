package com.hgys.iptv.service;

import com.hgys.iptv.model.dto.SettleDTO;
import com.hgys.iptv.model.dto.SettleMetaResource;

/**
 * 结算引擎接口
 */
public interface SettleEnginerService {
    /**
     * 业务级结算
     */
    SettleMetaResource settleByBusiness(SettleDTO settleDTO);
    /**
     * 业务级结算--按金额
     */
    SettleMetaResource settleByBusinessWithAmount(SettleDTO settleDTO);
    /**
     * 业务级结算--按比例
     */
    SettleMetaResource settleByBusinessWithRatio(SettleDTO settleDTO);
    /**
     * cp.settleType = 1:定比例结算, 2：定金额结算
     */
    SettleMetaResource settleByCp(SettleDTO settleDTO);
    /**
     * 产品级结算--单维度
     */
    SettleMetaResource settleByProdWithSingleDime(SettleDTO settleDTO);
    /**
     * 产品级结算--组合维度
     */
    SettleMetaResource settleByProdWithCombDime(SettleDTO settleDTO);
    /**
     * 订购量结算
     */
    SettleMetaResource settleByQuantity(SettleDTO settleDTO);
}
