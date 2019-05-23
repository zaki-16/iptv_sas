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

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 分账结算产品级多维度结算源数据表(settlement_product_many)
 *
 * @author yance
 * @version 1.0.0 2019-05-15
 */
@Entity
@Table(name = "settlement_product_many")
public class SettlementProductMany implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -6465767121730170190L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 分账结算编码 */
    @Column(name = "masterCode", nullable = true, length = 50)
    private String masterCode;

    /** cp编码 */
    @Column(name = "cpcode", nullable = true, length = 50)
    private String cpcode;

    /** cp名称 */
    @Column(name = "cpname", nullable = true, length = 100)
    private String cpname;

    /** 产品编码 */
    @Column(name = "productCode", nullable = true, length = 50)
    private String productCode;

    /** 产品名称 */
    @Column(name = "productName", nullable = true, length = 100)
    private String productName;

    /** 维度A编码 */
    @Column(name = "dimacode", nullable = true, length = 50)
    private String dimACode;

    /** 维度A名称 */
    @Column(name = "dimaname", nullable = true, length = 100)
    private String dimAName;

    /** 结算金额 */
    @Column(name = "setMoney", nullable = true, length = 12)
    private BigDecimal setMoney;

    /** E数量 */
    @Column(name = "numberE", nullable = true, length = 12)
    private BigDecimal numberE;

    /** A数量 */
    @Column(name = "numberA", nullable = true, length = 12)
    private BigDecimal numberA;

    /** B数量 */
    @Column(name = "numberB", nullable = true, length = 12)
    private BigDecimal numberB;

    /** C数量 */
    @Column(name = "numberC", nullable = true, length = 12)

    private BigDecimal numberC;

    /** D数量 */
    @Column(name = "numberD", nullable = true, length = 12)
    private BigDecimal numberD;

    /** 创建时间 */
    @Column(name = "createTime", nullable = true, length = 26)
    private Timestamp createTime;

    /** 维度B编码 */
    @Column(name = "dimbcode", nullable = true, length = 50)
    private String dimBCode;

    /** 维度B名称 */
    @Column(name = "dimbname", nullable = true, length = 100)
    private String dimBName;

    /** 维度C编码 */
    @Column(name = "dimccode", nullable = true, length = 50)
    private String dimCCode;

    /** 维度C名称 */
    @Column(name = "dimcname", nullable = true, length = 100)
    private String dimCName;

    /** 维度D编码 */
    @Column(name = "dimdcode", nullable = true, length = 50)
    private String dimDCode;

    /** 维度D名称 */
    @Column(name = "dimdname", nullable = true, length = 100)
    private String dimDName;

    /** 维度E编码 */
    @Column(name = "dimecode", nullable = true, length = 50)
    private String dimECode;

    /** 维度E名称 */
    @Column(name = "dimename", nullable = true, length = 100)
    private String dimEName;

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
     * 获取分账结算编码
     *
     * @return 分账结算编码
     */
    public String getMasterCode() {
        return this.masterCode;
    }

    /**
     * 设置分账结算编码
     *
     * @param masterCode
     *          分账结算编码
     */
    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    /**
     * 获取cp编码
     *
     * @return cp编码
     */
    public String getCpcode() {
        return this.cpcode;
    }

    /**
     * 设置cp编码
     *
     * @param cpcode
     *          cp编码
     */
    public void setCpcode(String cpcode) {
        this.cpcode = cpcode;
    }

    /**
     * 获取cp名称
     *
     * @return cp名称
     */
    public String getCpname() {
        return this.cpname;
    }

    /**
     * 设置cp名称
     *
     * @param cpname
     *          cp名称
     */
    public void setCpname(String cpname) {
        this.cpname = cpname;
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
     * 获取维度A编码
     *
     * @return 维度A编码
     */
    public String getDimACode() {
        return this.dimACode;
    }

    /**
     * 设置维度A编码
     *
     * @param dimACode
     *          维度A编码
     */
    public void setDimACode(String dimACode) {
        this.dimACode = dimACode;
    }

    /**
     * 获取维度A名称
     *
     * @return 维度A名称
     */
    public String getDimAName() {
        return this.dimAName;
    }

    /**
     * 设置维度A名称
     *
     * @param dimAName
     *          维度A名称
     */
    public void setDimAName(String dimAName) {
        this.dimAName = dimAName;
    }

    /**
     * 获取结算金额
     *
     * @return 结算金额
     */
    public BigDecimal getSetMoney() {
        return this.setMoney;
    }

    /**
     * 设置结算金额
     *
     * @param setMoney
     *          结算金额
     */
    public void setSetMoney(BigDecimal setMoney) {
        this.setMoney = setMoney;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime
     *          创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取维度B编码
     *
     * @return 维度B编码
     */
    public String getDimBCode() {
        return this.dimBCode;
    }

    /**
     * 设置维度B编码
     *
     * @param dimBCode
     *          维度B编码
     */
    public void setDimBCode(String dimBCode) {
        this.dimBCode = dimBCode;
    }

    /**
     * 获取维度B名称
     *
     * @return 维度B名称
     */
    public String getDimBName() {
        return this.dimBName;
    }

    /**
     * 设置维度B名称
     *
     * @param dimBName
     *          维度B名称
     */
    public void setDimBName(String dimBName) {
        this.dimBName = dimBName;
    }

    /**
     * 获取维度C编码
     *
     * @return 维度C编码
     */
    public String getDimCCode() {
        return this.dimCCode;
    }

    /**
     * 设置维度C编码
     *
     * @param dimCCode
     *          维度C编码
     */
    public void setDimCCode(String dimCCode) {
        this.dimCCode = dimCCode;
    }

    /**
     * 获取维度C名称
     *
     * @return 维度C名称
     */
    public String getDimCName() {
        return this.dimCName;
    }

    /**
     * 设置维度C名称
     *
     * @param dimCName
     *          维度C名称
     */
    public void setDimCName(String dimCName) {
        this.dimCName = dimCName;
    }

    /**
     * 获取维度D编码
     *
     * @return 维度D编码
     */
    public String getDimDCode() {
        return this.dimDCode;
    }

    /**
     * 设置维度D编码
     *
     * @param dimDCode
     *          维度D编码
     */
    public void setDimDCode(String dimDCode) {
        this.dimDCode = dimDCode;
    }

    /**
     * 获取维度D名称
     *
     * @return 维度D名称
     */
    public String getDimDName() {
        return this.dimDName;
    }

    /**
     * 设置维度D名称
     *
     * @param dimDName
     *          维度D名称
     */
    public void setDimDName(String dimDName) {
        this.dimDName = dimDName;
    }

    /**
     * 获取维度E编码
     *
     * @return 维度E编码
     */
    public String getDimECode() {
        return this.dimECode;
    }

    /**
     * 设置维度E编码
     *
     * @param dimECode
     *          维度E编码
     */
    public void setDimECode(String dimECode) {
        this.dimECode = dimECode;
    }

    /**
     * 获取维度E名称
     *
     * @return 维度E名称
     */
    public String getDimEName() {
        return this.dimEName;
    }

    /**
     * 设置维度E名称
     *
     * @param dimEName
     *          维度E名称
     */
    public void setDimEName(String dimEName) {
        this.dimEName = dimEName;
    }

    public BigDecimal getNumberE() {
        return numberE;
    }

    public void setNumberE(BigDecimal numberE) {
        this.numberE = numberE;
    }

    public BigDecimal getNumberA() {
        return numberA;
    }

    public void setNumberA(BigDecimal numberA) {
        this.numberA = numberA;
    }

    public BigDecimal getNumberB() {
        return numberB;
    }

    public void setNumberB(BigDecimal numberB) {
        this.numberB = numberB;
    }

    public BigDecimal getNumberC() {
        return numberC;
    }

    public void setNumberC(BigDecimal numberC) {
        this.numberC = numberC;
    }

    public BigDecimal getNumberD() {
        return numberD;
    }

    public void setNumberD(BigDecimal numberD) {
        this.numberD = numberD;
    }
}
