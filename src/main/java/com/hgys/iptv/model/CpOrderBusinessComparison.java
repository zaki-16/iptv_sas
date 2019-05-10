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
 * 结算类型-业务定比例,结算规则表(cp_order_business_comparison)
 * 
 * @author yance
 * @version 1.0.0 2019-05-07
 */
@Entity
@Table(name = "cp_order_business_comparison")
public class CpOrderBusinessComparison implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -4164364684165511360L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 业务编码 */
    @Column(name = "masterCode", nullable = true, length = 50)
    private String masterCode;

    /** cp编码 */
    @Column(name = "cp_code", nullable = true, length = 50)
    private String cp_code;

    /** cp名称 */
    @Column(name = "cp_name", nullable = true, length = 100)
    private String cp_name;

    /** 所占比例 */
    @Column(name = "proportion", nullable = true, length = 10)
    private Integer proportion;

    /** 金额 */
    @Column(name = "money", nullable = true, length = 10)
    private Integer money;

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
     * 获取业务定比例比编码
     * 
     * @return 业务定比例比编码
     */
    public String getMasterCode() {
        return this.masterCode;
    }

    /**
     * 设置业务定比例比编码
     * 
     * @param masterCode
     *          业务定比例比编码
     */
    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    /**
     * 获取cp编码
     * 
     * @return cp编码
     */
    public String getCp_code() {
        return this.cp_code;
    }

    /**
     * 设置cp编码
     * 
     * @param cp_code
     *          cp编码
     */
    public void setCp_code(String cp_code) {
        this.cp_code = cp_code;
    }

    /**
     * 获取cp名称
     * 
     * @return cp名称
     */
    public String getCp_name() {
        return this.cp_name;
    }

    /**
     * 设置cp名称
     * 
     * @param cp_name
     *          cp名称
     */
    public void setCp_name(String cp_name) {
        this.cp_name = cp_name;
    }

    /**
     * 获取所占比例
     * 
     * @return 所占比例
     */
    public Integer getProportion() {
        return this.proportion;
    }

    /**
     * 设置所占比例
     * 
     * @param proportion
     *          所占比例
     */
    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    /**
     * 获取金额
     * 
     * @return 金额
     */
    public Integer getMoney() {
        return this.money;
    }

    /**
     * 设置金额
     * 
     * @param money
     *          金额
     */
    public void setMoney(Integer money) {
        this.money = money;
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
}