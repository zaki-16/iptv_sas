package com.hgys.iptv.model.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @ClassName SysMenuDTO
 * @Auther: wangz
 * @Date: 2019/5/20 15:39
 * @Description: TODO
 */
@Data
public class SysMenuDTO {
    private Integer id;
    @ApiModelProperty("菜单标识名 如 cpMenu")
    private String name;//
    @ApiModelProperty("菜单展示名")
    private String text;//菜单展示名
    private String navigateUrl;//
    private String icon;//图标
    private Integer parentId;//父节点
    //    private Integer level;//层级
//    private Integer sequence;//序号
//    private Timestamp createTime;
    private Integer status;

    private String pids;
}
