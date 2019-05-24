package com.hgys.iptv.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName SysUserDTO
 * @Auther: wangz
 * @Date: 2019/5/16 11:40
 * @Description: TODO
 */
@Data
public class SysUserDTO {
    @ApiModelProperty("主键--新增时不需要填")
    private Integer id;

    @ApiModelProperty("用户注册名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty("用户注册密码---修改时不展示")
    private String password;

    private String email;
    @ApiModelProperty("电话")
    private String telephone;//电话
    @ApiModelProperty("手机号")
    private String mobilePhone;//手机号

    @ApiModelProperty("状态0:启用，1：禁用")
    private Integer status;

    @ApiModelProperty("用户所属cp的id")
    private Integer cpId;
    // 角色字符串
    @ApiModelProperty("用户所具有的角色集字符串--不填写为游客")
    private String rids;

}
