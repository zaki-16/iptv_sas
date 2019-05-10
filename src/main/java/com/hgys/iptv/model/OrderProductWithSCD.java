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

    /** 产品名称 */
    @Column(name = "pname", nullable = true, length = 100)
    private String pname;

    /** 产品编码 */
    @Column(name = "pcode", nullable = true, length = 50)
    private String pcode;

    /** 结算类型-产品级名称 */
    @Column(name = "opname", nullable = true, length = 100)
    private String opname;

    /** 结算类型-产品级编码 */
    @Column(name = "opcode", nullable = true, length = 50)
    private String opcode;

    /** 创建时间 */
    @Column(name = "createtime", nullable = true, length = 6)
    private Timestamp createtime;



    public Integer getId() {
        return id;
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

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}