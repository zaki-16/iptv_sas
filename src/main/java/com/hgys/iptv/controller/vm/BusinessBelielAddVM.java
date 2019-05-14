package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("分配结算业务定比例结算新增VM")
@Data
public class BusinessBelielAddVM {
    /**
     * 业务名称
     */
    @ApiModelProperty("业务名称")
    private String name;

    /**
     * 业务编码
     */
    @ApiModelProperty("业务编码")
    private String code;

    /**
     * 业务收入
     */
    @ApiModelProperty("业务收入")
    private short money;
}
