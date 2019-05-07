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

    /** 备注 */
    @ApiModelProperty("备注")
    private String note;

    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除")
    private Integer isdelete;

    @ApiModelProperty("结算类型-CP定比例与CP集合")
    private List<OrderCPWithCPListVM.OrderQuantityWithCp> list;

    public static class OrderQuantityWithCp{

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
        private String cpid;

        public Integer getId() {
            return id;
        }

        public String getCode() {
            return code;
        }

        public String getCpname() {
            return cpname;
        }

        public String getCpid() {
            return cpid;
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

        public void setCpid(String cpid) {
            this.cpid = cpid;
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

    public List<OrderCPWithCPListVM.OrderQuantityWithCp> getList() {
        return list;
    }

    public void setList(List<OrderCPWithCPListVM.OrderQuantityWithCp> list) {
        this.list = list;
    }
}
