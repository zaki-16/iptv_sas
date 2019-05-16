package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="sys_role")
@Data
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    //角色描述
    @Column(name = "description",length = 200)
    private String description;

    @Column(name = "status",length = 2)
    private Integer status;//0:启用，1：禁用

    private Timestamp createdTime;

    private Timestamp modifyTime;

//    //用户-角色==多对多，被维护
//    @ManyToMany(mappedBy="roles")
////    @JoinTable(name="sys_user_role",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="user_id")})
//    private Set<User> users = new HashSet<>();;
//
//    //角色-权限==多对多，，维护关系方
//    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
//    @JoinTable(name="sys_role_permission",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="permission_id")})
//    private List<Permission> permissions = new ArrayList<>();
}