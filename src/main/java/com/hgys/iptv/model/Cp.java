package com.hgys.iptv.model;

import javassist.SerialVersionUID;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * CP表，关联产品表和业务表
 *
 * @Auther: wangz
 * @Date: 2019/5/5 16:16
 * @Description:
 */
@Entity
@Table(name="cp")
public class Cp implements Serializable {
    private static final long serialVersionUID = -60947913577748968L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;
    @Column(name = "cpAbbr", nullable = false, length = 50)
    private String cpAbbr;
    private String code;
    private String contactNm;
    private String contactTel;
    private String contactMail;
    private Timestamp regisTime;
    private Timestamp modifyTime;
    private Timestamp cancelTime;
    @Column(name = "status", nullable = false, length = 2)
    private Integer status;//1.正常 2.结算 3.异常 4.注销
    private String note;
    private Integer isdelete;//0：未删除 1：已删除

//    @ManyToMany(mappedBy = "cpList",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
//    private List<Product> productList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpAbbr() {
        return cpAbbr;
    }

    public void setCpAbbr(String cpAbbr) {
        this.cpAbbr = cpAbbr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContactNm() {
        return contactNm;
    }

    public void setContactNm(String contactNm) {
        this.contactNm = contactNm;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public Timestamp getRegisTime() {
        return regisTime;
    }

    public void setRegisTime(Timestamp regisTime) {
        this.regisTime = regisTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Timestamp getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Timestamp cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

}
