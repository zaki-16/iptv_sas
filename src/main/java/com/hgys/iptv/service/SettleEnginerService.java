package com.hgys.iptv.service;

import com.hgys.iptv.model.dto.SettleByBusinessDTO;
import com.hgys.iptv.model.dto.SettleByCpDTO;
import com.hgys.iptv.model.dto.SettleMetaResource;

/**
 * 结算引擎接口
 */
public interface SettleEnginerService {
    /**
     * 业务级结算
     */
    SettleMetaResource settleByBusiness(SettleByBusinessDTO settleByBusinessDTO);
    /**
     * 业务级结算--按金额
     */
    SettleMetaResource settleByBusinessWithAmount();
    /**
     * 业务级结算--按比例
     */
    SettleMetaResource settleByBusinessWithRatio();
    /**
     * cp.settleType = 1:定比例结算, 2：定金额结算
     */
    SettleMetaResource settleByCp(SettleByCpDTO settleByCpDTO);
    /**
     * 产品级结算--要有各维度权重
     */
    SettleMetaResource settleByProduct();
    /**
     * 订购量结算
     */
    SettleMetaResource settleByQuantity();
}
