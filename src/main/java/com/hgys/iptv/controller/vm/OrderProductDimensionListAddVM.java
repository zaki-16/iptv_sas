package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("分配结算产品级多维度新增VM")
@Data
public class OrderProductDimensionListAddVM {

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
     * 维度A编码
     */
    @ApiModelProperty("维度A编码")
    private String dimensionACode;

    /**
     * 维度B编码
     */
    @ApiModelProperty("维度B编码")
    private String dimensionBCode;

    /**
     * 维度C编码
     */
    @ApiModelProperty("维度C编码")
    private String dimensionCCode;

    /**
     * 产品结算金额
     */
    @ApiModelProperty("结算金额（单位：元）")
    private short money;
}
