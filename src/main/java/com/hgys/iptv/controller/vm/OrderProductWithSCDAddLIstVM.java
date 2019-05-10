package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.sql.Timestamp;

@ApiModel("结算类型-产品级新增VM(单纬度维度,多维度关系)")
public class OrderProductWithSCDAddLIstVM {

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

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public void setOpname(String opname) {
        this.opname = opname;
    }
}
