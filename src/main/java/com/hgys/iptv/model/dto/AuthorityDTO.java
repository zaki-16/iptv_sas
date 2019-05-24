package com.hgys.iptv.model.dto;

import lombok.Data;

/**
 * @ClassName AuthorityDTO
 * @Auther: wangz
 * @Date: 2019/5/20 09:24
 * @Description: TODO
 */
@Data
public class AuthorityDTO {
    private Integer id;

//    private String name;// name = menuName+":"+permName;

    //菜单 id
    private Integer menuId;

    private String name;
//    //菜单 名称
//    private String menuName;
//    // 权限 id
//    private Integer permId;
//    // 权限 名称
//    private String permName;

    private Integer status;//0:启用，1：禁用

}
