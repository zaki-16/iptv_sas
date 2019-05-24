package com.hgys.iptv.model.bean;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

/**
 * @ClassName PermissionNode--权限树节点
 * @Auther: wangz
 * @Date: 2019/5/23 19:57
 * @Description: TODO
 */
@Data
public class PermissionNode {
    private Integer id;

    private Integer parentId;//权限父节点

    private String description;
    //权限字符，如query，update等
    private String name;

    private Integer status;//0:启用，1：禁用

    private List<PermissionNode> childrens;
}
