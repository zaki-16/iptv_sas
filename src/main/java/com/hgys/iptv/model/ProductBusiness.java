package com.hgys.iptv.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName ProductBusiness
 * @Auther: wangz
 * @Date: 2019/5/9 13:23
 * @Description: TODO
 */
@Entity
@Table(name="product_business")
public class ProductBusiness implements Serializable {
    private static final long serialVersionUID = 4961808318959636126L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 20)
    private Integer id;
    @Column(name = "pid", nullable = false, length = 20)
    private Integer pid;
    @Column(name = "bid",nullable = false, length = 20)
    private Integer bid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }
}
