package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@ApiModel("业务类型-业务定比例查询VM")
@Data
public class OrderBusinessComparisonQueryVM {

    @ApiModelProperty("主键")
    private Integer id;
    /** 名称 */
    @ApiModelProperty("业务定比例名称")
    private String name;

    @ApiModelProperty("业务定比例编码")
    private String code;

    /** 结算方式(1:按比例结算;2:按金额结算) */
    @ApiModelProperty("结算方式(1:按比例结算;2:按金额结算)")
    private Integer mode;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remakes;

    /** 录入时间 */
    @ApiModelProperty("录入时间")
    private Timestamp inputTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;

    /** 是否删除 */
    @ApiModelProperty("是否删除")
    private Integer isdelete;

    @ApiModelProperty("业务定比例与业务关系")
    List<OrderBusinessComparisonBusinessAddVM> list;

}
