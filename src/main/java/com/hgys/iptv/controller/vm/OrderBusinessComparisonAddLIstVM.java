package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("业务类型-业务定比例新增VM(cp关系)")
public class OrderBusinessComparisonAddLIstVM{

    @ApiModelProperty("主键")
    private Integer id;
    /** 业务定比例比编码 */
    @ApiModelProperty("业务定比例比编码")
    private String masterCode;

    /** cp编码 */
    @ApiModelProperty("cp编码")
    private String cp_code;

    /** cp名称 */
    @ApiModelProperty("cp名称")
    private String cp_name;

    /** 所占比例 */
    @ApiModelProperty("所占比例")
    private Integer proportion;

    /** 金额 */
    @ApiModelProperty("金额")
    private Integer money;

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public String getCp_code() {
        return cp_code;
    }

    public void setCp_code(String cp_code) {
        this.cp_code = cp_code;
    }

    public String getCp_name() {
        return cp_name;
    }

    public void setCp_name(String cp_name) {
        this.cp_name = cp_name;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
