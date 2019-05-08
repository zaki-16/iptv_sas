package com.hgys.iptv.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 产品表
 */
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;
    private String code;
    private Double price;
    private Timestamp inputTime;
    private Timestamp modifyTime;
    @Column(name = "status", nullable = false, length = 2)
    private Integer status;//0.禁用 1.启用
    private Integer isdelete;//0：未删除 1：已删除

    @ManyToMany(cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinTable(name="cp_product",joinColumns = {@JoinColumn(name="pid")},inverseJoinColumns={@JoinColumn(name="cpid")})
    private List<Cp> cpList;
    @ManyToMany
    @JoinTable(name="business_product",joinColumns = {@JoinColumn(name="pid")},inverseJoinColumns={@JoinColumn(name="bid")})
    private List<Business> businessList;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public List<Business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }
}
