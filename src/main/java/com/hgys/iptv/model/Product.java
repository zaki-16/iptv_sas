package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品表
 */
@Entity
@Table(name="product")
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -4437702247979414602L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    private String code;
    private String price;
    private Timestamp inputTime;
    private Timestamp modifyTime;
    @Column(name = "status", nullable = false, length = 2)
    private Integer status;
    private Integer isdelete;//0：未删除 1：已删除

//    @ManyToMany
//    @JoinTable(name="cp_product",
//            joinColumns = {@JoinColumn(name="pid",referencedColumnName="id")},
//            inverseJoinColumns={@JoinColumn(name="cpid",referencedColumnName="id")})
//    private List<Cp> cpList = new ArrayList<>();
//
//    @ManyToMany(mappedBy = "productList",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
//    private List<Business> businessList = new ArrayList<>();



}
