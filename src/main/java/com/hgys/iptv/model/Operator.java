package com.hgys.iptv.model;

import javax.persistence.*;

@Entity
@Table(name="operator")
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer opid;
    private String opNm;
    private String opAbbr;
    private String opCode;
    private String contactNm;
    private String contactTel;
    private String contactMail;

    public Integer getOpid() {
        return opid;
    }

    public void setOpid(Integer opid) {
        this.opid = opid;
    }

    public String getOpNm() {
        return opNm;
    }

    public void setOpNm(String opNm) {
        this.opNm = opNm;
    }

    public String getOpAbbr() {
        return opAbbr;
    }

    public void setOpAbbr(String opAbbr) {
        this.opAbbr = opAbbr;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
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
}
