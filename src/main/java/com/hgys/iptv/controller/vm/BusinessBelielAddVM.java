package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("分配结算业务定比例结算新增VM")
@Data
public class BusinessBelielAddVM {
    /**
     * 业务名称
     */
    @ApiModelProperty("业务名称")
    private String businessName;

    /**
     * 业务编码
     */
    @ApiModelProperty("业务编码")
    private String businessCode;

    /** 1:比例结算；2:金额结算 */
    @ApiModelProperty("1:比例结算；2:金额结算")
    private Integer type;

    /**
     * 业务收入
     */
    @ApiModelProperty("业务收入")
    private BigDecimal money;

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public Integer getType() {
        return type;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }
}
