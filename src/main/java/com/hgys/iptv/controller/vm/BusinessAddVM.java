package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
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

    @ApiModelProperty(value = "业务关联的产品集合id字符串")//dataType = "List"
    private String pids;

    @ApiModelProperty(value = "业务关联的cp集合id字符串")//dataType = "List"
    private String cpids;

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

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public String getCpids() {
        return cpids;
    }

    public void setCpids(String cpids) {
        this.cpids = cpids;
    }
}
