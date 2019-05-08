package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

public class SmallOrderBusinessVM {



    @ApiModelProperty("权重")
    private Integer weight;

    /** cp名称*/
    @ApiModelProperty("cp名称")
    private String cpname;

    /** cpcode */
    @ApiModelProperty("cpcode")
    private String cpcode;


    /** 备注 */
    @ApiModelProperty("note")
    private String note;

    /** 结算类型-业务级名称 */
    @ApiModelProperty("obname")
    private String obname;

    /** 结算类型-业务级-obcode */
    @ApiModelProperty("obcode")
    private String obcode;

    /** 业务名称 */
    @ApiModelProperty("buname")
    private String buname;

    /** 业务code */
    @ApiModelProperty("bucode")
    private String bucode;

    /** 备用字段3 */
    @ApiModelProperty("col3")
    private String col3;

    /** 是否删除 */
    @ApiModelProperty("isdelete")
    private Integer isdelete;

    public Integer getWeight() {
        return weight;
    }

    public String getCpname() {
        return cpname;
    }

    public String getCpcode() {
        return cpcode;
    }



    public String getNote() {
        return note;
    }

    public String getObname() {
        return obname;
    }

    public String getObcode() {
        return obcode;
    }

    public String getBuname() {
        return buname;
    }

    public String getBucode() {
        return bucode;
    }

    public String getCol3() {
        return col3;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public void setCpcode(String cpcode) {
        this.cpcode = cpcode;
    }



    public void setNote(String note) {
        this.note = note;
    }

    public void setObname(String obname) {
        this.obname = obname;
    }

    public void setObcode(String obcode) {
        this.obcode = obcode;
    }

    public void setBuname(String buname) {
        this.buname = buname;
    }

    public void setBucode(String bucode) {
        this.bucode = bucode;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}
