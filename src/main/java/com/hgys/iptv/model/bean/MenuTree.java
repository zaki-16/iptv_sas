package com.hgys.iptv.model.bean;

import lombok.Data;

import java.util.List;

/**
 * @ClassName MenuTree
 * @Auther: wangz
 * @Date: 2019/5/16 21:37
 * @Description: TODO
 */
@Data
public class MenuTree {
    private Object id;
    private String text;
    private String status;
    private Object parentId;
    private List<MenuTree> childrens;
    private String iconCls;
    //根结点到叶子的路径名或id为权限单元
}
