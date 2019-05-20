package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName SysUserRole
 * @Auther: wangz
 * @Date: 2019/5/16 19:05
 * @Description: TODO
 */
@Entity
@Table(name="sys_user_role")
@Data
public class SysUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    @Column(name = "user_id",nullable = false, length = 11)
    private Integer userId;

    @Column(name = "role_id", nullable = false, length = 11)
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
