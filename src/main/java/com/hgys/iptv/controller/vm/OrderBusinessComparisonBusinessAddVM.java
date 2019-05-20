package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("业务类型-业务定比例新增业务VM")
@Data
public class OrderBusinessComparisonBusinessAddVM {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("业务编码")
    private String businessCode;

    /** 业务名称 */
    @ApiModelProperty("业务名称")
    private String businessName;

    @ApiModelProperty("cp信息集合")
    private List<OrderBusinessComparisonAddListVM> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<OrderBusinessComparisonAddListVM> getList() {
        return list;
    }

    public void setList(List<OrderBusinessComparisonAddListVM> list) {
        this.list = list;
    }
}
