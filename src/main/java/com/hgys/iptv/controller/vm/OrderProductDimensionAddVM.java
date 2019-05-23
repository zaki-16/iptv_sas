package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("分配结算产品级单维度新增VM")
public class OrderProductDimensionAddVM {
    /**
     * CP编码
     */
    @ApiModelProperty("CP编码")
    private String cpcode;

    /**
     * CP名称
     */
    @ApiModelProperty("CP名称")
    private String cpname;

    /** 产品名称 */
    @ApiModelProperty("产品名称")
    private String productName;

    /** 产品编码 */
    @ApiModelProperty("产品编码")
    private String productCode;

    /**
     * 维度编码
     */
    @ApiModelProperty("维度编码")
    private String dimCode;

    /**
     * 维度名称
     */
    @ApiModelProperty("维度名称")
    private String dimName;

    /**
     * 维度名称
     */
    @ApiModelProperty("数量")
    private BigDecimal number;

    /**
     * 产品结算金额
     */
    @ApiModelProperty("结算金额（单位：元）")
    private BigDecimal setMoney;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDimCode() {
        return dimCode;
    }

    public void setDimCode(String dimCode) {
        this.dimCode = dimCode;
    }

    public String getDimName() {
        return dimName;
    }

    public void setDimName(String dimName) {
        this.dimName = dimName;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getSetMoney() {
        return setMoney;
    }

    public void setSetMoney(BigDecimal setMoney) {
        this.setMoney = setMoney;
    }
}
