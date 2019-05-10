package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("业务类型-业务定比例新增VM(cp关系)")
@Data
public class OrderBusinessComparisonAddListVM{

    @ApiModelProperty("id")
    private Integer id;

    /** 业务编码 */
    @ApiModelProperty("业务编码")
    private String masterCode;

    /** cp编码 */
    @ApiModelProperty("cp编码")
    private String cp_code;

    /** cp名称 */
    @ApiModelProperty("cp名称")
    private String cp_name;

    /** 所占比例 */
    @ApiModelProperty("所占比例")
    private Integer proportion;

    /** 金额 */
    @ApiModelProperty("金额")
    private Integer money;
}
