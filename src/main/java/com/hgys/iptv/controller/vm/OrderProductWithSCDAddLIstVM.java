package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

@ApiModel("结算类型-产品级新增VM(单纬度维度,多维度关系)")
public class OrderProductWithSCDAddLIstVM {

    @ApiModelProperty("主键")
    private Integer id;

    /** 单维度名称 */
    @Column(name = "sdname", nullable = true, length = 100)
    private String sdname;

    /** 单维度编码 */
    @Column(name = "sdcode", nullable = true, length = 50)
    private String sdcode;


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

    /** 多维度名称 */
    @Column(name = "scdname", nullable = true, length = 100)
    private String scdname;

    /** 多维度编码 */
    @Column(name = "scdcode", nullable = true, length = 50)
    private String scdcode;

    public Integer getId() {
        return id;
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

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
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

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public void setOpname(String opname) {
        this.opname = opname;
    }
}
