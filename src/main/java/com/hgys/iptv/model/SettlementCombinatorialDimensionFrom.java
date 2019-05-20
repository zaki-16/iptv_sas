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

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 结算组合维度从表(settlement_combinatorial_dimension_from)
 * 
 * @author yance
 * @version 1.0.0 2019-05-06
 */
@Entity
@Table(name = "settlement_combinatorial_dimension_from")
public class SettlementCombinatorialDimensionFrom implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -6611014417317366936L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 结算组合维度主表code */
    @Column(name = "master_code", nullable = false, length = 50)
    private String master_code;

    /** 维度编码 */
    @Column(name = "dim_code", nullable = true, length = 50)
    private String dim_code;

    /** 维度名称 */
    @Column(name = "dim_name", nullable = true, length = 100)
    private String dim_name;

    /** 权重 */
    @Column(name = "weight", nullable = true, length = 11)
    private Integer weight;

    /** 逻辑删除(0:否;1:是) */
    @Column(name = "isdelete", nullable = true, length = 10)
    private Integer isdelete;

    /** 创建时间 */
    @Column(name = "create_time", nullable = true, length = 26)
    private Timestamp create_time;

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
     * 获取结算组合维度主表code
     * 
     * @return 结算组合维度主表code
     */
    public String getMaster_code() {
        return this.master_code;
    }

    /**
     * 设置结算组合维度主表code
     * 
     * @param master_code
     *          结算组合维度主表code
     */
    public void setMaster_code(String master_code) {
        this.master_code = master_code;
    }

    /**
     * 获取维度编码
     * 
     * @return 维度编码
     */
    public String getDim_code() {
        return this.dim_code;
    }

    /**
     * 设置维度编码
     * 
     * @param dim_code
     *          维度编码
     */
    public void setDim_code(String dim_code) {
        this.dim_code = dim_code;
    }

    /**
     * 获取维度名称
     * 
     * @return 维度名称
     */
    public String getDim_name() {
        return this.dim_name;
    }

    /**
     * 设置维度名称
     * 
     * @param dim_name
     *          维度名称
     */
    public void setDim_name(String dim_name) {
        this.dim_name = dim_name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Timestamp getCreate_time() {
        return this.create_time;
    }

    /**
     * 设置创建时间
     * 
     * @param create_time
     *   创建时间
     */
    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}