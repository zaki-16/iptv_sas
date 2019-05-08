package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModelProperty;

public class SettlementDimensionVM {

    /** 维度编码 */
    @ApiModelProperty("结算维度编码")
    private String dim_code;

    /** 维度名称 */
    @ApiModelProperty("结算维度名称")
    private String dim_name;

    @ApiModelProperty("权重")
    private Integer weight;

    public String getDim_code() {
        return dim_code;
    }

    public void setDim_code(String dim_code) {
        this.dim_code = dim_code;
    }

    public String getDim_name() {
        return dim_name;
    }

    public void setDim_name(String dim_name) {
        this.dim_name = dim_name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
