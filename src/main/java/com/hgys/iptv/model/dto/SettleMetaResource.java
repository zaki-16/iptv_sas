package com.hgys.iptv.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 分账结算--统一返回结果实体
 *
 * @ClassName SettleMetaResource
 * @Auther: wangz
 * @Date: 2019/5/14 15:59
 * @Description: TODO
 */
@Data
public class SettleMetaResource implements Serializable {
    private static final long serialVersionUID = 4137534435902661694L;

    /**
     *
     * 结算周期	结算名称	结算编码	结算类型	结算方式	结算方式编码	CP名称	CP编码
     * 产品名称	产品编码	业务名称	业务编码	结算金额	结算时间
     */

    /** 结算名称--操作员填写*/
    private String settleName;

    /**结算编码--返填*/
    private String settleCode;

    /**结算类型 == 订购量结算、业务级结算、产品级结算、CP定比例结算、业务定比例结算*/
    private Integer settleType;

    /**结算方式类型*/
    private Integer settleModeType;
    /**结算方式名称 = 结算类型+该类型下的结算规则详情*/
    private String settleModeName;

    /**结算方式编码*/
    private String settleModeCode;

    /** CP名称*/
    private String cpName;

    /** CP编码*/
    private String cpCode;

    /**每个cp的结算金额*/
    private String settleAccount;

    /**结算时间*/
    private Timestamp settleTime;

    /**结算周期*/
    private String settleCycle;

//    --------------------以上是公共设置部分
    /** 业务名称*/
    private String bizName;

    /** 业务编码*/
    private String bizCode;

    /** 产品名称*/
    private String prodName;

    /** 产品编码*/
    private String prodCode;

    /**结算消息简要*/
    private String msg;

    public String getSettleName() {
        return settleName;
    }

    public void setSettleName(String settleName) {
        this.settleName = settleName;
    }

    public String getSettleCode() {
        return settleCode;
    }

    public void setSettleCode(String settleCode) {
        this.settleCode = settleCode;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Integer getSettleModeType() {
        return settleModeType;
    }

    public void setSettleModeType(Integer settleModeType) {
        this.settleModeType = settleModeType;
    }

    public String getSettleModeName() {
        return settleModeName;
    }

    public void setSettleModeName(String settleModeName) {
        this.settleModeName = settleModeName;
    }

    public String getSettleModeCode() {
        return settleModeCode;
    }

    public void setSettleModeCode(String settleModeCode) {
        this.settleModeCode = settleModeCode;
    }

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

    public String getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(String settleAccount) {
        this.settleAccount = settleAccount;
    }

    public Timestamp getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Timestamp settleTime) {
        this.settleTime = settleTime;
    }

    public String getSettleCycle() {
        return settleCycle;
    }

    public void setSettleCycle(String settleCycle) {
        this.settleCycle = settleCycle;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
