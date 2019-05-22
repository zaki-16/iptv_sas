package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.SettlementDocumentCPListExcelVM;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.model.bean.SettlementDocumentCpDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SettlementDocumentService;
import com.hgys.iptv.util.excel.ExcelForWebUtil;
import com.xuxueli.poi.excel.ExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangpeng
 */
@RestController
@RequestMapping("/settlementDocumentController")
@Api(value = "settlementDocumentController",tags = "结算文档Api接口")
public class SettlementDocumentController {

    @Autowired
    private SettlementDocumentService settlementDocumentService;

    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询",notes = "返回Json格式数据")
    @ResponseStatus(HttpStatus.OK)
    public Page<SettlementDocumentQueryListVM> findByConditions(@ApiParam(value = "结算分账名称") @RequestParam(value = "name",required = false )String name,
                                                                      @ApiParam(value = "结算分账编码") @RequestParam(value = "code",required = false)String code,
                                                                      @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
                                                                      @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize));
        Page<SettlementDocumentQueryListVM> byConditions = settlementDocumentService.findByConditions(name, code, pageable);
        return byConditions;
    }

    @GetMapping("/SettlementDocumentQueryCpList")
    @ApiOperation(value = "通过分账结算id查询Cp结算信息",notes = "返回Json格式数据")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> SettlementDocumentQueryCpList(@ApiParam(value = "结算分账ID",required = true) @RequestParam(value = "id")Integer id){
        return settlementDocumentService.findByIdQueryCpList(id);
    }

    @GetMapping("/SettlementDocumentQueryCpMySelfList")
    @ApiOperation(value = "通过CP编码查询CP自己的结算信息",notes = "返回Json格式数据")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> SettlementDocumentQueryCpMySelfList(@ApiParam(value = "CP编码",required = true) @RequestParam(value = "cpCode")String cpCode){
        return settlementDocumentService.settlementDocumentQueryCpMySelfList(cpCode);
    }

    @GetMapping("/excelCpSettlementInfo")
    @ApiOperation(value = "导出CP账单内的结算信息",notes = "返回Excel文件")
    public void excelCpSettlementInfo(HttpServletResponse response,@ApiParam(value = "结算ID",required = true) @RequestParam(value = "id")Integer id) throws IOException {
        List<SettlementDocumentCpDTO> dtos = new ArrayList<>();
        SettlementDocumentCPListExcelVM vm = settlementDocumentService.settlementCpExcel(id);
        /** 结算类型：1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算 */
        Map<String, Object> beanParams = new HashMap<>();
        if (null != vm){
            SettlementDocumentCpDTO dto = new SettlementDocumentCpDTO();
            dto.setCpcode(vm.getCpcode());
            dto.setCpname(vm.getCpname());
            if (1 == vm.getType()){
                dto.setType("订购量结算");
            }else if (2 == vm.getType()){
                dto.setType("业务级结算");
            }else if (3 == vm.getType()){
                dto.setType("产品级结算");
            }else if (4 == vm.getType()){
                dto.setType("CP定比例结算");
            }else if (5 == vm.getType()){
                dto.setType("业务定比例结算");
            }
            //结算账期
            String startTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(vm.getSetStartTime());
            String endTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(vm.getSetEndTime());
            dto.setSetTime(startTime + "-" + endTime);
            dto.setSettlementMoney(vm.getSettlementMoney().toString());
            dto.setCreateTime(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(vm.getCreateTime()));
            dto.setMasterCode(vm.getMasterCode());
            dto.setMasterName(vm.getMasterName());
            dto.setProductCode(vm.getProductCode());
            dto.setProductName(vm.getProductName());
            dto.setBusinessCode(vm.getBusinessCode());
            dto.setBusinessName(vm.getBusinessName());

            /** 1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回;7:已结算*/
            if (1 == vm.getStatus()){
                dto.setStatus("已录入");
            }else if (2 == vm.getStatus()){
                dto.setStatus("待审核");
            }else if (3 == vm.getStatus()){
                dto.setStatus("初审通过");
            }else if (4 == vm.getStatus()){
                dto.setStatus("复审通过");
            }else if (5 == vm.getStatus()){
                dto.setStatus("终审通过");
            }else if (6 == vm.getStatus()){
                dto.setStatus("驳回");
            }else if (7 == vm.getStatus()){
                dto.setStatus("已结算");
            }

            dtos.add(dto);
            //浏览器返回Excel
            Workbook sheets = ExcelExportUtil.exportWorkbook(dtos);
            ExcelForWebUtil.workBookExportExcel(response,sheets,"Cp结算信息表");
        }

    }
}
