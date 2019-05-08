package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 产品表
 */
@Entity
@Table(name="product")
@Data
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

}
