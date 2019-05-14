package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName SettleByBusiness
 * @Auther: wangz
 * @Date: 2019/5/13 09:21
 * @Description: TODO 业务级结算，填入总收入
 */
@Entity
@Table(name = "settleby_biz")
@Data
public class SettleByBusiness implements Serializable {
    private static final long serialVersionUID = 6769059582614114552L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    /** CP名称*/
    @Column(name = "cpName", nullable = false,columnDefinition="varchar(50) COMMENT 'cp名称'")
    private String cpName;

    /** CP编码*/
    @Column(name = "cpCode", nullable = false,columnDefinition="varchar(100) COMMENT 'cp编码'")
    private String cpCode;

    /**总收入--需放大1000倍计算*/
    @Column(name = "grossIncome", nullable = false,columnDefinition="int(20) COMMENT '总收入'")
    private Integer grossIncome;

    /** 备用字段1 */
    @Column(name = "col1", nullable = true, length = 100)
    private String col1;

    /** 备用字段2 */
    @Column(name = "col2", nullable = true, length = 100)
    private String col2;


}
