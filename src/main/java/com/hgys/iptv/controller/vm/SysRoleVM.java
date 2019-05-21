package com.hgys.iptv.controller.vm;

import com.hgys.iptv.model.Authority;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName SysRoleVM
 * @Auther: wangz
 * @Date: 2019/5/20 18:52
 * @Description: TODO
 */
@Data
public class SysRoleVM {

    private Integer id;

    private String name;

    //角色描述
    private String description;

    private Integer status;//0:启用，1：禁用

    private Timestamp createdTime;

    private Timestamp modifyTime;

    private List<Authority> list;

}
