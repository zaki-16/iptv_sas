package com.hgys.iptv.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName CpBusiness
 * @Auther: wangz
 * @Date: 2019/5/9 13:23
 * @Description: TODO
 */
@Entity
@Table(name="cp_business")
public class CpBusiness implements Serializable {
    private static final long serialVersionUID = -5615746247935132328L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 20)
    private Integer id;
    @Column(name = "bid", nullable = false, length = 20)
    private Integer bid;
    @Column(name = "cpid",nullable = false, length = 20)
    private Integer cpid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getCpid() {
        return cpid;
    }

    public void setCpid(Integer cpid) {
        this.cpid = cpid;
    }
}
