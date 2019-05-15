package com.hgys.iptv.model.dto;

import lombok.Data;

/**
 * 根据(结算类型+结算方式+待结算总金额)->查已有的结算规则表（展示规则名称+规则编码）：单选
 *          ->grossIncome->按规则编码查具体具体规则
 *         ->分账完成后，返回统一结果实体 SettleMetaResource
 *
 * 新增分账-其实就是新增规则-
 * @ClassName SettleDTO
 * @Auther: wangz
 * @Date: 2019/5/14 23:14
 * @Description: TODO
 */
@Data
public class SettleDTO {
    /**
     * 结算类型:
     * CP定比例结算=1
     * 业务级结算=2
     * 业务定比例结算=3
     * 订购量结算=4
     * 产品级结算=5
     * */
    private Integer settleType;
    /**结算方式类型
     * 无=0，即直接根据结算类型就可完成分账的方式，如cp定比例
     * 定比例=1
     * 定金额=2
     * */
    private Integer settleModeType;

    /**结算规则编号*/
    private String settleRuleCode;

    /**业务总收入*/
    private String grossIncome;


}
