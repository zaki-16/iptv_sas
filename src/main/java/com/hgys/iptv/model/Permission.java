package com.hgys.iptv.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sys_permission")
public class Permission {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //权限名
    private String name;
    //权限字符，如query，update等
    private String permission;
    //资源路径
    private String url;

    //角色-权限==多对多，被维护
    @ManyToMany()
    @JoinTable(name="sys_role_permission",joinColumns = {@JoinColumn(name="permission_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
