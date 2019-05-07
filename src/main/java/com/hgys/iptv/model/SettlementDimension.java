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

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * 结算单维度表(settlement_dimension)
 * 
 * @author yance
 * @version 1.0.0 2019-05-05
 */
@Entity
@Table(name = "settlement_dimension")
@Data
public class SettlementDimension implements Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 3103784636788260031L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 维度名称 */
    @Column(name = "name", nullable = true, length = 100)
    private String name;

    /** 维度编码 */
    @Column(name = "code", nullable = true, length = 50)
    private String code;

    /** 录入时间 */
    @Column(name = "inputTime", nullable = true, length = 26)
    private Timestamp inputTime;

    /** 修改时间 */
    @Column(name = "modifyTime", nullable = true, length = 26)
    private Timestamp modifyTime;

    /** 状态 */
    @Column(name = "status", nullable = true, length = 10)
    private Integer status;

    /** 备注 */
    @Column(name = "remarks", nullable = true, length = 200)
    private String remarks;

    /** 备用字段1 */
    @Column(name = "col1", nullable = true, length = 100)
    private String col1;

    /** 备用字段2 */
    @Column(name = "col2", nullable = true, length = 100)
    private String col2;

    /** 备用字段3 */
    @Column(name = "col3", nullable = true, length = 100)
    private String col3;

    /** 备用字段4 */
    @Column(name = "col4", nullable = true, length = 100)
    private String col4;

    /** 备用字段5 */
    @Column(name = "col5", nullable = true, length = 100)
    private String col5;

    /** 逻辑删除(0:否；1:是) */
    @Column(name = "isdelete", nullable = true, length = 10)
    private Integer isdelete;
}