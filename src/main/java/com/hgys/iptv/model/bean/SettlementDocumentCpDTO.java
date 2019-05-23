package com.hgys.iptv.model.bean;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
import org.apache.poi.hssf.util.HSSFColor;

import java.math.BigDecimal;

@ExcelSheet(name = "Cp结算信息", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class SettlementDocumentCpDTO {

    @ExcelField(name = "结算账期")
    private String setTime;

    @ExcelField(name = "结算状态")
    private String status;

    @ExcelField(name = "结算类型")
    /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算 */
    private String type;

    @ExcelField(name = "分账结算编码")
    private String masterCode;

    /** 分账结算名称 */
    @ExcelField(name = "分账结算名称")
    private String masterName;

    /** cp编码 */
    @ExcelField(name = "cp编码")
    private String cpcode;

    /** cp名称 */
    @ExcelField(name = "cp名称")
    private String cpname;

    /** 产品编码 */
    @ExcelField(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @ExcelField(name = "产品名称")
    private String productName;

    /** 业务编码 */
    @ExcelField(name = "业务编码")
    private String businessCode;

    /** 业务名称 */
    @ExcelField(name = "业务名称")
    private String businessName;

    /** 结算金额 */
    @ExcelField(name = "结算金额")
    private String settlementMoney;

    /** 创建时间 */
    @ExcelField(name = "创建时间")
    private String createTime;

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getSettlementMoney() {
        return settlementMoney;
    }

    public void setSettlementMoney(String settlementMoney) {
        this.settlementMoney = settlementMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
