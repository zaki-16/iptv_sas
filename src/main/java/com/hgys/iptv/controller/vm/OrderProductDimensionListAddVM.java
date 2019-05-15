package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

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
    private String productName;

    /** 产品编码 */
    @ApiModelProperty("产品编码")
    private String productCode;

    /**
     * 维度A编码
     */
    @ApiModelProperty("维度A编码")
    private String dimACode;

    /**
     * 维度B编码
     */
    @ApiModelProperty("维度B编码")
    private String dimBCode;

    /**
     * 维度C编码
     */
    @ApiModelProperty("维度C编码")
    private String dimCCode;

    /**
     * 产品结算金额
     */
    @ApiModelProperty("结算金额（单位：元）")
    private BigDecimal setMoney;
}
