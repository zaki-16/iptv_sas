package com.hgys.iptv.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 结算类型-CP定比例与CP关系表(cp_order_cp)
 *
 * @author tjq
 * @version 1.0.0 2019-05-05
 */
@Entity
@Table(name="cp_order_cp")
public class OrderCpWithCp implements java.io.Serializable{
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
    private Integer cpcode;

    @Column(name = "createtime", nullable = true, length = 6)
    /** 创建时间*/
    private Timestamp  createtime;

    @Column(name = "note", nullable = true, length = 255)
    /** 备注 */
    private String note;

    /** 结算类型-CP定比例表名称 */
    @Column(name = "ocname", nullable = true, length = 50)
    private String ocname;

    /** 结算类型-CP定比例表的CODE */
    @Column(name = "occode", nullable = true, length = 11)
    private Integer occode;

    /** 备用字段3 */
    @Column(name = "col3", nullable = true, length = 50)
    private String col3;

    /** 是否删除 */
    @Column(name = "isdelete", nullable = true, length = 2)
    private Integer isdelete;

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

    public Integer getCpcode() {
        return cpcode;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCpcode(Integer cpcode) {
        this.cpcode = cpcode;
    }

    public String getOcname() {
        return ocname;
    }

    public Integer getOccode() {
        return occode;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public void setOcname(String ocname) {
        this.ocname = ocname;
    }

    public void setOccode(Integer occode) {
        this.occode = occode;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}
