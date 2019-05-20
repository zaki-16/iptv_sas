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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
