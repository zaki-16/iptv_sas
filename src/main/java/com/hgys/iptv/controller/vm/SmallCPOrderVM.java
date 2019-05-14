package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

public class SmallCPOrderVM {

    /** 金额 */
    @ApiModelProperty("money")
    private Integer money;

    @ApiModelProperty("权重")
    private String weight;

    /** cp名称*/
    @ApiModelProperty("cp名称")
    private String cpname;

    /** cpcode */
    @ApiModelProperty("cpcode")
    private String cpcode;



    /** 备注 */
    @ApiModelProperty("note")
    private String note;

    /** 结算类型-订购量表名称 */
    @ApiModelProperty("ocname")
    private String ocname;

    /** 结算类型-oqcode */
    @ApiModelProperty("occode")
    private String occode;

    /** 备用字段3 */
    @ApiModelProperty("col3")
    private String col3;

    /** 是否删除 */
    @ApiModelProperty("isdelete")
    private Integer isdelete;

    /** 结算方式 */
    @ApiModelProperty("settleaccounts")
    private Integer settleaccounts;

    public Integer getMoney() {
        return money;
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

    public String getOcname() {
        return ocname;
    }

    public String getOccode() {
        return occode;
    }

    public String getCol3() {
        return col3;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public Integer getSettleaccounts() {
        return settleaccounts;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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

    public void setOcname(String ocname) {
        this.ocname = ocname;
    }

    public void setOccode(String occode) {
        this.occode = occode;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public void setSettleaccounts(Integer settleaccounts) {
        this.settleaccounts = settleaccounts;
    }
}
