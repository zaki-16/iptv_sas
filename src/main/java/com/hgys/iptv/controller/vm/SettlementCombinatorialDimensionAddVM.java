package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@ApiModel(value = "结算组合维度新增VM")
public class SettlementCombinatorialDimensionAddVM {

    @ApiModelProperty("结算组合维度ID")
    private Integer id;
    /** 维度名称 */
    @ApiModelProperty("结算组合维度名称")
    @NotBlank(message = "结算组合维度名称不能为空")
    private String name;

    @ApiModelProperty("结算组合维度备注")
    private String remakes;

    private Timestamp modifyTime;

    @ApiModelProperty("结算组合维度状态")
    private Integer status;

    @ApiModelProperty("结算维度组合集合")
    private List<SettlementDimension> list;

    @Data
    public class SettlementDimension{
        /** 维度编码 */
        @ApiModelProperty("结算维度编码")
        private String dim_code;

        /** 维度名称 */
        @ApiModelProperty("结算维度名称")
        private String dim_name;

        @ApiModelProperty("权重")
        private Integer weight;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemakes() {
        return remakes;
    }

    public void setRemakes(String remakes) {
        this.remakes = remakes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SettlementDimension> getList() {
        return list;
    }

    public void setList(List<SettlementDimension> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }
}
