package com.hgys.iptv.controller;

import cn.afterturn.easypoi.entity.vo.MapExcelConstants;
import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.hgys.iptv.exception.BaseException;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.bean.ShopDTO;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SettlementDimensionService;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.excel.ExcelForWebUtil;
import com.hgys.iptv.util.excel.PathConstant;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xuxueli.poi.excel.ExcelExportUtil;
import com.xuxueli.poi.excel.ExcelImportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/demo")
@Api(value = "Demo",tags = "demo演示接口")
public class DemoContronller {
    @Autowired
    private SettlementDimensionService settlementDimensionService;

    @GetMapping("demoReturn")
    @ApiOperation(value = "演示接口",notes = "返回json数据类型")
    public ResultVO<?> signBaseAgreement(@ApiParam(value = "用户名称",required = true) @RequestParam("name")String name) {
        /**
         * 抛异常处理
         */
        if (StringUtils.isBlank(name)){
            throw new BaseException(ResultEnum.ACCESS_DENIED_ERROR);
        }

        /**
         * 处理成功情况
         */
        if (true){
            //success方法对象为T型，Object、List等查询的对象都可放入其中
            //return ResultVOUtil.success(Boolean.TRUE);
            return ResultVOUtil.success(name);
        }
        /**
         * 处理失败情况
         */
        return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);

    }

    @GetMapping("/excel")
    @ApiOperation(value = "excel导出模板方法一",notes = "返回Excel文件")
    public void excel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> beanParams = new HashMap<>();
        List<Map> l = new ArrayList<>();
        for(int i=1;i<29;i++){
            Map<String,String> a = new HashMap<>();
            a.put("name","名称："+i);
            a.put("code","编码："+i);
            l.add(a);
        }
        beanParams.put("pList",l);
        ExcelForWebUtil.exportExcel(response,beanParams,"test.xlsx", PathConstant.getExcelExportResource(),"导出测试文件.xlsx");

    }

    @GetMapping("/test")
    @ApiOperation(value = "QueryDSL  SQL方法测试",notes = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Page<SettlementDimension> updateSettlementDimension(){
        return settlementDimensionService.test();
    }

    @GetMapping("/testExcel")
    @ApiOperation(value = "Excel导入导出Demo",notes = "返回Excel")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ShopDTO> testExcel(HttpServletResponse response){
        //模拟假数据
        List<ShopDTO> shopDTOList = new ArrayList<ShopDTO>();
        for (int i = 0; i < 100; i++) {
            ShopDTO shop = new ShopDTO(true, "商户"+i, (short) i, 1000+i, 10000+i, (float) (1000+i), (double) (10000+i), new Date());
            shopDTOList.add(shop);
        }

        /**
         * Excel导出：Object 转换为 Excel
         */
//        //自己硬盘的地址
//        String filePath = "/Users/yance/Downloads/demo-sheet.xls";
//        PathConstant.getExcelExportResource();

//        ExcelExportUtil.exportToFile(filePath, shopDTOList);

        //浏览器返回Excel
        Workbook sheets = ExcelExportUtil.exportWorkbook(shopDTOList);
        ExcelForWebUtil.workBookExportExcel(response,sheets,"Excel导出测试文件");

//        /**
//         * Excel导入：Excel 转换为 Object
//         */
//        List<Object> list = ExcelImportUtil.importExcel(filePath, ShopDTO.class);

//        System.out.println(list);
        return shopDTOList;
    }

    @GetMapping("/testExcelExportEntity")
    @ApiOperation(value = "测试ExcelExportEntity动态方式导出数据",notes = "返回Excel")
    public void dynaCol(HttpServletResponse response) {
        try {
            List<ExcelExportEntity> colList = new ArrayList<>();
            ExcelExportEntity colEntity = new ExcelExportEntity("商品名称", "title");
            colEntity.setNeedMerge(true);
            colList.add(colEntity);

            colEntity = new ExcelExportEntity("供应商", "supplier");
            colEntity.setNeedMerge(true);
            colList.add(colEntity);

            ExcelExportEntity deliColGroup = new ExcelExportEntity("得力", "deli");
            List<ExcelExportEntity> deliColList = new ArrayList<>();
            deliColList.add(new ExcelExportEntity("市场价", "orgPrice"));
            deliColList.add(new ExcelExportEntity("专区价", "salePrice"));
            deliColGroup.setList(deliColList);
            colList.add(deliColGroup);

            ExcelExportEntity jdColGroup = new ExcelExportEntity("京东", "jd");
            List<ExcelExportEntity> jdColList = new ArrayList<>();
            jdColList.add(new ExcelExportEntity("市场价", "orgPrice"));
            jdColList.add(new ExcelExportEntity("专区价", "salePrice"));
            jdColGroup.setList(jdColList);
            colList.add(jdColGroup);


            List<Map<String, Object>> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Map<String, Object> valMap = new HashMap<>();
                valMap.put("title", "名称." + i);
                valMap.put("supplier", "供应商." + i);

                List<Map<String, Object>> deliDetailList = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    Map<String, Object> deliValMap = new HashMap<>();
                    deliValMap.put("orgPrice", "得力.市场价." + j);
                    deliValMap.put("salePrice", "得力.专区价." + j);
                    deliDetailList.add(deliValMap);
                }
                valMap.put("deli", deliDetailList);

                List<Map<String, Object>> jdDetailList = new ArrayList<>();
                for (int j = 0; j < 2; j++) {
                    Map<String, Object> jdValMap = new HashMap<>();
                    jdValMap.put("orgPrice", "京东.市场价." + j);
                    jdValMap.put("salePrice", "京东.专区价." + j);
                    jdDetailList.add(jdValMap);
                }
                valMap.put("jd", jdDetailList);

                list.add(valMap);
            }

            Workbook workbook = cn.afterturn.easypoi.excel.ExcelExportUtil.exportExcel(new ExportParams("价格分析表", "数据"), colList,
                    list);
            ExcelForWebUtil.workBookExportExcel(response,workbook,"test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
