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
 * 结算类型-产品级,结算规则表(order_product_scd)
 * 
 * @author yance
 * @version 1.0.0 2019-05-07
 */
@Entity
@Table(name = "order_product_scd")
public class OrderProductWithSCD implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -6175682938379576515L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 单维度名称 */
    @Column(name = "sdname", nullable = true, length = 100)
    private String sdname;

    /** 单维度编码 */
    @Column(name = "sdcode", nullable = true, length = 50)
    private String sdcode;

    /** 产品名称 */
    @Column(name = "pname", nullable = true, length = 100)
    private String pname;

    /** 产品编码 */
    @Column(name = "pcode", nullable = true, length = 50)
    private String pcode;

    /** 结算类型-产品级名称 */
    @Column(name = "opname", nullable = true, length = 100)
    private String opname;

    /** 测试 */
    @Column(name = "masterCode", nullable = true, length = 100)
    private String masterCode;

    /** 结算类型-产品级编码 */
    @Column(name = "opcode", nullable = true, length = 50)
    private String opcode;

    /** 多维度名称 */
    @Column(name = "scdname", nullable = true, length = 100)
    private String scdname;

    /** 多维度编码 */
    @Column(name = "scdcode", nullable = true, length = 50)
    private String scdcode;


    /** 创建时间 */
    @Column(name = "createtime", nullable = true, length = 6)
    private Timestamp createtime;

    public Integer getId() {
        return id;
    }

    public String getSdname() {
        return sdname;
    }

    public String getSdcode() {
        return sdcode;
    }

    public String getPname() {
        return pname;
    }

    public String getPcode() {
        return pcode;
    }

    public String getOpname() {
        return opname;
    }

    public String getOpcode() {
        return opcode;
    }

    public String getScdname() {
        return scdname;
    }

    public String getScdcode() {
        return scdcode;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public void setSdname(String sdname) {
        this.sdname = sdname;
    }

    public void setSdcode(String sdcode) {
        this.sdcode = sdcode;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public void setOpname(String opname) {
        this.opname = opname;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public void setScdname(String scdname) {
        this.scdname = scdname;
    }

    public void setScdcode(String scdcode) {
        this.scdcode = scdcode;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}