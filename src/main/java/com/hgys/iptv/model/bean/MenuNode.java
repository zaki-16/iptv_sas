package com.hgys.iptv.model.bean;

import com.hgys.iptv.model.Authority;
import lombok.Data;

import java.util.List;

/**
 * @ClassName MenuNode
 * @Auther: wangz
 * @Date: 2019/5/23 12:15
 * @Description: TODO
 */
@Data
public class MenuNode {
    private Integer id;
    private String name;//
    private String text;//菜单展示名
    private String navigateUrl;//
    private String icon;//图标
    private Integer parentId;//父节点
    private String status;
    private List<MenuNode> childrens;
    //根结点到叶子的路径名或id为权限单元
    // 叶子结点会有一颗权限树
    private List<PermissionNode> permissionTree;

    private List<Authority> authorities;
}
