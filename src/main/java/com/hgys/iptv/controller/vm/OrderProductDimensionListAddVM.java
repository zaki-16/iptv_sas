package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("分配结算产品级多维度新增VM")
public class OrderProductDimensionListAddVM {

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
     * 维度A编码
     */
    @ApiModelProperty("维度A编码")
    private String dimACode;

    /**
     * 维度A数量
     */
    @ApiModelProperty("维度A数量")
    private BigDecimal numberA;

    /**
     * 维度B编码
     */
    @ApiModelProperty("维度B编码")
    private String dimBCode;

    /**
     * 维度B数量
     */
    @ApiModelProperty("维度B数量")
    private BigDecimal numberB;

    /**
     * 维度C编码
     */
    @ApiModelProperty("维度C编码")
    private String dimCCode;

    /**
     * 维度C数量
     */
    @ApiModelProperty("维度C数量")
    private BigDecimal numberC;

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

    public String getDimACode() {
        return dimACode;
    }

    public void setDimACode(String dimACode) {
        this.dimACode = dimACode;
    }

    public BigDecimal getNumberA() {
        return numberA;
    }

    public void setNumberA(BigDecimal numberA) {
        this.numberA = numberA;
    }

    public String getDimBCode() {
        return dimBCode;
    }

    public void setDimBCode(String dimBCode) {
        this.dimBCode = dimBCode;
    }

    public BigDecimal getNumberB() {
        return numberB;
    }

    public void setNumberB(BigDecimal numberB) {
        this.numberB = numberB;
    }

    public String getDimCCode() {
        return dimCCode;
    }

    public void setDimCCode(String dimCCode) {
        this.dimCCode = dimCCode;
    }

    public BigDecimal getNumberC() {
        return numberC;
    }

    public void setNumberC(BigDecimal numberC) {
        this.numberC = numberC;
    }

    public BigDecimal getSetMoney() {
        return setMoney;
    }

    public void setSetMoney(BigDecimal setMoney) {
        this.setMoney = setMoney;
    }
}
