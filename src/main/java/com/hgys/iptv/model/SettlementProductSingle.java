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
 * 分账结算产品级单维度结算源数据表(settlement_product_single)
 *
 * @author yance
 * @version 1.0.0 2019-05-15
 */
@Entity
@Table(name = "settlement_product_single")
public class SettlementProductSingle implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -6592741993407142020L;

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

    /** 维度编码 */
    @Column(name = "dimCode", nullable = true, length = 50)
    private String dimCode;

    /** 维度名称 */
    @Column(name = "dimName", nullable = true, length = 100)
    private String dimName;

    /** 结算金额 */
    @Column(name = "setMoney", nullable = true, length = 12)
    private BigDecimal setMoney;

    /** 创建时间 */
    @Column(name = "createTime", nullable = true, length = 26)
    private Timestamp createTime;

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
     * 获取维度编码
     *
     * @return 维度编码
     */
    public String getDimCode() {
        return this.dimCode;
    }

    /**
     * 设置维度编码
     *
     * @param dimCode
     *          维度编码
     */
    public void setDimCode(String dimCode) {
        this.dimCode = dimCode;
    }

    /**
     * 获取维度名称
     *
     * @return 维度名称
     */
    public String getDimName() {
        return this.dimName;
    }

    /**
     * 设置维度名称
     *
     * @param dimName
     *          维度名称
     */
    public void setDimName(String dimName) {
        this.dimName = dimName;
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
}
