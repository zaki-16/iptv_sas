package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName SysRoleAuthority
 * @Auther: wangz
 * @Date: 2019/5/20 10:19
 * @Description: TODO
 */
@Entity
@Table(name="sys_role_authority")
@Data
public class SysRoleAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;
    @Column(name = "role_id", nullable = false, length = 11)
    private Integer roleId;
    @Column(name = "authId",nullable = false, length = 11)
    private Integer authId;
}
