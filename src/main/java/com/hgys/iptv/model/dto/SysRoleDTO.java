package com.hgys.iptv.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @ClassName SysRoleDTO
 * @Auther: wangz
 * @Date: 2019/5/16 11:45
 * @Description: TODO
 */
@Data
public class SysRoleDTO {
    @ApiModelProperty("主键--新增时不需要填")
    private Integer id;

    @ApiModelProperty("角色名--建议全大写")
    @NotBlank(message = "角色名不能为空")
    private String name;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("状态0:启用，1：禁用")
    private Integer status;

//    @ApiModelProperty("角色集字符串")
//    private String uids;

    @ApiModelProperty("权限集字符串")
    private String pids;
}
