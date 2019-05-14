package com.hgys.iptv.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

/**
 * @ClassName SettleByCpDTO
 * @Auther: wangz
 * @Date: 2019/5/14 10:07
 * @Description: TODO
 */
@Data
public class SettleByCpDTO extends SettleMetaResource{
    private static final long serialVersionUID = 6133461996873060801L;
    //CP定比例型结算编码
    private String orderCpCode;

    /**总收入--用string防止精度截断*/
    private String grossIncome;

}
