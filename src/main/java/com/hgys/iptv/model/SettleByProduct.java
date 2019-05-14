package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SettleByProduct
 * @Auther: wangz
 * @Date: 2019/5/13 09:35
 * @Description: TODO 产品级结算
 */
@Entity
@Table(name = "settleby_product")
@Data
public class SettleByProduct implements Serializable {
    private static final long serialVersionUID = 6632543699979055808L;
    /**
     * 选择单维度结算，通过下拉列表从结算单维度选择结算维度。
     * CP编码、CP名称、产品编码、产品名称、xx维度、产品结算金额。
     *
     * 若组合维度结算，填入CP编码、CP名称、产品编码、产品名称、A维度、B维度、C维度、产品结算金额。
     * 提交时校验所有CP结算金额之和不大于总收入。
     *
     * 结算公式：按产品a下所属cpa对应的维度组合（各自有权重，需校验）所占比金额
     * e.g：PA.amount= 100;
     * CP1.amount = PA.amount * CP1.W1(r1*t1+r2*t2+r3*t3+...);
     * 其中W(x)={t1,t2,t3,...ti,...},ti是维度分量,ri是维度比例（需计算）
     *
     */

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    /** CP名称*/
    @Column(name = "cpName", nullable = false,columnDefinition="varchar(50) COMMENT 'CP名称'")
    private String cpName;

    /** CP编码*/
    @Column(name = "cpCode", nullable = false,columnDefinition="varchar(100) COMMENT 'CP编码'")
    private String cpCode;

    /** 产品名称*/
    @Column(name = "prodName", nullable = false,columnDefinition="varchar(50) COMMENT '产品名称'")
    private String prodName;

    /** 产品编码*/
    @Column(name = "prodCode", nullable = false,columnDefinition="varchar(100) COMMENT '产品编码'")
    private String prodCode;


    /**产品结算金额--需放大1000倍计算*/
    @Column(name = "prodIncome", nullable = false,columnDefinition="int(20) COMMENT '产品收入'")
    private Integer prodIncome;

    /**维度组合k：维度名称，v:权重--单维度是特殊的多维度，权重为100%*/
//    private Map<String,Object> dimensionMap = new HashMap<>();

    /** 备用字段1 */
    @Column(name = "col1", nullable = true, length = 100)
    private String col1;

    /** 备用字段2 */
    @Column(name = "col2", nullable = true, length = 100)
    private String col2;
}
