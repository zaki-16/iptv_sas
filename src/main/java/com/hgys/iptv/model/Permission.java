package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name="sys_permission")
@Data
public class Permission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;
    //权限描述
    @Column(name = "description",length = 50)
    private String description;
    //权限字符，如query，update等
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "status",length = 2)
    private Integer status;//0:启用，1：禁用

    private Timestamp createdTime;

    private Timestamp modifyTime;

//    //资源路径--
//    @Column(name = "uri", length = 100)
//    private String uri;

//    //角色-权限==多对多，被维护
//    @ManyToMany(mappedBy = "permissions")
////    @JoinTable(name="sys_role_permission",joinColumns = {@JoinColumn(name="permission_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
//    private Set<Role> roles = new HashSet<>();

}
