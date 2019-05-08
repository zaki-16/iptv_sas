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
 * 结算类型-业务定比例表（合同基于业务收入）(order_business_comparison)
 * 
 * @author yance
 * @version 1.0.0 2019-05-07
 */
@Entity
@Table(name = "order_business_comparison")
public class OrderBusinessComparison implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -1943300429611991342L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 名称 */
    @Column(name = "name", nullable = true, length = 100)
    private String name;

    /** 编码 */
    @Column(name = "code", nullable = true, length = 50)
    private String code;

    /** 结算方式(1:按比例结算;2:按金额结算) */
    @Column(name = "mode", nullable = true, length = 10)
    private Integer mode;

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

    /** 业务编码 */
    @Column(name = "business_code", nullable = true, length = 50)
    private String businessCode;

    /** 业务名称 */
    @Column(name = "business_name", nullable = true, length = 50)
    private String businessName;

    /** 逻辑删除标记 */
    @Column(name = "isdelete", nullable = true, length = 10)
    private Integer isdelete;

    /** 备用字段 */
    @Column(name = "col1", nullable = true, length = 100)
    private String col1;

    /** 备用字段 */
    @Column(name = "col2", nullable = true, length = 100)
    private String col2;

    /** 备用字段 */
    @Column(name = "col3", nullable = true, length = 100)
    private String col3;

    /** 备用字段 */
    @Column(name = "col4", nullable = true, length = 100)
    private String col4;

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
     * 获取名称
     * 
     * @return 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置名称
     * 
     * @param name
     *          名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取编码
     * 
     * @return 编码
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置编码
     * 
     * @param code
     *          编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取结算方式(1:按比例结算;2:按金额结算)
     * 
     * @return 结算方式(1
     */
    public Integer getMode() {
        return this.mode;
    }

    /**
     * 设置结算方式(1:按比例结算;2:按金额结算)
     * 
     * @param mode
     *          结算方式(1
     */
    public void setMode(Integer mode) {
        this.mode = mode;
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
     * 获取逻辑删除标记
     * 
     * @return 逻辑删除标记
     */
    public Integer getIsdelete() {
        return this.isdelete;
    }

    /**
     * 设置逻辑删除标记
     * 
     * @param isdelete
     *          逻辑删除标记
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * 获取备用字段
     * 
     * @return 备用字段
     */
    public String getCol1() {
        return this.col1;
    }

    /**
     * 设置备用字段
     * 
     * @param col1
     *          备用字段
     */
    public void setCol1(String col1) {
        this.col1 = col1;
    }

    /**
     * 获取备用字段
     * 
     * @return 备用字段
     */
    public String getCol2() {
        return this.col2;
    }

    /**
     * 设置备用字段
     * 
     * @param col2
     *          备用字段
     */
    public void setCol2(String col2) {
        this.col2 = col2;
    }

    /**
     * 获取备用字段
     * 
     * @return 备用字段
     */
    public String getCol3() {
        return this.col3;
    }

    /**
     * 设置备用字段
     * 
     * @param col3
     *          备用字段
     */
    public void setCol3(String col3) {
        this.col3 = col3;
    }

    /**
     * 获取备用字段
     * 
     * @return 备用字段
     */
    public String getCol4() {
        return this.col4;
    }

    /**
     * 设置备用字段
     * 
     * @param col4
     *          备用字段
     */
    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}