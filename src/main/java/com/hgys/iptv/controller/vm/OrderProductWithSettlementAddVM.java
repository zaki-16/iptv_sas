package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("结算类型-产品级新增VM")
public class OrderProductWithSettlementAddVM implements Serializable {

    @ApiModelProperty("主键")
    private Integer id;
    /** 名称 */
    @ApiModelProperty("结算类型-产品级名称")
    private String name;

    /** 结算方式(1:按单维度结算;2:多维度结算) */
    @ApiModelProperty("结算方式(1:按单维度结算;2:多维度结算)")
    private Integer mode;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String note;

    /** 业务编码 */
    @ApiModelProperty("业务编码")
    private String productcode;

    /** 业务名称 */
    @ApiModelProperty("业务名称")
    private String productname;

    @ApiModelProperty("维度信息集合")
    private List<OrderProductWithSCDAddLIstVM> list;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMode() {
        return mode;
    }

    public Integer getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public String getProductcode() {
        return productcode;
    }

    public String getProductname() {
        return productname;
    }

    public List<OrderProductWithSCDAddLIstVM> getList() {
        return list;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setList(List<OrderProductWithSCDAddLIstVM> list) {
        this.list = list;
    }
}
