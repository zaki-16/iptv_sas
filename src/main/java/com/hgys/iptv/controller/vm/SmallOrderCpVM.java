package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

public class SmallOrderCpVM {

    /** CP编码 */
    @ApiModelProperty("CP编码")
    private String cpcode;

    /** CP名称 */
    @ApiModelProperty("CP名称")
    private String cpname;

    /** orderquantity名称 */
    @ApiModelProperty("oqname")
    private String oqname;
    /** orderquantity名称 */
    @ApiModelProperty("createtime")
    private Timestamp createtime;
    /** orderquantity oqcode */
    @ApiModelProperty("oqcode")
    private String oqcode;

    public String getCpcode() {
        return cpcode;
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpcode(String cpcode) {
        this.cpcode = cpcode;
    }

    public void setOqname(String oqname) {
        this.oqname = oqname;
    }

    public void setOqcode(String oqcode) {
        this.oqcode = oqcode;
    }

    public String getOqname() {
        return oqname;
    }

    public String getOqcode() {
        return oqcode;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }
}
