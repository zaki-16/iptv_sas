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
@Data
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


}