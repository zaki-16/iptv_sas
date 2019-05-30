package com.hgys.iptv.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName ChartVMForBiz
 * @Auther: wangz
 * @Date: 2019/5/30 16:15
 * @Description: TODO
 */
@Data
public class ChartVMForBiz {

    private String name;// 业务名称

    private String ratio;

    private BigDecimal settlementMoney;

    private List<String> cpNames;//关联的cp

    private List<String> prodNames;//关联的产品
}
