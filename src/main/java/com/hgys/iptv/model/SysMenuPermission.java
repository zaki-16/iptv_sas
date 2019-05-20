package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 菜单-权限 中间表
 * @ClassName SysMenuPermission
 * @Auther: wangz
 * @Date: 2019/5/19 14:08
 * @Description: TODO
 */
@Data
@Entity
@Table(name="sys_menu_permission")
public class SysMenuPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;
    //菜单 id
    private Integer menuId;
    // 权限 id
    private Integer permId;

}
