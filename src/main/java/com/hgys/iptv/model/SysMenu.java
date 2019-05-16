package com.hgys.iptv.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName SysMenu
 * @Auther: wangz
 * @Date: 2019/5/16 21:55
 * @Description: TODO
 */
@Data
public class SysMenu {
    private Long id;
    private String name;
    private String code;
    private String navigateUrl;//
    private String icon;//图标
    private SysMenu parentMenu;//父节点
    private Integer level;//层级
    private Integer sequence;//序号
    private Timestamp createTime;
    private Integer status;
}
