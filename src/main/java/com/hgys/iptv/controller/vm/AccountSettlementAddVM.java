package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@ApiModel(value = "分配结算新增VM")
public class AccountSettlementAddVM {

    /** 名称 */
    @ApiModelProperty("名称")
    private String name;

    /** Id */
    @ApiModelProperty("id")
    private Integer id;

    /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算 */
    @ApiModelProperty("1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算")
    private Integer set_type;

    /** 结算规则编码 */
    @ApiModelProperty("结算规则编码")
    private String set_ruleCode;

    /** 结算规则名称 */
    @ApiModelProperty("结算规则名称")
    private String set_ruleName;

    /** 1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回 */
    @ApiModelProperty("1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remakes;

    /** 结算开始时间 */
    @ApiModelProperty("结算开始时间(2019-01-01 12:12:12)")
    private String startTime;

    /** 结算结束时间 */
    @ApiModelProperty("结算结束时间(2019-01-01 12:12:12)")
    private String endTime;

    /**分配结算业务级结算总收入*/
    @ApiModelProperty("分配结算业务级结算总收入")
    private BigDecimal businessMoney;

    /**分配结算订购量结算总收入*/
    @ApiModelProperty("分配结算订购量结算总收入")
    private BigDecimal orderMoney;

    /**分配结算CP定比例结算总收入*/
    @ApiModelProperty("分配结算CP定比例结算总收入")
    private BigDecimal cpAllMoney;

    /**分配结算订购量信息集合*/
    @ApiModelProperty("分配结算订购量信息集合")
    private List<CpOrderCpAddVM> cpAddVMS;

    /**分配结算产品级单维度结算信息集合*/
    @ApiModelProperty("分配结算产品级单维度信息集合")
    private List<OrderProductDimensionAddVM> dimensionAddVM;

    /**分配结算产品级多维度信息集合*/
    @ApiModelProperty("分配结算产品级多维度信息集合")
    private List<OrderProductDimensionListAddVM> dimensionListAddVMS;

    /**分配结算业务定比例结算信息集合*/
    @ApiModelProperty("分配结算业务定比例结算信息集合")
    private List<BusinessBelielAddVM> belielAddVMS;
}
