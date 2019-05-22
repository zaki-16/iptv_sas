package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

@ApiModel(value = "结算文档列表页CP查询Excel文档VM")
public class SettlementDocumentCPListExcelVM {

    @ApiModelProperty("分账结算ID")
    private Integer masterId;

    @ApiModelProperty("结算账期开始时间")
    private Timestamp setStartTime;

    @ApiModelProperty("结算账期结束时间")
    private Timestamp setEndTime;

    @ApiModelProperty("结算状态")
    private Integer status;

    @ApiModelProperty("结算类型")
    /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算 */
    private Integer type;


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

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Timestamp getSetStartTime() {
        return setStartTime;
    }

    public void setSetStartTime(Timestamp setStartTime) {
        this.setStartTime = setStartTime;
    }

    public Timestamp getSetEndTime() {
        return setEndTime;
    }

    public void setSetEndTime(Timestamp setEndTime) {
        this.setEndTime = setEndTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
