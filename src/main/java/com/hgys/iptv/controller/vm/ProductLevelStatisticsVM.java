package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@ApiModel("产品结算统计VM")
public class ProductLevelStatisticsVM {

    /** 结算开始时间 */
    @ApiModelProperty("结算开始时间")
    private Timestamp setStartTime;

    /** 结算结束时间 */
    @ApiModelProperty("结算结束时间")
    private Timestamp setEndTime;

    @ApiModelProperty("结算编码")
    private String code;

    /** 名称 */
    @ApiModelProperty("结算名称")
    private String name;

    @ApiModelProperty("账单总金额")
    private BigDecimal allMongey;

    @ApiModelProperty("最多12条数据")
    List<ProductLevelStatisticsCPVM> vmList;

    @ApiModelProperty("最多6条数据")
    List<ProductLevelStatisticsCPVM> vmCPList;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAllMongey() {
        return allMongey;
    }

    public void setAllMongey(BigDecimal allMongey) {
        this.allMongey = allMongey;
    }

    public List<ProductLevelStatisticsCPVM> getVmList() {
        return vmList;
    }

    public void setVmList(List<ProductLevelStatisticsCPVM> vmList) {
        this.vmList = vmList;
    }

    public List<ProductLevelStatisticsCPVM> getVmCPList() {
        return vmCPList;
    }

    public void setVmCPList(List<ProductLevelStatisticsCPVM> vmCPList) {
        this.vmCPList = vmCPList;
    }
}
