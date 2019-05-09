package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "结算单维度新增VM")
@Data
public class SettlementDimensionAddVM {

    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("备注")
    private String remarks;
    @ApiModelProperty("状态")
    private String status;
}
