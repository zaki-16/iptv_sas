package com.hgys.iptv.model.bean;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
import lombok.Data;
import org.apache.poi.hssf.util.HSSFColor;

import java.math.BigDecimal;

@ExcelSheet(name = "产品级多维度结算", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
@Data
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
}
