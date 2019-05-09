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
 * Cp雨分赃结算关系表(cp_account_settlement)
 * 
 * @author yance
 * @version 1.0.0 2019-05-09
 */
@Entity
@Table(name = "cp_account_settlement")
public class CpAccountSettlement implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 2565493331192690594L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 分账编码 */
    @Column(name = "masterCode", nullable = true, length = 50)
    private String masterCode;

    /** 分账名称 */
    @Column(name = "masterName", nullable = true, length = 100)
    private String masterName;

    /** cp编码 */
    @Column(name = "cpCode", nullable = true, length = 50)
    private String cpCode;

    /** cp名称 */
    @Column(name = "cpName", nullable = true, length = 100)
    private String cpName;

    /** 产品编码 */
    @Column(name = "productCode", nullable = true, length = 50)
    private String productCode;

    /** 产品名称 */
    @Column(name = "productName", nullable = true, length = 100)
    private String productName;

    /** 结算金额 */
    @Column(name = "set_money", nullable = true, length = 10)
    private Integer set_money;

    /** 创建时间 */
    @Column(name = "create_time", nullable = true, length = 26)
    private Timestamp create_time;

    /** 预留字段1 */
    @Column(name = "col1", nullable = true, length = 100)
    private String col1;

    /** 预留字段2 */
    @Column(name = "col2", nullable = true, length = 100)
    private String col2;

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
     * 获取分账编码
     * 
     * @return 分账编码
     */
    public String getMasterCode() {
        return this.masterCode;
    }

    /**
     * 设置分账编码
     * 
     * @param masterCode
     *          分账编码
     */
    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    /**
     * 获取分账名称
     * 
     * @return 分账名称
     */
    public String getMasterName() {
        return this.masterName;
    }

    /**
     * 设置分账名称
     * 
     * @param masterName
     *          分账名称
     */
    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    /**
     * 获取cp编码
     * 
     * @return cp编码
     */
    public String getCpCode() {
        return this.cpCode;
    }

    /**
     * 设置cp编码
     * 
     * @param cpCode
     *          cp编码
     */
    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    /**
     * 获取cp名称
     * 
     * @return cp名称
     */
    public String getCpName() {
        return this.cpName;
    }

    /**
     * 设置cp名称
     * 
     * @param cpName
     *          cp名称
     */
    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    /**
     * 获取产品编码
     * 
     * @return 产品编码
     */
    public String getProductCode() {
        return this.productCode;
    }

    /**
     * 设置产品编码
     * 
     * @param productCode
     *          产品编码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取产品名称
     * 
     * @return 产品名称
     */
    public String getProductName() {
        return this.productName;
    }

    /**
     * 设置产品名称
     * 
     * @param productName
     *          产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取结算金额
     * 
     * @return 结算金额
     */
    public Integer getSet_money() {
        return this.set_money;
    }

    /**
     * 设置结算金额
     * 
     * @param set_money
     *          结算金额
     */
    public void setSet_money(Integer set_money) {
        this.set_money = set_money;
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
     *          创建时间
     */
    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    /**
     * 获取预留字段1
     * 
     * @return 预留字段1
     */
    public String getCol1() {
        return this.col1;
    }

    /**
     * 设置预留字段1
     * 
     * @param col1
     *          预留字段1
     */
    public void setCol1(String col1) {
        this.col1 = col1;
    }

    /**
     * 获取预留字段2
     * 
     * @return 预留字段2
     */
    public String getCol2() {
        return this.col2;
    }

    /**
     * 设置预留字段2
     * 
     * @param col2
     *          预留字段2
     */
    public void setCol2(String col2) {
        this.col2 = col2;
    }
}