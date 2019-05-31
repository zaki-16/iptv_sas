package com.hgys.iptv.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @ClassName PieVMForBiz
 * @Auther: wangz
 * @Date: 2019/5/30 16:16
 * @Description: TODO
 */
@Data
public class PieVMForBiz {

    private String name;// 账单名
    private BigDecimal settlementMoney;// 账单总金额

    Set<Details> list;// 账单明细

    @Data
    public static class Details {

        private String bizName;// 业务名称

        private String ratio;

        private BigDecimal settlementMoney;

        private Set<String> cpNames;//关联的cp

        private Set<String> prodNames;//关联的产品
    }
}
