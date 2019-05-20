package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@ApiModel("结算类型-产品级新增VM")
public class OrderProductWithSettlementfindVM implements Serializable {

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



    @ApiModelProperty(value = "单维度产品集合",dataType = "List")
    private List<OrderProductWithSettlementfindVM.SettlementDimension> listsd;
    public static class SettlementDimension {
        @ApiModelProperty("主键")
        private Integer id;
        /** 单维度名称 */
        @Column(name = "name", nullable = true, length = 100)
        private String name;
        /** 单维度编码 */
        @Column(name = "code", nullable = true, length = 50)
        private String code;

        @ApiModelProperty("创建时间")
        private Timestamp createtime;

        public Integer getId() {
            return id;
        }


        public Timestamp getCreatetime() {
            return createtime;
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

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public void setCreatetime(Timestamp createtime) {
            this.createtime = createtime;
        }
    }







    @ApiModelProperty(value = "多维度产品集合",dataType = "List")
    private List<OrderProductWithSettlementfindVM.OrderProductWithSCDAddLIstVMs> list;
    public static class OrderProductWithSCDAddLIstVMs {
        @ApiModelProperty("主键")
        private Integer id;
        /** 产品名称 */
        @Column(name = "pname", nullable = true, length = 100)
        private String pname;

        /** 产品编码 */
        @Column(name = "pcode", nullable = true, length = 50)
        private String pcode;

        /** 结算类型-产品级名称 */
        @Column(name = "opname", nullable = true, length = 100)
        private String opname;

        /** 结算类型-产品级编码 */
        @Column(name = "opcode", nullable = true, length = 50)
        private String opcode;

        @ApiModelProperty("创建时间")
        private Timestamp createtime;
        @ApiModelProperty(value = "产品下的CP集合",dataType = "List")
        private List<OrderProductWithSettlementfindVM.OrderProductWithSCDAddLIstVMs.OrderProductCPWithSCDAddLIstVMs> listcp;
        public static class OrderProductCPWithSCDAddLIstVMs {
            @ApiModelProperty("主键")
            private Integer id;

            /** 产品ID */
            @Column(name = "pid", nullable = true, length = 11)
            private Integer pid;

            /** CPID */
            @Column(name = "cpid", nullable = true, length = 11)
            private Integer cpid;

            /** cp名称 */
            @Column(name = "cpname", nullable = true, length = 100)
            private String cpname;

            /** CPcode */
            @Column(name = "cpcode", nullable = true, length = 50)
            private String cpcode;

            @ApiModelProperty("创建时间")
            private Timestamp createtime;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public void setCpname(String cpname) {
                this.cpname = cpname;
            }

            public void setCpcode(String cpcode) {
                this.cpcode = cpcode;
            }

            public Integer getPid() {
                return pid;
            }

            public Integer getCpid() {
                return cpid;
            }

            public void setPid(Integer pid) {
                this.pid = pid;
            }

            public void setCpid(Integer cpid) {
                this.cpid = cpid;
            }

            public String getCpname() {
                return cpname;
            }

            public String getCpcode() {
                return cpcode;
            }

            public Timestamp getCreatetime() {
                return createtime;
            }

            public void setCreatetime(Timestamp createtime) {
                this.createtime = createtime;
            }




        }
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public Timestamp getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Timestamp createtime) {
            this.createtime = createtime;
        }

        public String getPname() {
            return pname;
        }

        public String getPcode() {
            return pcode;
        }

        public String getOpcode() {
            return opcode;
        }

        public String getOpname() {
            return opname;
        }

        public void setListcp(List<OrderProductWithSettlementfindVM.OrderProductWithSCDAddLIstVMs.OrderProductCPWithSCDAddLIstVMs> listcp) {
            this.listcp = listcp;
        }

        public List<OrderProductWithSettlementfindVM.OrderProductWithSCDAddLIstVMs.OrderProductCPWithSCDAddLIstVMs> getListcp() {
            return listcp;
        }

        public void setOpcode(String opcode) {
            this.opcode = opcode;
        }

        public void setOpname(String opname) {
            this.opname = opname;
        }

    }
    @ApiModelProperty(value = "多维度里面的单维度集合",dataType = "List")
    private List<OrderProductWithSettlementfindVM.SettlementCombinatorialDimensionFrom> listscd;

    public static class SettlementCombinatorialDimensionFrom{


        /** 维度编码 */
        @ApiModelProperty("结算维度ID")
        private Integer id;

        /** 维度编码 */
        @ApiModelProperty("结算维度编码")
        private String dim_code;

        /** 维度名称 */
        @ApiModelProperty("结算维度名称")
        private String dim_name;

        @ApiModelProperty("权重")
        private Integer weight;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDim_code() {
            return dim_code;
        }

        public void setDim_code(String dim_code) {
            this.dim_code = dim_code;
        }

        public String getDim_name() {
            return dim_name;
        }

        public void setDim_name(String dim_name) {
            this.dim_name = dim_name;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public Integer getWeight() {
            return weight;
        }
    }


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

    public List<SettlementDimension> getListsd() {
        return listsd;
    }



    public List<OrderProductWithSCDAddLIstVMs> getList() {
        return list;
    }



    public void setList(List<OrderProductWithSCDAddLIstVMs> list) {
        this.list = list;
    }

    public List<SettlementCombinatorialDimensionFrom> getListscd() {
        return listscd;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setListsd(List<SettlementDimension> listsd) {
        this.listsd = listsd;
    }



    public void setListscd(List<SettlementCombinatorialDimensionFrom> listscd) {
        this.listscd = listscd;
    }
}
