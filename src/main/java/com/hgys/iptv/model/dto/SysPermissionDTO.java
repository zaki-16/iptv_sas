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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
