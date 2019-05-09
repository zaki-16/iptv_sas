package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "业务修改新增VM")
public class BusinessAddVM {
    @ApiModelProperty("主键，新增时填写无效")
    private Integer id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("业务类型")
    private Integer bizType;
    @ApiModelProperty("结算类型")
    private Integer settleType;
    @ApiModelProperty("状态")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
