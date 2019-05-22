package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

@ApiModel(value = "结算文档列表页查询VM")
public class SettlementDocumentQueryListVM {

    /** id */
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("结算开始时间")
    private Timestamp setStartTime;

    @ApiModelProperty("结算结束时间")
    private Timestamp setEndTime;

    /** 分账结算名称 */
    @ApiModelProperty("分账结算名称")
    private String name;

    /** 编码 */
    @ApiModelProperty("分账结算编码")
    private String code;

    /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算 */
    @ApiModelProperty("1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算")
    private Integer set_type;

    /** 结算规则编码 */
    @ApiModelProperty("结算规则编码")
    private String set_ruleCode;

    /** 1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回 */
    @ApiModelProperty("1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remakes;

    @ApiModelProperty("CP信息集合")
    private List<SettlementDocumentCPListVM> cpList;

    public Timestamp getSetStartTime() {
        return setStartTime;
    }

    public void setSetStartTime(Timestamp setStartTime) {
        this.setStartTime = setStartTime;
    }

    public Timestamp getSetEndTime() {
        return setEndTime;
    }

    public void setSetEndTime(Timestamp setEndTime) {
        this.setEndTime = setEndTime;
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

    public Integer getSet_type() {
        return set_type;
    }

    public void setSet_type(Integer set_type) {
        this.set_type = set_type;
    }

    public String getSet_ruleCode() {
        return set_ruleCode;
    }

    public void setSet_ruleCode(String set_ruleCode) {
        this.set_ruleCode = set_ruleCode;
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

    public List<SettlementDocumentCPListVM> getCpList() {
        return cpList;
    }

    public void setCpList(List<SettlementDocumentCPListVM> cpList) {
        this.cpList = cpList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
