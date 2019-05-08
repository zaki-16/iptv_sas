package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@ApiModel(value = "结算组合维度新增VM")
public class SettlementCombinatorialDimensionAddVM implements Serializable{

    @ApiModelProperty("结算组合维度ID")
    private Integer id;
    /** 维度名称 */
    @ApiModelProperty("结算组合维度名称")
    @NotBlank(message = "结算组合维度名称不能为空")
    private String name;

    @ApiModelProperty("结算组合维度备注")
    private String remakes;

    @ApiModelProperty("结算组合维度状态")
    private Integer status;

    @ApiModelProperty(value = "结算维度组合集合",dataType = "List")
    private List<SettlementDimensionVM> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemakes() {
        return remakes;
    }

    public void setRemakes(String remakes) {
        this.remakes = remakes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SettlementDimensionVM> getList() {
        return list;
    }

    public void setList(List<SettlementDimensionVM> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
