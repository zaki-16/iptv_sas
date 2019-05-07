package com.hgys.iptv.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sys_permission")
public class Permission {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, length = 20)
    private Long id;
    //权限名
    @Column(name = "name",length = 200)
    private String name;
    //权限字符，如query，update等
    @Column(name = "permission", unique = true, nullable = false, length = 200)
    private String permission;
    //资源路径
    @Column(name = "uri", length = 100)
    private String uri;

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
