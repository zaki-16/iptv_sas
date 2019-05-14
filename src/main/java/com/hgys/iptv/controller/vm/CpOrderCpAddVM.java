package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("分配结算订购量结算新增VM")
@Data
public class CpOrderCpAddVM {
    @ApiModelProperty("Cp编码")
    private String cpcode;

    @ApiModelProperty("Cp名称")
    private String cpname;

    @ApiModelProperty("订购量")
    private Integer quantity;
}
