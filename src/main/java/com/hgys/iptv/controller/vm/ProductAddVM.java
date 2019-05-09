package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "产品新增VM")
public class ProductAddVM {
    @ApiModelProperty("主键，新增时填写无效")
    private Integer id;
    @ApiModelProperty("名称") @NotBlank(message = "不能为空")
    private String name;

    @ApiModelProperty("价格")
    private Double price;

    @ApiModelProperty("状态")@NotBlank(message = "不能为空")
    private Integer status;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
