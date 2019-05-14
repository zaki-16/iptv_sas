package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName SettleByBusinessWithRatio
 * @Auther: wangz
 * @Date: 2019/5/13 09:21
 * @Description: TODO 业务级--定比例结算
 */
@Entity
@Table(name= "settleby_biz_ratio")
@Data
public class SettleByBusinessWithRatio implements Serializable {
    private static final long serialVersionUID = 9010978324563815936L;
    /**
     * 若选择按比例结算，填入业务名称、业务代码、业务收入。
     * 若选择按金额结算，填入业务名称、业务代码、业务收入。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    /** CP名称*/
    @Column(name = "cpName", nullable = false, length = 50)
    private String cpName;

    /** CP编码*/
    @Column(name = "cpCode", nullable = false, length = 100)
    private String cpCode;

    /** 计算类型：按比例结算，按金额结算--选定后可以返回客户也可隐藏，但只能选定一种结算方式*/
    @Column(name = "settleType", nullable = false, length = 2)
    private Integer settleType;

    /** 业务名称*/
    @Column(name = "businessName", nullable = false, length = 50)
    private String businessName;

    /** 业务编码*/
    @Column(name = "businessCode", nullable = false, length = 100)
    private String businessCode;

    /**业务收入--需放大1000倍计算*/
    @Column(name = "businessIncome", nullable = false, length = 20)
    private Integer businessIncome;

    /** 备用字段1 */
    @Column(name = "col1", nullable = true, length = 100)
    private String col1;

    /** 备用字段2 */
    @Column(name = "col2", nullable = true, length = 100)
    private String col2;
}
