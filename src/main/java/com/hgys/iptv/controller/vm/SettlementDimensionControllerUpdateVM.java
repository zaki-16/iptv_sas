package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@ApiModel(value = "结算维度修改VM")
@Data
public class SettlementDimensionControllerUpdateVM {

    /** 维度名称 */
    @ApiModelProperty("维度名称")
    private String name;

    /** 维度编码 */
    @ApiModelProperty("维度编码")
    @NotBlank(message = "维度编码不能为空")
    private String code;

    /** 状态 */
    @ApiModelProperty("状态")
    @NotBlank(message = "维度状态不能为空")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    @NotBlank(message = "维度备注不能为空")
    private String remarks;

}
