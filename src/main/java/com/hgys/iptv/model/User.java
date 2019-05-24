package com.hgys.iptv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * @Author: wangzhen
 * @Date:2019/4/19 16:26
 */
@Entity
@Table(name="sys_user")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
public class User implements Serializable, UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, length = 200)
    private String username;//登录名

    @Column(name = "realName", length = 200)//用户真实姓名
    private String realName;

    private Integer cpId;//-1=平台用户

    private String cpAbbr;

    @Column(name = "password", nullable = false, length = 200)
    @JsonIgnore
    private String password;

    private String email;

    private String telephone;//电话

    private String mobilePhone;//手机号

    private Timestamp createdTime;

    private Timestamp modifyTime;

    @Column(name = "status",length = 2)
    private Integer status;//0:启用，1：禁用

    private Integer isdelete;//0：未删除 1：已删除

//    @ElementCollection(targetClass = SystemUserRole.class, fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
    //用户-角色==多对多，维护关系方
//    @ManyToMany(cascade = CascadeType.PERSIST,fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
//    @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns ={@JoinColumn(name = "role_id") })
//    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

//    public void setPassword(String password) {
//        this.password = PASSWORD_ENCODER.encode(password);
//    }


}
