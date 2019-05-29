package com.hgys.iptv.controller.vm;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName CpSettleStatisticsVM
 * @Auther: wangz
 * @Date: 2019/5/29 13:24
 * @Description: TODO
 */
@Data
public class CpSettleStatisticsVM {
    /**
     *      * 以账期为root
     *      *      child1：cpList：包含金额+占比
     *      *      child2：汇总：取占比前5cp+剩余其他
     */
        /** 结算总账编码 */
        private String code;
        /** 结算总账名称 */
        private String name;

        private List<CpSettlementMoneyVM> cpList;

        private LinkedList<TopSixListVM> topSixList;//按rank排序

        @Getter @Setter
        public static class TopSixListVM{
            /** cp名称 */
            private String cpname;
            private Integer rank;//cp-排名
            private BigDecimal grossIncome;
            private String ratio;//占比
        }


}
