package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName CpControllerListVM
 * @Auther: wangz
 * @Date: 2019/5/7 11:49
 * @Description: TODO
 */
@Data
@ApiModel("集合VM")
public class CpControllerListVM {


    /** 主键*/
    @ApiModelProperty("主键")
    private String id;

    /** 名称*/
    @ApiModelProperty("名称")
    private String name;

    /** 编码*/
    @ApiModelProperty("编码")
    private String code;

    /** cp简称*/
    @ApiModelProperty("cp简称")
    private String cpAbbr;

    /** 联系人姓名*/
    @ApiModelProperty("联系人姓名")
    private String contactNm;

    /** 联系人手机号*/
    @ApiModelProperty("联系人手机号")
    private String contactTel;

    /** 联系人邮箱*/
    @ApiModelProperty("联系人邮箱")
    private String contactMail;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String note;

    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除(0:否；1:是)")
    private Integer isdelete;
}
