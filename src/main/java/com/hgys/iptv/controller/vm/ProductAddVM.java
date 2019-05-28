package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
@ApiModel(value = "产品新增VM")
public class ProductAddVM {
    @ApiModelProperty("主键，新增时填写无效")
    private Integer id;
    @ApiModelProperty("名称") @NotBlank(message = "不能为空")
    private String name;

    @ApiModelProperty("价格")
    private String price;

    @ApiModelProperty("产品编号")
    private String prodNum;

    @ApiModelProperty("状态")@NotBlank(message = "不能为空")
    private Integer status;

    @ApiModelProperty(value = "产品关联的cp集合id字符串")//dataType = "List"
    private String cpids;

    @ApiModelProperty(value = "产品关联的业务集合id字符串")
    private String bids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCpids() {
        return cpids;
    }

    public void setCpids(String cpids) {
        this.cpids = cpids;
    }

    public String getBids() {
        return bids;
    }

    public void setBids(String bids) {
        this.bids = bids;
    }
}
