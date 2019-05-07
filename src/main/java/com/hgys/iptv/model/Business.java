package com.hgys.iptv.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 业务表
 *
 * @Auther: wangz
 * @Date: 2019/5/5 17:11
 * @Description:
 */
@Entity
@Table(name="business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid", unique = true, nullable = false, length = 11)
    private Integer bid;
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;
    private String code;
    private Integer bizType;//1.视频类 2.非视频类
    private Integer settleType;//1.比例结算 2.订购量结算
    private Timestamp inputTime;
    private Timestamp modifyTime;
    @Column(name = "status", nullable = false, length = 2)
    private Integer status;//0.禁用 1.启用
    private Integer isdelete;//0：未删除 1：已删除

    @ManyToMany
    @JoinTable(name="cp_business",joinColumns = {@JoinColumn(name="bid")},inverseJoinColumns={@JoinColumn(name="cpid")})
    private List<Cp> cpList;
    @ManyToMany
    @JoinTable(name="business_product",joinColumns = {@JoinColumn(name="bid")},inverseJoinColumns={@JoinColumn(name="pid")})
    private List<Product> productList;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
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

    public List<Cp> getCpList() {
        return cpList;
    }

    public void setCpList(List<Cp> cpList) {
        this.cpList = cpList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
