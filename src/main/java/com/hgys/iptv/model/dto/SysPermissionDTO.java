package com.hgys.iptv.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName SysPermissionDTO
 * @Auther: wangz
 * @Date: 2019/5/16 11:45
 * @Description: TODO
 */
@Data
public class SysPermissionDTO {
    @ApiModelProperty("主键--新增时不需要填")
    private Integer id;

    @ApiModelProperty("权限名")
    @NotBlank(message = "权限名不能为空")
    private String name;

    @ApiModelProperty("权限描述")
    private String description;

    @ApiModelProperty("状态0:启用，1：禁用")
    private Integer status;

//    @ApiModelProperty("角色集字符串")
//    private String rids;


}
