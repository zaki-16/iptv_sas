package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName SettleByCp
 * @Auther: wangz
 * @Date: 2019/5/13 09:22
 * @Description: TODO CP定比例结算
 * 若选择按比例结算，填入总收入。
 * 若选择按金额结算，填入总收入。
 */
@Entity
@Table(name = "settleby_cp")
@Data
public class SettleByCp implements Serializable {
    private static final long serialVersionUID = 5544663334406108024L;

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

    /** 计算类型：按比例结算，按金额结算--选定后可以返回客户也可隐藏，但只能选定一种结算方式
     * 其实可以不加，直接校验总额*/
    @Column(name = "settleType", nullable = false, length = 2)
    private Integer settleType;

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
