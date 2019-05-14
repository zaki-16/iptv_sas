package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("分配结算产品级单维度新增VM")
@Data
public class OrderProductDimensionAddVM {
    /**
     * CP编码
     */
    @ApiModelProperty("CP编码")
    private String cpcode;

    /**
     * CP名称
     */
    @ApiModelProperty("CP名称")
    private String cpname;

    /** 产品名称 */
    @ApiModelProperty("产品名称")
    private String pname;

    /** 产品编码 */
    @ApiModelProperty("产品编码")
    private String pcode;

    /**
     * 维度编码
     */
    @ApiModelProperty("维度编码")
    private String dimensionCode;

    /**
     * 产品结算金额
     */
    @ApiModelProperty("结算金额（单位：元）")
    private short money;
}
