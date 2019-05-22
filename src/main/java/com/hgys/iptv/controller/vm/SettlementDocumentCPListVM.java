package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "结算文档列表页CP查询VM")
public class SettlementDocumentCPListVM {

    @ApiModelProperty("id")
    private Integer id;

    /** 分账结算编码 */
    @ApiModelProperty("分账结算编码")
    private String masterCode;

    /** 分账结算名称 */
    @ApiModelProperty("分账结算名称")
    private String masterName;

    /** cp编码 */
    @ApiModelProperty("cp编码")
    private String cpcode;

    /** cp名称 */
    @ApiModelProperty("cp名称")
    private String cpname;

    /** 产品编码 */
    @ApiModelProperty("产品编码")
    private String productCode;

    /** 产品名称 */
    @ApiModelProperty("产品名称")
    private String productName;

    /** 业务编码 */
    @ApiModelProperty("业务编码")
    private String businessCode;

    /** 业务名称 */
    @ApiModelProperty("业务名称")
    private String businessName;

    /** 结算金额 */
    @ApiModelProperty("结算金额")
    private BigDecimal settlementMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getCpcode() {
        return cpcode;
    }

    public void setCpcode(String cpcode) {
        this.cpcode = cpcode;
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public BigDecimal getSettlementMoney() {
        return settlementMoney;
    }

    public void setSettlementMoney(BigDecimal settlementMoney) {
        this.settlementMoney = settlementMoney;
    }
}
