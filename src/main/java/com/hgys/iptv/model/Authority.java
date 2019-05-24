package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 角色-菜单-权限 明细表
 *
 * @ClassName SysAuthority
 * @Auther: wangz
 * @Date: 2019/5/19 14:32
 * @Description: TODO
 */
@Data
@Entity
@Table(name="authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    private String name;// name = menuName+":"+permName;

    private String text;
    //菜单 id
    private Integer menuId;
//    //菜单 名称
    private String menuName;
//    // 权限 id
//    private Integer permId;
//    // 权限 名称
//    private String permName;
    private Integer parentId;//权限父节点

    private String description;

    private Integer status;//0:启用，1：禁用

    private Timestamp createdTime;

}
