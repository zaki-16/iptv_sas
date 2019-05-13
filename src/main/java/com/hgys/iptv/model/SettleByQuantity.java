package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName SettleByQuantity
 * @Auther: wangz
 * @Date: 2019/5/13 09:20
 * @Description: TODO 订购量结算
 */
@Entity
@Table(name = "settleby_quantity")
@Data
public class SettleByQuantity implements Serializable {
    private static final long serialVersionUID = -2294426599384293315L;

    /**
     * 填入每个CP订购量、总收入。需要导入的Excel模板（CP编码、CP名称、订购量）
     */
    /** 主键 */
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

    /**cp订购量*/
    @Column(name = "cpQuantity", nullable = false, length = 11)
    private Integer cpQuantity;

    /**总收入--需放大1000倍计算*/
    @Column(name = "grossIncome", nullable = false, length = 20)
    private Integer grossIncome;

    /** 备用字段1 */
    @Column(name = "col1", nullable = true, length = 100)
    private String col1;

    /** 备用字段2 */
    @Column(name = "col2", nullable = true, length = 100)
    private String col2;
}
