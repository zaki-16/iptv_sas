package com.hgys.iptv.controller.vm;

import com.hgys.iptv.model.Permission;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SysMenuListVM
 * @Auther: wangz
 * @Date: 2019/5/19 14:50
 * @Description: TODO
 */
@Data
public class SysMenuListVM {

    private Integer id;
    private String name;
    private String text;
    private String navigateUrl;//
    private String icon;//图标
    private Integer parentId;//父节点
    //    private Integer level;//层级
//    private Integer sequence;//序号
//    private Timestamp createTime;
    private Integer status;

    private List<Permission> list;
}
