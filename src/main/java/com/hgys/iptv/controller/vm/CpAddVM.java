package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "cp新增VM")
@Data
public class CpAddVM {
    /**
     * CP名称（必填，列表展示）、
     * CP简称（非必填，列表展示）、
     * CP编码（系统生成，列表不展示）、
     * 所属产品（必填，列表展示）、
     * CP联系人（非必填，列表不展示）、
     * CP联系方式（手机号）（非必填，列表不展示）、
     * CP联系方式（邮箱）（非必填，列表不展示）、
     * 注册时间（系统获取，列表展示）、
     * 修改时间（最后修改时间）（系统获取，列表展示）、
     * 状态（必填，列表展示）、
     * 注销时间（非必填，列表展示）、
     * 备注（非必填，列表不展示）
     */
    @ApiModelProperty("主键，新增时填写无效")
    private Integer id;

    @ApiModelProperty("名称") @NotBlank(message = "不能为空")
    private String name;

    @ApiModelProperty("cp简称")
    private String cpAbbr;

    @ApiModelProperty("状态")@NotBlank(message = "不能为空")
    private Integer status;

    @ApiModelProperty("联系人")@NotBlank(message = "不能为空")
    private String contactNm;

    @ApiModelProperty("手机号")
    private String contactTel;

    @ApiModelProperty("邮箱")
    private String contactMail;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty(value = "cp关联的产品集合id字符串")//dataType = "List"
    private String pids;

    @ApiModelProperty(value = "cp关联的业务集合id字符串")//dataType = "List"
    private String bids;

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

    public String getCpAbbr() {
        return cpAbbr;
    }

    public void setCpAbbr(String cpAbbr) {
        this.cpAbbr = cpAbbr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContactNm() {
        return contactNm;
    }

    public void setContactNm(String contactNm) {
        this.contactNm = contactNm;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public String getBids() {
        return bids;
    }

    public void setBids(String bids) {
        this.bids = bids;
    }
}
