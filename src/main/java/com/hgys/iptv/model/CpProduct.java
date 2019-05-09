package com.hgys.iptv.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName CpProduct
 * @Auther: wangz
 * @Date: 2019/5/9 13:54
 * @Description: TODO
 */
@Entity
@Table(name="cp_product")
public class CpProduct implements Serializable {
    private static final long serialVersionUID = 6695854482630167624L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 20)
    private Integer id;
    @Column(name = "pid", nullable = false, length = 20)
    private Integer pid;
    @Column(name = "cpid",nullable = false, length = 20)
    private Integer cpid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCpid() {
        return cpid;
    }

    public void setCpid(Integer cpid) {
        this.cpid = cpid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
