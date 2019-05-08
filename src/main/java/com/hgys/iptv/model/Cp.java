package com.hgys.iptv.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
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
@Data
public class Cp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    @JSONField(ordinal=1)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;
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
    @ManyToMany
    @JoinTable(name="cp_business",joinColumns = {@JoinColumn(name="cpid")},inverseJoinColumns={@JoinColumn(name="bid")})
    private List<Business> businessList;
    @ManyToMany
    @JoinTable(name="cp_product",joinColumns = {@JoinColumn(name="cpid")},inverseJoinColumns={@JoinColumn(name="pid")})
    private List<Product> productList;

}
