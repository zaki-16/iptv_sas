package com.hgys.iptv.model.bean;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
import lombok.Data;
import org.apache.poi.hssf.util.HSSFColor;

import java.math.BigDecimal;

@ExcelSheet(name = "产品级多维度结算", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class OrderProductDimensionListDTO {
    /**
     * CP编码
     */
    @ExcelField(name = "CP编码")
    private String cpcode;

    /**
     * CP名称
     */
    @ExcelField(name = "CP名称")
    private String cpname;

    /** 产品名称 */
    @ExcelField(name = "产品名称")
    private String pname;

    /** 产品编码 */
    @ExcelField(name = "产品编码")
    private String pcode;

    /**
     * 维度A编码
     */
    @ExcelField(name = "维度A编码")
    private String dimensionACode;

    /**
     * 维度B编码
     */
    @ExcelField(name = "维度B编码")
    private String dimensionBCode;

    /**
     * 维度C编码
     */
    @ExcelField(name = "维度C编码")
    private String dimensionCCode;

    /**
     * 产品结算金额
     */
    @ExcelField(name = "结算金额（单位：元）")
    private BigDecimal money;

    public String getCpcode() {
        return cpcode;
    }

    public void setCpcode(String cpcode) {
        this.cpcode = cpcode;
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getDimensionACode() {
        return dimensionACode;
    }

    public void setDimensionACode(String dimensionACode) {
        this.dimensionACode = dimensionACode;
    }

    public String getDimensionBCode() {
        return dimensionBCode;
    }

    public void setDimensionBCode(String dimensionBCode) {
        this.dimensionBCode = dimensionBCode;
    }

    public String getDimensionCCode() {
        return dimensionCCode;
    }

    public void setDimensionCCode(String dimensionCCode) {
        this.dimensionCCode = dimensionCCode;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
