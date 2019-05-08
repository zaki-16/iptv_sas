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

    /** 结算类型-订购量名称 */
    @ApiModelProperty("结算类型-订购量名称")
    @NotBlank(message = "结算类型-订购量名称不能为空")
    private String name;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("集合")
    private List<OrderQuantityWithCp> list;

    @Data
    public class OrderQuantityWithCp{
        /** 编码 */
        @ApiModelProperty("编码")
        private String code;

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
        @ApiModelProperty("oqname")
        private String oqname;

        /** 结算类型-oqcode */
        @ApiModelProperty("oqcode")
        private String oqcode;

        /** 备用字段3 */
        @ApiModelProperty("col3")
        private String col3;

        /** 是否删除 */
        @ApiModelProperty("isdelete")
        private Integer isdelete;


        public String getCode() {
            return code;
        }

        public String getCpname() {
            return cpname;
        }


        public String getOqcode() {
            return oqcode;
        }

        public String getNote() {
            return note;
        }

        public String getOqname() {
            return oqname;
        }

        public Timestamp getCreatetime() {
            return createtime;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setCpname(String cpname) {
            this.cpname = cpname;
        }



        public void setCreatetime(Timestamp createtime) {
            this.createtime = createtime;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public void setOqname(String oqname) {
            this.oqname = oqname;
        }



        public void setCol3(String col3) {
            this.col3 = col3;
        }

        public void setIsdelete(Integer isdelete) {
            this.isdelete = isdelete;
        }

        public void setCpcode(String cpcode) {
            this.cpcode = cpcode;
        }

        public void setOqcode(String oqcode) {
            this.oqcode = oqcode;
        }

        public String getCpcode() {
            return cpcode;
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

    public List<OrderQuantityWithCp> getList() {
        return list;
    }

    public void setList(List<OrderQuantityWithCp> list) {
        this.list = list;
    }
}
