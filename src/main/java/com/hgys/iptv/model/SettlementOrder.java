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
 * 分账结算订购量结算源数据表(settlement_order)
 *
 * @author yance
 * @version 1.0.0 2019-05-15
 */
@Entity
@Table(name = "settlement_order")
public class SettlementOrder implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 5099991739118841955L;

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

    /** 订购量 */
    @Column(name = "orderQuantity", nullable = true, length = 12)
    private BigDecimal orderQuantity;

    /** 总收入 */
    @Column(name = "orderMoney", nullable = true, length = 12)
    private BigDecimal orderMoney;

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
     * 获取订购量
     *
     * @return 订购量
     */
    public BigDecimal getOrderQuantity() {
        return this.orderQuantity;
    }

    /**
     * 设置订购量
     *
     * @param orderQuantity
     *          订购量
     */
    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
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

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }
}
