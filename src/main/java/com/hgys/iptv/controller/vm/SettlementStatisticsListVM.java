package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@ApiModel("集合VM")
public class SettlementStatisticsListVM {

    /** 名称 */
    @ApiModelProperty("名称")
    private String name;

    /** 编码 */
    @ApiModelProperty("编码")
    private String code;

    /** Id */
    @ApiModelProperty("id")
    private Integer id;

    /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算 */
    @ApiModelProperty("1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算")
    private Integer set_type;

    /** 结算规则编码 */
    @ApiModelProperty("结算规则编码")
    private String set_ruleCode;

    /** 结算规则ID */
    @ApiModelProperty("结算规则ID")
    private Integer set_ruleId;

    /** 结算规则名称 */
    @ApiModelProperty("结算规则名称")
    private String set_ruleName;

    /** 1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回 */
    @ApiModelProperty("1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remakes;

    /** 结算开始时间 */
    @ApiModelProperty("结算开始时间(2019-01-01 12:12:12)")
    private String startTime;

    /** 录入时间 */
    @ApiModelProperty("录入时间")
    private  Timestamp inputTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;

    /** 结算结束时间 */
    @ApiModelProperty("结算结束时间(2019-01-01 12:12:12)")
    private String endTime;

    /**分配结算业务级结算总收入*/
    @ApiModelProperty("分配结算业务级结算总收入")
    private BigDecimal businessMoney;

    /**分配结算订购量结算总收入*/
    @ApiModelProperty("分配结算订购量结算总收入")
    private BigDecimal orderMoney;

    /**分配结算CP定比例结算总收入*/
    @ApiModelProperty("分配结算CP定比例结算总收入")
    private BigDecimal cpAllMoney;
    /**分配结算订购量信息集合*/
    @ApiModelProperty("分配结算订购量信息集合")
    private List<CpOrderCpAddVM> cpAddVMS;

    /**分配结算产品级单维度结算信息集合*/
    @ApiModelProperty("分配结算产品级单维度信息集合")
    private List<OrderProductDimensionAddVM> dimensionAddVM;

    /**分配结算产品级多维度信息集合*/
    @ApiModelProperty("分配结算产品级多维度信息集合")
    private List<OrderProductDimensionListAddVM> dimensionListAddVMS;

    /**分配结算业务定比例结算信息集合*/
    @ApiModelProperty("分配结算业务定比例结算信息集合")
    private List<BusinessBelielAddVM> belielAddVMS;

    /**订购量*/
    @ApiModelProperty("订购量集合")
    private List<OrderQuantityAddVM> OrderQuantityAddVM;


    /**产品级*/
    @ApiModelProperty("产品级集合")
    private List<OrderProductWithSettlementfindVM> OrderProductWithSettlementfindVM;


    /**CP定比例*/
    @ApiModelProperty("CP定比例集合")
    private List<OrderCPWithCPListVM> OrderCPWithCPListVM;


    /**业务级*/
    @ApiModelProperty("业务级集合")
    private List<OrderBusinessWithCPAddVM> OrderBusinessWithCPAddVM;



    /**业务定比例*/
    @ApiModelProperty("业务定比例集合")
    private List<OrderBusinessComparisonQueryVM> OrderBusinessComparisonQueryVM;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSet_ruleId() {
        return set_ruleId;
    }

    public void setSet_ruleId(Integer set_ruleId) {
        this.set_ruleId = set_ruleId;
    }

    public String getSet_ruleName() {
        return set_ruleName;
    }

    public void setSet_ruleName(String set_ruleName) {
        this.set_ruleName = set_ruleName;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getBusinessMoney() {
        return businessMoney;
    }

    public void setBusinessMoney(BigDecimal businessMoney) {
        this.businessMoney = businessMoney;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getCpAllMoney() {
        return cpAllMoney;
    }

    public void setCpAllMoney(BigDecimal cpAllMoney) {
        this.cpAllMoney = cpAllMoney;
    }

    public List<CpOrderCpAddVM> getCpAddVMS() {
        return cpAddVMS;
    }

    public void setCpAddVMS(List<CpOrderCpAddVM> cpAddVMS) {
        this.cpAddVMS = cpAddVMS;
    }

    public List<OrderProductDimensionAddVM> getDimensionAddVM() {
        return dimensionAddVM;
    }

    public void setDimensionAddVM(List<OrderProductDimensionAddVM> dimensionAddVM) {
        this.dimensionAddVM = dimensionAddVM;
    }

    public List<OrderProductDimensionListAddVM> getDimensionListAddVMS() {
        return dimensionListAddVMS;
    }

    public void setDimensionListAddVMS(List<OrderProductDimensionListAddVM> dimensionListAddVMS) {
        this.dimensionListAddVMS = dimensionListAddVMS;
    }

    public List<BusinessBelielAddVM> getBelielAddVMS() {
        return belielAddVMS;
    }

    public void setBelielAddVMS(List<BusinessBelielAddVM> belielAddVMS) {
        this.belielAddVMS = belielAddVMS;
    }

    public List<com.hgys.iptv.controller.vm.OrderQuantityAddVM> getOrderQuantityAddVM() {
        return OrderQuantityAddVM;
    }

    public void setOrderQuantityAddVM(List<com.hgys.iptv.controller.vm.OrderQuantityAddVM> orderQuantityAddVM) {
        OrderQuantityAddVM = orderQuantityAddVM;
    }

    public List<com.hgys.iptv.controller.vm.OrderProductWithSettlementfindVM> getOrderProductWithSettlementfindVM() {
        return OrderProductWithSettlementfindVM;
    }

    public void setOrderProductWithSettlementfindVM(List<com.hgys.iptv.controller.vm.OrderProductWithSettlementfindVM> orderProductWithSettlementfindVM) {
        OrderProductWithSettlementfindVM = orderProductWithSettlementfindVM;
    }

    public List<com.hgys.iptv.controller.vm.OrderCPWithCPListVM> getOrderCPWithCPListVM() {
        return OrderCPWithCPListVM;
    }

    public void setOrderCPWithCPListVM(List<com.hgys.iptv.controller.vm.OrderCPWithCPListVM> orderCPWithCPListVM) {
        OrderCPWithCPListVM = orderCPWithCPListVM;
    }

    public List<com.hgys.iptv.controller.vm.OrderBusinessWithCPAddVM> getOrderBusinessWithCPAddVM() {
        return OrderBusinessWithCPAddVM;
    }

    public void setOrderBusinessWithCPAddVM(List<com.hgys.iptv.controller.vm.OrderBusinessWithCPAddVM> orderBusinessWithCPAddVM) {
        OrderBusinessWithCPAddVM = orderBusinessWithCPAddVM;
    }

    public List<com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM> getOrderBusinessComparisonQueryVM() {
        return OrderBusinessComparisonQueryVM;
    }

    public void setOrderBusinessComparisonQueryVM(List<com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM> orderBusinessComparisonQueryVM) {
        OrderBusinessComparisonQueryVM = orderBusinessComparisonQueryVM;
    }
}
