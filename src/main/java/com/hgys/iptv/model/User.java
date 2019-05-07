package com.hgys.iptv.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: wangzhen
 * @Date:2019/4/19 16:26
 */
@Entity
@Table(name="sys_user")
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 20)
    private Long id;
    @Column(name = "username", unique = true, nullable = false, length = 200)
    private String username;
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    @Column(name = "salt", length = 200)
    private String salt;//加密盐

    //用户-角色==多对多，维护关系方
    @ManyToMany(cascade = CascadeType.ALL,fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns ={@JoinColumn(name = "role_id") })
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}