package com.hgys.iptv.model.dto;

import lombok.Data;

import java.math.BigDecimal;
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

    @Data
    public static class Details {

        private String name;// 业务名称

        private String ratio;

        private BigDecimal settlementMoney;

        private List<String> bizNames;//关联的业务

        private List<String> prodNames;//关联的产品
    }
}
