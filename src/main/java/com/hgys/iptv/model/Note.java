/*
 * Welcome to use the TableGo Tools.
 *
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 *
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.8.8
 */

package com.hgys.iptv.model;

import java.sql.Timestamp;
import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 * 备注模板表(note)
 *
 * @author yance
 * @version 1.0.0 2019-05-09
 */
@Entity
@Table(name = "note")
public class Note implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -3204442735607163305L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 内容 */
    @Column(name = "content", nullable = true, length = 200)
    private String content;

    /** 用户ID(0表示所有人都可用) */
    @Column(name = "userId", nullable = true, length = 255)
    private Integer userId;

    /** 类型 */
    @Column(name = "note_type", nullable = true, length = 10)
    private Integer note_type;

    /** 创建时间 */
    @Column(name = "create_time", nullable = true, length = 26)
    private Timestamp create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNote_type() {
        return note_type;
    }

    public void setNote_type(Integer note_type) {
        this.note_type = note_type;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
