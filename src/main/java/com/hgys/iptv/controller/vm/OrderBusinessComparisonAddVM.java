package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel("业务类型-业务定比例新增VM")
@Data
public class OrderBusinessComparisonAddVM implements Serializable {

    @ApiModelProperty("主键")
    private Integer id;

    /** 名称 */
    @ApiModelProperty("业务定比例名称")
    private String name;

    /** 结算方式(1:按比例结算;2:按金额结算) */
    @ApiModelProperty("结算方式(1:按比例结算;2:按金额结算)")
    private Integer mode;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remakes;

    @ApiModelProperty("业务信息集合")
    List<OrderBusinessComparisonBusinessAddVM> list;

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

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemakes() {
        return remakes;
    }

    public void setRemakes(String remakes) {
        this.remakes = remakes;
    }

    public List<OrderBusinessComparisonBusinessAddVM> getList() {
        return list;
    }

    public void setList(List<OrderBusinessComparisonBusinessAddVM> list) {
        this.list = list;
    }
}
