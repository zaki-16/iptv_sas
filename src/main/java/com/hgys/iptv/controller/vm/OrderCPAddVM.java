package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@ApiModel(value = "结算类型-CP定比例新增VM")
public class OrderCPAddVM {

    /** 结算类型-CP定比例名称 */
    @ApiModelProperty("结算类型-CP定比例名称")
    @NotBlank(message = "结算类型-CP定比例名称不能为空")
    private String name;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("集合")
    private List<OrderCpWithCp> list;

    public void setSettleaccounts(Integer settleaccounts) {
        this.settleaccounts = settleaccounts;
    }

    public Integer getSettleaccounts() {
        return settleaccounts;
    }

    /** 结算方式 */
    @ApiModelProperty("settleaccounts")
    private Integer settleaccounts;

    @Data
    public class OrderCpWithCp{
        /** 金额 */
        @ApiModelProperty("money")
        private Integer money;

        @ApiModelProperty("权重")
        private Integer weight;

        /** cp名称*/
        @ApiModelProperty("cp名称")
        private String cpname;

        /** cpcode */
        @ApiModelProperty("cpcode")
        private String cpcode;

        /** 创建时间*/
        @ApiModelProperty("createtime")
        private Timestamp createtime;

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

        public String getCpname() {
            return cpname;
        }




        public String getNote() {
            return note;
        }



        public Timestamp getCreatetime() {
            return createtime;
        }

        public void setMoney(Integer money) {
            this.money = money;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }



        public String getOcname() {
            return ocname;
        }

        public String getOccode() {
            return occode;
        }

        public void setOcname(String ocname) {
            this.ocname = ocname;
        }

        public void setOccode(String occode) {
            this.occode = occode;
        }

        public void setSettleaccounts(Integer settleaccounts) {
            this.settleaccounts = settleaccounts;
        }

        public void setCpname(String cpname) {
            this.cpname = cpname;
        }

        public Integer getMoney() {
            return money;
        }

        public Integer getWeight() {
            return weight;
        }

        public Integer getSettleaccounts() {
            return settleaccounts;
        }

        public void setCreatetime(Timestamp createtime) {
            this.createtime = createtime;
        }

        public void setNote(String note) {
            this.note = note;
        }





        public void setCol3(String col3) {
            this.col3 = col3;
        }

        public void setIsdelete(Integer isdelete) {
            this.isdelete = isdelete;
        }

        public String getCpcode() {
            return cpcode;
        }

        public void setCpcode(String cpcode) {
            this.cpcode = cpcode;
        }

        public String getCol3() {
            return col3;
        }

        public Integer getIsdelete() {
            return isdelete;
        }

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

    public List<OrderCpWithCp> getList() {
        return list;
    }

    public void setList(List<OrderCpWithCp> list) {
        this.list = list;
    }
}
