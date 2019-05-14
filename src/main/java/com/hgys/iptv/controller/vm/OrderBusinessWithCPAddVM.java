package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@ApiModel(value = "结算类型-业务级新增VM")
public class OrderBusinessWithCPAddVM implements Serializable{

    @ApiModelProperty("ID")
    private Integer id;
    /** 结算类型-业务级名称 */
    @ApiModelProperty("结算类型-业务级名称")
    @NotBlank(message = "结算类型-业务级名称不能为空")
    private String name;

    @ApiModelProperty("结算类型-业务级备注")
    private String note;

    @ApiModelProperty("结算类型-业务级状态")
    private Integer status;

    /** 录入时间 */
    @ApiModelProperty("录入时间")
    private Timestamp inputTime;


    /** 修改时间   */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;

    @ApiModelProperty(value = "结算类型-业务级集合",dataType = "List")
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

    public List<SmallOrderBusinessVM> getList() {
        return list;
    }

    public Timestamp getInputTime() {
        return inputTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
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

    public void setList(List<SmallOrderBusinessVM> list) {
        this.list = list;
    }
}
