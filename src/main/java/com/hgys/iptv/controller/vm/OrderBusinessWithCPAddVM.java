package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@ApiModel(value = "结算组合维度新增VM")
public class OrderBusinessWithCPAddVM implements Serializable{

    @ApiModelProperty("ID")
    private Integer id;
    /** 维度名称 */
    @ApiModelProperty("结算类型-业务级名称")
    @NotBlank(message = "结算类型-业务级名称不能为空")
    private String name;

    @ApiModelProperty("结算类型-业务级备注")
    private String note;

    @ApiModelProperty("结算类型-业务级状态")
    private Integer status;


    @ApiModelProperty("权重")
    private Integer weight;


    @ApiModelProperty(value = "结算维度组合集合",dataType = "List")
    private List<SmallOrderBusinessVM> list;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public Integer getStatus() {
        return status;
    }

    public Integer getWeight() {
        return weight;
    }

    public List<SmallOrderBusinessVM> getList() {
        return list;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setList(List<SmallOrderBusinessVM> list) {
        this.list = list;
    }
}
