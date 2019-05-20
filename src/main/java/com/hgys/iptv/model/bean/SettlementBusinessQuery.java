package com.hgys.iptv.model.bean;

import lombok.Data;

import java.math.BigDecimal;

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

    /**
     * cp所在金额
     */
    private BigDecimal money;

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessWeight() {
        return businessWeight;
    }

    public void setBusinessWeight(String businessWeight) {
        this.businessWeight = businessWeight;
    }

    public String getCpWeight() {
        return cpWeight;
    }

    public void setCpWeight(String cpWeight) {
        this.cpWeight = cpWeight;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
