package com.hgys.iptv.controller.vm;

import com.hgys.iptv.model.Authority;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SysMenuVM
 * @Auther: wangz
 * @Date: 2019/5/24 19:32
 * @Description: TODO
 */
@Data
public class SysMenuVM {
    private Integer id;
    private String name;//
    private String text;//菜单展示名
//    private String navigateUrl;//
    private String icon;//图标
    private Integer parentId;//父节点
    //    private Integer level;//层级
//    private Integer sequence;//序号
//    private Timestamp createTime;
    private Integer status;

    private List<Authority> list;
}
