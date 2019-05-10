package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;
@ApiModel(value = "结算类型-订购量新增VM")
public class OrderQuantityAddVM {


    @ApiModelProperty("结算类型-订购量ID")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    /** 维度编码 */
    @ApiModelProperty("维度编码")
    private String code;



    /** 结算类型-订购量名称 */
    @ApiModelProperty("结算类型-订购量名称")
    @NotBlank(message = "结算类型-订购量名称不能为空")
    private String name;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("录入时间")
    private Timestamp inputTime;

    @ApiModelProperty("状态")
    private Integer status;
    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除")
    private Integer isdelete;
    @ApiModelProperty("集合")
    private List<SmallOrderCpVM> list;

    public Integer getIsdelete() {
        return isdelete;
    }

    public Timestamp getInputTime() {
        return inputTime;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getName() {
        return name;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SmallOrderCpVM> getList() {
        return list;
    }

    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    public String getCode() {
        return code;
    }

    public void setList(List<SmallOrderCpVM> list) {
        this.list = list;
    }
}
