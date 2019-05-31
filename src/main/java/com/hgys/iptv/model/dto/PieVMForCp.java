package com.hgys.iptv.model.dto;

import com.hgys.iptv.controller.vm.CpSettleStatisticsVM;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName PieVMForCp
 * @Auther: wangz
 * @Date: 2019/5/30 17:48
 * @Description: TODO
 */
@Data
public class PieVMForCp {
    private String name;// 账单名
    private BigDecimal settlementMoney;// 账单总金额

    Set<PieVMForCp.Details> list;// 账单明细

    @Getter
    @Setter
    public static class Details {

        private String cpName;

        private String ratio;

        private BigDecimal settlementMoney;

        private Set<String> bizNames;//关联的业务

        private Set<String> prodNames;//关联的产品

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (this == obj)
                return true;
            if (obj instanceof PieVMForCp.Details) {
                Details vo = (Details) obj;
                // 比较每个属性的值 一致时才返回true
                return (vo.cpName.equals(this.cpName) && vo.settlementMoney.equals(this.settlementMoney));
            }
            return false;
        }

        /**
         * 重写hashcode 方法，返回的hashCode不一样才再去比较每个属性的值
         */
        @Override
        public int hashCode() {
            return cpName.hashCode() * settlementMoney.hashCode();
        }
    }


    private List<PieVMForCp.TopSixListVM> topSixList;//按rank排序

    @Getter
    @Setter
    public static class TopSixListVM{
        /** cp名称 */
        private String cpName;
        private Integer rank;//cp-排名
        private BigDecimal settlementMoney;
        private String ratio;//占比
    }
}
