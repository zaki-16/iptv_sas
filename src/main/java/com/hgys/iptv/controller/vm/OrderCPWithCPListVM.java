package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

@ApiModel("结算类型-CP定比例VM")
public class OrderCPWithCPListVM {

    @ApiModelProperty("主键")
    private Integer id;

    /** 编码 */
    @ApiModelProperty("编码")
    private String code;

    /** 名称 */
    @ApiModelProperty("名称")
    private String name;

    /** 录入时间 */
    @ApiModelProperty("录入时间")
    private Timestamp inputTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;
    /** 结算类型0：按比例，1：按金额 */
    @ApiModelProperty("结算类型")
    private Integer settleaccounts;

    /** 备注 */
    @ApiModelProperty("备注")
    private String note;

    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除")
    private Integer isdelete;

    @ApiModelProperty("结算类型-CP定比例与CP集合")
    private List<OrderCPWithCPListVM.OrderCpWithCp> list;

    public static class OrderCpWithCp{

        /** ID */
        @ApiModelProperty("ID")
        private Integer id;

        /** 编码 */
        @ApiModelProperty("编码")
        private String code;

        /** cp名称 */
        @ApiModelProperty("cp名称")
        private String cpname;

        /** cpcode */
        @ApiModelProperty("cpcode")
        private String cpcode;


        /** occode */
        @ApiModelProperty("occode")
        private String occode;

        /** ocname */
        @ApiModelProperty("ocname")
        private String ocname;

        /** 金额 */
        @ApiModelProperty("money")
        private Integer money;

        /** 权重 */
        @ApiModelProperty("weight")
        private Integer weight;

        public String getCpcode() {
            return cpcode;
        }

        public String getOccode() {
            return occode;
        }

        public String getOcname() {
            return ocname;
        }

        public Integer getId() {
            return id;
        }

        public String getCode() {
            return code;
        }

        public String getCpname() {
            return cpname;
        }

        public void setCpcode(String cpcode) {
            this.cpcode = cpcode;
        }

        public void setOccode(String occode) {
            this.occode = occode;
        }

        public void setOcname(String ocname) {
            this.ocname = ocname;
        }

        public Integer getMoney() {
            return money;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setMoney(Integer money) {
            this.money = money;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setCpname(String cpname) {
            this.cpname = cpname;
        }


    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<OrderCPWithCPListVM.OrderCpWithCp> getList() {
        return list;
    }

    public void setList(List<OrderCPWithCPListVM.OrderCpWithCp> list) {
        this.list = list;
    }

    public Integer getSettleaccounts() {
        return settleaccounts;
    }

    public void setSettleaccounts(Integer settleaccounts) {
        this.settleaccounts = settleaccounts;
    }
}
