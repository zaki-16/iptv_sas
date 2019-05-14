package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

public class SmallOrderBusinessVM {


    /** 结算类型-业务级-id */
    @ApiModelProperty("id")
    private Integer id;
    /** 结算类型-业务级-obcode */
    @ApiModelProperty("obcode")
    private String obcode;

    /** 业务名称 */
    @ApiModelProperty("buname")
    private String buname;

    /** 业务code */
    @ApiModelProperty("bucode")
    private String bucode;


    @ApiModelProperty("权重")
    private String weight;

    /** 备用字段3 */
    @ApiModelProperty("col3")
    private String col3;


    @ApiModelProperty(value = "结算类型-业务级CP集合",dataType = "List")
    private List<SmallOrderBusinessVM.SmallOrderBusinessCPVM> lists;

    public static class SmallOrderBusinessCPVM{


        @ApiModelProperty("权重")
        private String weight;

        /** cp名称*/
        @ApiModelProperty("cp名称")
        private String cpname;

        /** cpcode */
        @ApiModelProperty("cpcode")
        private String cpcode;

        /** 业务Code */
        @ApiModelProperty("bucode")
        private String bucode;

        public String getWeight() {
            return weight;
        }

        public String getCpname() {
            return cpname;
        }

        public String getCpcode() {
            return cpcode;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public void setBucode(String bucode) {
            this.bucode = bucode;
        }

        public String getBucode() {
            return bucode;
        }

        public void setCpname(String cpname) {
            this.cpname = cpname;
        }

        public void setCpcode(String cpcode) {
            this.cpcode = cpcode;
        }

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

    public List<SmallOrderBusinessCPVM> getLists() {
        return lists;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLists(List<SmallOrderBusinessCPVM> lists) {
        this.lists = lists;
    }

    public String getWeight() {
        return weight;
    }
}
