package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "结算组合维度新增VM")
@Data
public class SettlementCombinatorialDimensionAddVM {

    /** 维度名称 */
    @ApiModelProperty("结算组合维度名称")
    private String name;

    @ApiModelProperty("结算组合维度备注")
    private String remakes;

    @ApiModelProperty("结算组合维度状态")
    private Integer status;

    /** 维度编码 */
    @ApiModelProperty("结算维度编码")
    private String dim_code;

    /** 维度名称 */
    @ApiModelProperty("结算维度名称")
    private String dim_name;

}
