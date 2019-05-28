package com.hgys.iptv.controller.vm;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @ClassName CpSettlementMoneyVM
 * @Auther: wangz
 * @Date: 2019/5/28 16:22
 * @Description: TODO
 */
@Data
public class CpSettlementMoneyVM {
    /**
     * CP名称、关联产品、关联业务、结算金额（各账期结算金额之和）、占比
     * 账期（日期区间）、CP名称（文本输入）。
     */
//    private Integer id;

//    /** 分账结算编码 */
//    private String masterCode;
//
//    /** 分账结算名称 */
//    private String masterName;
//
//    /** cp编码 */
    private String cpcode;

    /** cp名称 */
    private String cpname;

    /** 产品编码 */
//    private String productCode;

    /** 产品名称 */
    private String productName;

    /** 业务编码 */
//    private String businessCode;

    /** 业务名称 */
    private String businessName;

    /** 结算金额（各账期结算金额之和）*/
//    private BigDecimal settlementMoney;

    /**
     * （各账期结算金额之和）
     */
    private BigDecimal grossIncome;

    /**
     * 占比
     */
    private String ratio;

//    /** 创建时间 */
//    private Timestamp createTime;
//    private Timestamp setStartTime;
//    private Timestamp setEndTime;

    /**
     * 业务结算展示名称
     * 业务名称、关联CP、关联产品、结算金额（各账期结算金额之和）
     *
     * 产品名称、关联CP、关联业务、结算金额（各账期结算金额之和）
     */

}
