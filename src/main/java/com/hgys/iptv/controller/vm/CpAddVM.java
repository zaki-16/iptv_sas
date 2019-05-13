package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "cp新增VM")
@Data
public class CpAddVM {
    /**
     * CP名称（必填，列表展示）、
     * CP简称（非必填，列表展示）、
     * CP编码（系统生成，列表不展示）、
     * 所属产品（必填，列表展示）、
     * CP联系人（非必填，列表不展示）、
     * CP联系方式（手机号）（非必填，列表不展示）、
     * CP联系方式（邮箱）（非必填，列表不展示）、
     * 注册时间（系统获取，列表展示）、
     * 修改时间（最后修改时间）（系统获取，列表展示）、
     * 状态（必填，列表展示）、
     * 注销时间（非必填，列表展示）、
     * 备注（非必填，列表不展示）
     */
    @ApiModelProperty("主键，新增时填写无效")
    private Integer id;

    @ApiModelProperty("名称") @NotBlank(message = "不能为空")
    private String name;

    @ApiModelProperty("cp简称")
    private String cpAbbr;

    @ApiModelProperty("状态")@NotBlank(message = "不能为空")
    private Integer status;

    @ApiModelProperty("联系人")@NotBlank(message = "不能为空")
    private String contactNm;

    @ApiModelProperty("手机号")
    private String contactTel;

    @ApiModelProperty("邮箱")
    private String contactMail;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty(value = "cp关联的产品集合id字符串")//dataType = "List"
    private String pids;

    @ApiModelProperty(value = "cp关联的业务集合id字符串")//dataType = "List"
    private String bids;


}
