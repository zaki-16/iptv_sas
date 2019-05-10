package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@ApiModel("集合VM")
public class OrderQuantityControllerListVM {

    /** 主键 */
    @ApiModelProperty("主键")
    private Integer id;

    /** 订购量结算名称*/
    @ApiModelProperty("订购量结算名称")
    private String name;

    /** 订购量结算编码*/
    @ApiModelProperty("订购量结算编码")
    private String code;

    /** 录入时间 */
    @ApiModelProperty("录入时间")
    private Timestamp inputTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String note;

    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除(0:否；1:是)")
    private Integer isdelete;
    @ApiModelProperty("集合")
    private List<SmallOrderCpVM> list;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Timestamp getInputTime() {
        return inputTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public List<SmallOrderCpVM> getList() {
        return list;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public void setList(List<SmallOrderCpVM> list) {
        this.list = list;
    }
}
