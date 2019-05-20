package com.hgys.iptv.controller.vm;

import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Product;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName CpVM
 * @Auther: wangz
 * @Date: 2019/5/9 10:44
 * @Description: TODO
 */
@Data
public class CpVM {
    private Integer id;
    private String name;
    private String cpAbbr;
    private String code;
    private String contactNm;
    private String contactTel;
    private String contactMail;
    private Timestamp regisTime;
    private Timestamp modifyTime;
    private Timestamp cancelTime;
    private Integer status;//1.正常 2.结算 3.异常 4.注销
    private String note;
    private Integer isdelete;//0：未删除 1：已删除
//    分页
    private Integer pageNum;
    private Integer pageSize;

    private List<Product> pList;

    private List<Business> bList;

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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Product> getpList() {
        return pList;
    }

    public void setpList(List<Product> pList) {
        this.pList = pList;
    }

    public List<Business> getbList() {
        return bList;
    }

    public void setbList(List<Business> bList) {
        this.bList = bList;
    }
}
