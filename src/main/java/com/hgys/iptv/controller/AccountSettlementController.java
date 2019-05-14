package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.AccountSettlementAddVM;
import com.hgys.iptv.model.OrderProduct;
import com.hgys.iptv.model.bean.CpOrderCpExcelDTO;
import com.hgys.iptv.model.bean.OrderProductDimensionDTO;
import com.hgys.iptv.model.bean.OrderProductDimensionListDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.OrderProductRepository;
import com.hgys.iptv.service.AccountSettlementService;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.excel.ExcelForWebUtil;
import com.xuxueli.poi.excel.ExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController()
@RequestMapping("/accountSettlementController")
@Api(value = "accountSettlementController",tags = "分账结算Api接口")
public class AccountSettlementController {

    @Autowired
    private AccountSettlementService accountSettlementService;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @RequestMapping(value = "/accountSettlement", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ApiOperation(value = "新增分配结算",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> addAccountSettlement(@ApiParam(value = "新增分配结算VM") @ModelAttribute AccountSettlementAddVM vm){

        if (StringUtils.isBlank("")){
            return ResultVOUtil.error("1","分配结算name不能为空");
        }

        if (StringUtils.isBlank("")){
            return ResultVOUtil.error("1","分配结算setRuleCode不能为空");
        }

        return accountSettlementService.addAccountSettlement();
    }

    @GetMapping("/excelExport")
    @ApiOperation(value = "根据选择查询数据并返回excel",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    public void excelExport(@ApiParam(value = "分配结算类型",required = true) @RequestParam(value = "type") Integer type,
                            @ApiParam(value = "编码",required = true) @RequestParam(value = "code") String code,
                            HttpServletResponse response){


        if (type == 1){
            List<CpOrderCpExcelDTO> dtos = (List<CpOrderCpExcelDTO>) accountSettlementService.excelExport(type, code);
            //浏览器返回Excel
            Workbook sheets = ExcelExportUtil.exportWorkbook(dtos);
            ExcelForWebUtil.workBookExportExcel(response,sheets,"订购量结算");
        }else if (type == 3){
            //查询是单维度还是多维度
            OrderProduct byCode = orderProductRepository.findByCode(code);
            if (byCode.getMode() == 1){
                List<OrderProductDimensionDTO> result = (List<OrderProductDimensionDTO>) accountSettlementService.excelExport(type,code);
                //浏览器返回Excel
                Workbook sheets = ExcelExportUtil.exportWorkbook(result);
                ExcelForWebUtil.workBookExportExcel(response,sheets,"产品级单维度结算");
            }else {
                List<OrderProductDimensionListDTO> result = (List<OrderProductDimensionListDTO>) accountSettlementService.excelExport(type,code);
                //浏览器返回Excel
                Workbook sheets = ExcelExportUtil.exportWorkbook(result);
                ExcelForWebUtil.workBookExportExcel(response,sheets,"产品级多维度结算");
            }

        }

    }

}
