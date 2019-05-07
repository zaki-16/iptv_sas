package com.hgys.iptv.model;

import java.sql.Timestamp;
import javax.persistence.*;
/**
 * 结算类型-CP定比例(order_cp)
 *
 * @author tjq
 * @version 1.0.0 2019-05-06
 */
@Entity
@Table(name="order_cp")
public class OrderCp implements java.io.Serializable{
    /** 版本号 */
    private static final long serialVersionUID = 654371763045670414L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** CP定比例型结算名称 */
    @Column(name = "name", nullable = true, length = 50)
    private String name;


    @Column(name = "code", nullable = true, length = 255)
    /** CP定比例型结算编码 */
    private String code;


    @Column(name = "inputTime", nullable = true, length = 50)
    /** 录入时间*/
    private Timestamp inputTime;



    @Column(name = "modifyTime", nullable = true, length = 50)
    /** 修改时间 */
    private Timestamp modifyTime;



    @Column(name = "status", nullable = true, length = 2)
    /** 状态 */
    private Integer status;


    @Column(name = "note", nullable = true, length = 255)
    /** 备注 */
    private String note;


    @Column(name = "settleaccounts", nullable = true, length = 2)
    /** 备注 */
    private Integer settleaccounts;

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






    public void setSettleaccounts(Integer settleaccounts) {
        this.settleaccounts = settleaccounts;
    }

    public Integer getSettleaccounts() {
        return settleaccounts;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Timestamp getInputTime() {
        return inputTime;
    }

    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
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

    public Timestamp getModifyTime() {
        return modifyTime;
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

    public Integer getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }
}
