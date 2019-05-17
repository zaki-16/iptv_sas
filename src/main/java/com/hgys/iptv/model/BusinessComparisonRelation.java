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


/**
 * 结算类型-业务定比例与业务关系表(business_comparison_relation)
 *
 * @author yance
 * @version 1.0.0 2019-05-10
 */
@Entity
@Table(name = "business_comparison_relation")
public class BusinessComparisonRelation implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -5332672880234197356L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    @Column(name = "code", unique = true, nullable = false, length = 10)
    private String code;

    /** 业务定比列编码 */
    @Column(name = "masterCode", nullable = true, length = 50)
    private String masterCode;

    /** 业务定比例名称 */
    @Column(name = "masterName", nullable = true)
    private String masterName;

    /** 业务编码 */
    @Column(name = "businessCode", nullable = true, length = 50)
    private String businessCode;

    /** 业务名称 */
    @Column(name = "businessName", nullable = true, length = 100)
    private String businessName;

    /** 创建时间 */
    @Column(name = "create_time", nullable = true, length = 16)
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
     * 获取业务定比列编码
     *
     * @return 业务定比列编码
     */
    public String getMasterCode() {
        return this.masterCode;
    }

    /**
     * 设置业务定比列编码
     *
     * @param masterCode
     *          业务定比列编码
     */
    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    /**
     * 获取业务定比例名称
     *
     * @return 业务定比例名称
     */
    public String getMasterName() {
        return this.masterName;
    }

    /**
     * 设置业务定比例名称
     *
     * @param masterName
     *          业务定比例名称
     */
    public void setMasterName(String masterName) {
        this.masterName = masterName;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
