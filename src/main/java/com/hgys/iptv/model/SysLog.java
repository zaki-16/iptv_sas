package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName SysLog
 * @Auther: wangz
 * @Date: 2019/5/17 10:49
 * @Description: TODO
 */
@Data
@Entity
@Table(name="sys_log")
public class SysLog implements Serializable {
    private static final long serialVersionUID = -2595531394821445881L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;
    //     * 登录名称
    private String loginName;
    //     * 真实姓名
    private String realName;
    //     * 类型:登录 or 注销
    private String type;
    //     * 结果
    private String result;
    //     * 时间
    private Timestamp time;

}
