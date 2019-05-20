package com.hgys.iptv.controller.vm;

import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.Product;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName BusinessVM
 * @Auther: wangz
 * @Date: 2019/5/13 17:55
 * @Description: TODO
 */
@Data
public class BusinessVM {
    private Integer id;
    private String name;
    private String code;
    private Integer bizType;//1.视频类 2.非视频类
    private Integer settleType;//1.比例结算 2.订购量结算
    private Timestamp inputTime;
    private Timestamp modifyTime;
    private Integer status;//0.禁用 1.启用
    private Integer isdelete;//0：未删除 1：已删除

    private List<Product> pList;
    private List<Cp> cpList;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Timestamp getInputTime() {
        return inputTime;
    }

    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public List<Product> getpList() {
        return pList;
    }

    public void setpList(List<Product> pList) {
        this.pList = pList;
    }

    public List<Cp> getCpList() {
        return cpList;
    }

    public void setCpList(List<Cp> cpList) {
        this.cpList = cpList;
    }
}
