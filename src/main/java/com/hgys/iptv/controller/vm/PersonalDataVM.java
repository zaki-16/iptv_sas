package com.hgys.iptv.controller.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hgys.iptv.model.Role;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName PersonalDataVM
 * @Auther: wangz
 * @Date: 2019/5/25 01:04
 * @Description: TODO
 */
@Data
public class PersonalDataVM {

//    private Integer id;

    private String username;//登录名

    private String realName;

    private Integer cpId;//-1=平台用户

    private String cpAbbr;
//    @JsonIgnore
//    private String password;

    private String email;

    private String telephone;//电话

    private String mobilePhone;//手机号

    private Timestamp createdTime;

    private Timestamp modifyTime;

    private Integer status;//0:启用，1：禁用

    private Integer isdelete;//0：未删除 1：已删除

    private List<PersonalRole> list;

}
