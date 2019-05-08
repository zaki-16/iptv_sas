package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

@ApiModel("业务类型-业务定比例查询VM")
public class OrderBusinessComparisonQueryVM {

    @ApiModelProperty("主键")
    private Integer id;
    /** 名称 */
    @ApiModelProperty("业务定比例名称")
    private String name;

    @ApiModelProperty("业务定比例编码")
    private String code;

    /** 结算方式(1:按比例结算;2:按金额结算) */
    @ApiModelProperty("结算方式(1:按比例结算;2:按金额结算)")
    private Integer mode;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remakes;

    /** 业务编码 */
    @ApiModelProperty("业务编码")
    private String businessCode;

    /** 业务名称 */
    @ApiModelProperty("业务名称")
    private String businessName;

    /** 录入时间 */
    @ApiModelProperty("录入时间")
    private Timestamp inputTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;


    @ApiModelProperty("cp信息集合")
    private List<OrderBusinessComparisonAddLIstVM> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
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

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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

    public List<OrderBusinessComparisonAddLIstVM> getList() {
        return list;
    }

    public void setList(List<OrderBusinessComparisonAddLIstVM> list) {
        this.list = list;
    }
}
