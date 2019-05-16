package com.hgys.iptv.model.bean;

import lombok.Data;

@Data
public class SettlementBusinessQuery {
    /**
     * cp名称
     */
    private String cpName;

    /**
     * cp编码
     */
    private String cpCode;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 业务权重
     */
    private String businessWeight;

    /**
     * cp权重
     */
    private String cpWeight;
}
