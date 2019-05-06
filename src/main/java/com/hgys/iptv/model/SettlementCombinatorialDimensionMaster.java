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
 * 结算组合维度主表(settlement_combinatorial_dimension_master)
 * 
 * @author yance
 * @version 1.0.0 2019-05-06
 */
@Entity
@Table(name = "settlement_combinatorial_dimension_master")
public class SettlementCombinatorialDimensionMaster implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -3404644451092063028L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 维度编码 */
    @Column(name = "code", nullable = true, length = 50)
    private String code;

    /** 维度名称 */
    @Column(name = "name", nullable = true, length = 100)
    private String name;

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
    @Column(name = "remakes", nullable = true, length = 200)
    private String remakes;

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
    public String getRemakes() {
        return this.remakes;
    }

    /**
     * 设置备注
     * 
     * @param remakes
     *          备注
     */
    public void setRemakes(String remakes) {
        this.remakes = remakes;
    }

    /**
     * 获取逻辑删除(0:否；1:是)
     * 
     * @return 逻辑删除(0
     */
    public Integer getIsdelete() {
        return this.isdelete;
    }

    /**
     * 设置逻辑删除(0:否；1:是)
     * 
     * @param isdelete
     *          逻辑删除(0
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}