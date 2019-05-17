package com.hgys.iptv.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

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

    @ApiModelProperty("用户自定义展示名")
    private String displayName;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty("用户注册密码---修改时不展示--展示了也需要提供正确密码才能修改其他信息")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("状态0:启用，1：禁用")
    private Integer status;

    // 角色字符串
    @ApiModelProperty("用户所具有的角色集字符串--不填写为游客")
    private String rids;

}
