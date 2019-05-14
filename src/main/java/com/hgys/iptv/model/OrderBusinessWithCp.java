package com.hgys.iptv.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 结算类型-业务级表规则表(cp_order_business)
 *
 * @author tjq
 * @version 1.0.0 2019-05-06
 */
@Entity
@Table(name="cp_order_business")
public class OrderBusinessWithCp implements java.io.Serializable{
    /** 版本号 */
    private static final long serialVersionUID= -8746480993096953207L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 本表唯一codes*/
    @Column(name = "code", nullable = true, length = 50)
    private String code;

    /** 结算类型-业务级名称*/
    @Column(name = "obname", nullable = true, length = 50)
    private String obname;

    @Column(name = "obcode", nullable = true, length = 50)
    /** 结算类型-业务级编码 */
    private String obcode;


    /** 业务名称*/
    @Column(name = "buname", nullable = true, length = 50)
    private String buname;

    @Column(name = "bucode", nullable = true, length = 50)
    /** 业务编码 */
    private String bucode;

    @Column(name = "createtime", nullable = true, length = 6)
    /** 录入时间*/
    private Timestamp createtime;


    @Column(name = "weight", nullable = true, length =30)
    /** 权重 */
    private String weight;

    @Column(name = "status", nullable = true, length = 2)
    /** 状态 */
    private Integer status;

    @Column(name = "note", nullable = true, length = 255)
    /** 备注 */
    private String note;

    /** 备用字段1 */
    @Column(name = "col1", nullable = true, length = 50)
    private String col1;

    /** 备用字段2 */
    @Column(name = "col2", nullable = true, length = 11)
    private Integer col2;

    /** 备用字段3 */
    @Column(name = "col3", nullable = true, length = 50)
    private String col3;

    /** 是否删除 */
    @Column(name = "isdelete", nullable = true, length = 2)
    private Integer isdelete;

    public Integer getId() {
        return id;
    }

    public String getObname() {
        return obname;
    }

    public String getObcode() {
        return obcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBuname() {
        return buname;
    }

    public String getBucode() {
        return bucode;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }



    public Integer getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public String getCol1() {
        return col1;
    }

    public Integer getCol2() {
        return col2;
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

    public void setObname(String obname) {
        this.obname = obname;
    }

    public void setObcode(String obcode) {
        this.obcode = obcode;
    }

    public void setBuname(String buname) {
        this.buname = buname;
    }

    public void setBucode(String bucode) {
        this.bucode = bucode;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public void setCol2(Integer col2) {
        this.col2 = col2;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}
