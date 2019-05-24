package com.hgys.iptv.model.bean;

import lombok.Data;

import java.util.List;

/**
 * @ClassName AuthorityNode
 * @Auther: wangz
 * @Date: 2019/5/24 22:13
 * @Description: TODO
 */
@Data
public class AuthorityNode {
    private Integer id;

    private String name;// name = menuName+":"+permName;
    //菜单 id
    private Integer menuId;

    private Integer parentId;//权限父节点

    private String description;
//    //菜单 名称
//    private String menuName;
//    // 权限 id
//    private Integer permId;
//    // 权限 名称
//    private String permName;

    private Integer status;//0:启用，1：禁用

    private List<AuthorityNode> childrens;

}
