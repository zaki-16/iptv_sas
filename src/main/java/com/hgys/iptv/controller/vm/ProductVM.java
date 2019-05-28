package com.hgys.iptv.controller.vm;

import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName ProductVM
 * @Auther: wangz
 * @Date: 2019/5/13 17:55
 * @Description: TODO
 */
@Data
public class ProductVM {

    private Integer id;
    private String name;
    private String code;
    private String price;
    private String prodNum;
    private Timestamp inputTime;
    private Timestamp modifyTime;
    private Integer status;//0.禁用 1.启用
    private Integer isdelete;//0：未删除 1：已删除

    private List<Business> bList;
    private List<Cp> cpList;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public List<Business> getbList() {
        return bList;
    }

    public void setbList(List<Business> bList) {
        this.bList = bList;
    }

    public List<Cp> getCpList() {
        return cpList;
    }

    public void setCpList(List<Cp> cpList) {
        this.cpList = cpList;
    }
}
