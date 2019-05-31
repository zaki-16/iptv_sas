package com.hgys.iptv.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @ClassName ChartVMForBiz
 * @Auther: wangz
 * @Date: 2019/5/31 09:58
 * @Description: TODO 业务名称、关联CP、关联产品、结算金额
 */
@Data
public class ChartVMForCp {

    private String name;// cp名称

    private Set<String> bizNames;

    private Set<String> prodNames;//关联的产品

    private String ratio;

    private BigDecimal settlementMoney;
}
