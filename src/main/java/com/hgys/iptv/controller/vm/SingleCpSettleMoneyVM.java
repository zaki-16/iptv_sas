package com.hgys.iptv.controller.vm;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName SingleCpSettleMoneyVM
 * @Auther: wangz
 * @Date: 2019/5/29 14:57
 * @Description: TODO
 */
@Data
public class SingleCpSettleMoneyVM {

    /** 账期名称 */
    private String name;
    /** 账期编码 */
    private String code;

    /** 账期开始时间 */
    private Timestamp setStartTime;
    /** 结算结束时间 */
    private Timestamp setEndTime;
    /** 单个账期内所有cp的总金额之和*/
    private BigDecimal grossIncome;
    /** 单个账期内所有cp的结算详情*/
    List<CpSettlementMoneyVM> cpList;
}
