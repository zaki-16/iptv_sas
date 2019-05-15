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
 * 分账结算业务定比例结算源数据(settlement_business)
 *
 * @author yance
 * @version 1.0.0 2019-05-15
 */
@Entity
@Table(name = "settlement_business")
public class SettlementBusiness implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 595823469007935700L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 分账结算编码 */
    @Column(name = "masterCode", nullable = true, length = 50)
    private String masterCode;

    /** 1:比例结算；2:金额结算 */
    @Column(name = "type", nullable = true, length = 10)
    private Integer type;

    /** 业务编码 */
    @Column(name = "businessCode", nullable = true, length = 50)
    private String businessCode;

    /** 业务名称 */
    @Column(name = "businessName", nullable = true, length = 100)
    private String businessName;

    /** 业务收入 */
    @Column(name = "businessMoney", nullable = true, length = 12)
    private BigDecimal businessMoney;

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
     * 获取1:比例结算；2:金额结算
     *
     * @return 1:比例结算；2:金额结算
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * 设置1:比例结算；2:金额结算
     *
     * @param type
     *          1:比例结算；2:金额结算
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取业务编码
     *
     * @return 业务编码
     */
    public String getBusinessCode() {
        return this.businessCode;
    }

    /**
     * 设置业务编码
     *
     * @param businessCode
     *          业务编码
     */
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    /**
     * 获取业务名称
     *
     * @return 业务名称
     */
    public String getBusinessName() {
        return this.businessName;
    }

    /**
     * 设置业务名称
     *
     * @param businessName
     *          业务名称
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * 获取业务收入
     *
     * @return 业务收入
     */
    public BigDecimal getBusinessMoney() {
        return this.businessMoney;
    }

    /**
     * 设置业务收入
     *
     * @param businessMoney
     *          业务收入
     */
    public void setBusinessMoney(BigDecimal businessMoney) {
        this.businessMoney = businessMoney;
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
