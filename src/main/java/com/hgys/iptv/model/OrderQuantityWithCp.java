package com.hgys.iptv.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 结算类型-订购量表与CP关系表(cp_order_quantity)
 *
 * @author tjq
 * @version 1.0.0 2019-05-05
 */
@Entity
@Table(name="cp_order_quantity")
public class OrderQuantityWithCp implements java.io.Serializable{
    /** 版本号 */
    private static final long serialVersionUID = 8162579340244908011L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    /** cp名称*/
    @Column(name = "cpname", nullable = true, length = 50)
    private String cpname;


    /** code*/
    @Column(name = "code", nullable = true, length = 50)
    private String code;


    @Column(name = "cpcode", nullable = true, length = 11)
    /** cp的CODE */
    private String cpcode;

    @Column(name = "createtime", nullable = true, length = 6)
    /** 创建时间*/
    private Timestamp  createtime;

    @Column(name = "note", nullable = true, length = 255)
    /** 备注 */
    private String note;

    /** 结算类型-订购量表名称 */
    @Column(name = "oqname", nullable = true, length = 50)
    private String oqname;

    /** 结算类型-订购量表的CODE */
    @Column(name = "oqcode", nullable = true, length = 11)
    private String oqcode;

    /** 备用字段3 */
    @Column(name = "col3", nullable = true, length = 50)
    private String col3;

    /** 是否删除 */
    @Column(name = "isdelete", nullable = true, length = 2)
    private Integer isdelete;

    /**订购量*/
    @Column(name = "quantity", nullable = true, length = 11)
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public String getCpname() {
        return cpname;
    }


    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getNote() {
        return note;
    }

    public String getOqname() {
        return oqname;
    }



    public String getCol3() {
        return col3;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }


    public void setCpcode(String cpcode) {
        this.cpcode = cpcode;
    }

    public void setOqcode(String oqcode) {
        this.oqcode = oqcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCpcode() {
        return cpcode;
    }

    public String getOqcode() {
        return oqcode;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setOqname(String oqname) {
        this.oqname = oqname;
    }



    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
