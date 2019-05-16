package com.hgys.iptv.model.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BusinessBiLiQuery {
    /**
     * cp编码
     */
    private String cpcode;

    /**
     * cp名称
     */
    private String cpname;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 所占比例
     */
    private Integer proportion;
}
