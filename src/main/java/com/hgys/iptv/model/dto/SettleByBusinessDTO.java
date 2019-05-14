package com.hgys.iptv.model.dto;

import lombok.Data;

/**
 * @ClassName SettleByBusinessDTO
 * @Auther: wangz
 * @Date: 2019/5/14 17:58
 * @Description: TODO
 */
@Data
public class SettleByBusinessDTO extends SettleMetaResource{
    private static final long serialVersionUID = 1495496252467665323L;

    //业务名称、业务代码、业务收入
    private String orderCpCode;

    /**总收入--用string防止精度截断*/
    private String grossIncome;
}
