package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@ApiModel(value = "结算组合维度新增VM")
@Data
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

}
