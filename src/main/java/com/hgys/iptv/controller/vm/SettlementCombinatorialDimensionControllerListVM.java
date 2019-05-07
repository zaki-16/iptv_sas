package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

@ApiModel("结算组合维度集合VM")
public class SettlementCombinatorialDimensionControllerListVM {

    @ApiModelProperty("主键")
    private Integer id;

    /** 维度编码 */
    @ApiModelProperty("维度编码")
    private String code;

    /** 维度名称 */
    @ApiModelProperty("维度名称")
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
    private String remakes;

    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除")
    private Integer isdelete;

    @ApiModelProperty("结算维度组合集合")
    private List<SettlementCombinatorialDimensionControllerListVM.SettlementDimension> list;

    public static class SettlementDimension{

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

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getInputTime() {
        return inputTime;
    }

    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemakes() {
        return remakes;
    }

    public void setRemakes(String remakes) {
        this.remakes = remakes;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public List<SettlementCombinatorialDimensionControllerListVM.SettlementDimension> getList() {
        return list;
    }

    public void setList(List<SettlementCombinatorialDimensionControllerListVM.SettlementDimension> list) {
        this.list = list;
    }
}
