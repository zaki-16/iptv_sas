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

import org.hibernate.annotations.GenericGenerator;

/**
 * 结算单维度表(settlement_dimension)
 * 
 * @author yance
 * @version 1.0.0 2019-05-05
 */
@Entity
@Table(name = "settlement_dimension")
public class SettlementDimension implements java.io.Serializable {
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

    /**
     * 获取主键
     * 
     * @return 主键
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置主键
     * 
     * @param id
     *          主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取维度名称
     * 
     * @return 维度名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置维度名称
     * 
     * @param name
     *          维度名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取维度编码
     * 
     * @return 维度编码
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置维度编码
     * 
     * @param code
     *          维度编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取录入时间
     * 
     * @return 录入时间
     */
    public Timestamp getInputTime() {
        return this.inputTime;
    }

    /**
     * 设置录入时间
     * 
     * @param inputTime
     *          录入时间
     */
    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    /**
     * 获取修改时间
     * 
     * @return 修改时间
     */
    public Timestamp getModifyTime() {
        return this.modifyTime;
    }

    /**
     * 设置修改时间
     * 
     * @param modifyTime
     *          修改时间
     */
    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取状态
     * 
     * @return 状态
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态
     * 
     * @param status
     *          状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     * 
     * @return 备注
     */
    public String getRemarks() {
        return this.remarks;
    }

    /**
     * 设置备注
     * 
     * @param remarks
     *          备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取备用字段1
     * 
     * @return 备用字段1
     */
    public String getCol1() {
        return this.col1;
    }

    /**
     * 设置备用字段1
     * 
     * @param col1
     *          备用字段1
     */
    public void setCol1(String col1) {
        this.col1 = col1;
    }

    /**
     * 获取备用字段2
     * 
     * @return 备用字段2
     */
    public String getCol2() {
        return this.col2;
    }

    /**
     * 设置备用字段2
     * 
     * @param col2
     *          备用字段2
     */
    public void setCol2(String col2) {
        this.col2 = col2;
    }

    /**
     * 获取备用字段3
     * 
     * @return 备用字段3
     */
    public String getCol3() {
        return this.col3;
    }

    /**
     * 设置备用字段3
     * 
     * @param col3
     *          备用字段3
     */
    public void setCol3(String col3) {
        this.col3 = col3;
    }

    /**
     * 获取备用字段4
     * 
     * @return 备用字段4
     */
    public String getCol4() {
        return this.col4;
    }

    /**
     * 设置备用字段4
     * 
     * @param col4
     *          备用字段4
     */
    public void setCol4(String col4) {
        this.col4 = col4;
    }

    /**
     * 获取备用字段5
     * 
     * @return 备用字段5
     */
    public String getCol5() {
        return this.col5;
    }

    /**
     * 设置备用字段5
     * 
     * @param col5
     *          备用字段5
     */
    public void setCol5(String col5) {
        this.col5 = col5;
    }

    /**
     * 获取逻辑删除(0:否;1:是)
     *
     * @return 逻辑删除(0
     */
    public Integer getIsdelete() {
        return this.isdelete;
    }

    /**
     * 设置逻辑删除(0:否;1:是)
     *
     * @param isdelete
     *          逻辑删除(0
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}