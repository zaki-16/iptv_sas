package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel("集合VM")
public class OrderCpControllerListVM {

    /** 主键 */
    @ApiModelProperty("主键")
    private Integer id;

    /** 名称*/
    @ApiModelProperty("名称")
    private String name;

    /** 编码*/
    @ApiModelProperty("编码")
    private String code;

    /** 录入时间 */
    @ApiModelProperty("录入时间")
    private Timestamp inputTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String note;

    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除(0:否；1:是)")
    private Integer isdelete;

    /** 结算方式 0.按比例结算 1.按金额结算
     */
    @ApiModelProperty("结算方式")
    private Integer settleaccounts;

}
