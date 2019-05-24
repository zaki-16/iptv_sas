package com.hgys.iptv.model.bean;

import lombok.Data;

import java.util.List;

/**
 * @ClassName MenuAuthNode
 * @Auther: wangz
 * @Date: 2019/5/25 00:13
 * @Description: TODO
 */
@Data
public class MenuAuthNode {
    private Integer id;

    private String name;// name = menuName+":"+permName;
    //菜单 id
    private Integer menuId;

    private String text;

    private String menuName;

    private Integer parentId;//权限父节点

    private String description;

    private Integer status;//0:启用，1：禁用

    private List<MenuAuthNode> childrens;
}
