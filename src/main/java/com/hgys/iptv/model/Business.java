package com.hgys.iptv.model;

import lombok.Data;

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
@Data
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;
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
}
