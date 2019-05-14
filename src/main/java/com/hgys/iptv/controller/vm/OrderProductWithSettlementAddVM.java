package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
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
    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除")
    private Integer isdelete;
    /** 备注 */
    @ApiModelProperty("备注")
    private String note;

    /** 单维度名称 */
    @Column(name = "sdname", nullable = true, length = 100)
    private String sdname;

    /** code */
    @Column(name = "code", nullable = true, length = 50)
    private String code;
    /** 单维度编码 */
    @Column(name = "sdcode", nullable = true, length = 50)
    private String sdcode;

    /** 多维度名称 */
    @Column(name = "scdname", nullable = true, length = 100)
    private String scdname;

    /** 多维度编码 */
    @Column(name = "scdcode", nullable = true, length = 50)
    private String scdcode;

  /** 录入时间 */
    @ApiModelProperty("录入时间")
    private Timestamp inputTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    /** 修改时间   */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;
    @ApiModelProperty("产品信息集合")
    private List<OrderProductWithSCDAddLIstVM> list;

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
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

    public List<OrderProductWithSCDAddLIstVM> getList() {
        return list;
    }

    public void setSdname(String sdname) {
        this.sdname = sdname;
    }

    public void setSdcode(String sdcode) {
        this.sdcode = sdcode;
    }

    public void setScdname(String scdname) {
        this.scdname = scdname;
    }

    public void setScdcode(String scdcode) {
        this.scdcode = scdcode;
    }

    public String getSdname() {
        return sdname;
    }

    public String getSdcode() {
        return sdcode;
    }

    public String getScdname() {
        return scdname;
    }

    public String getScdcode() {
        return scdcode;
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

    public void setList(List<OrderProductWithSCDAddLIstVM> list) {
        this.list = list;
    }
}
