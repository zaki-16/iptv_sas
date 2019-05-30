package com.hgys.iptv.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ChartVM
 * @Auther: wangz
 * @Date: 2019/5/30 16:13
 * @Description: TODO
 */
@Data
public class ChartVM {

    private String cpname;

    private String ratio;

    private BigDecimal settlementMoney;

}
