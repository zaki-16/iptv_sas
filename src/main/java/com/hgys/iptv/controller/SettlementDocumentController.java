package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.SettlementDocumentCPListExcelVM;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SettlementDocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
        Sort sort = new Sort(Sort.Direction.DESC,"setStartTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize),sort);
        Page<SettlementDocumentQueryListVM> byConditions = settlementDocumentService.findByConditions(name, code, pageable);
        return byConditions;
    }

    @GetMapping("/settlementDocumentQueryCpList")
    @ApiOperation(value = "通过分账结算ID查询Cp结算信息",notes = "返回Json格式数据")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> settlementDocumentQueryCpList(@ApiParam(value = "结算分账ID",required = true) @RequestParam(value = "masterId")Integer masterId){
        return settlementDocumentService.findByIdQueryCpList(masterId);
    }

    @GetMapping("/documentHistoryQueryCpList")
    @ApiOperation(value = "通过分账结算ID查询当前账期历史结算信息",notes = "返回Json格式数据")
    @ResponseStatus(HttpStatus.OK)
    public Page<SettlementDocumentQueryListVM> documentHistoryQueryCpList(@ApiParam(value = "结算分账ID",required = true) @RequestParam(value = "masterId")Integer masterId,
                                                  @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
                                                  @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        return settlementDocumentService.documentHistoryQueryCpList(masterId,pageNum,pageSize);
    }

    @GetMapping("/settlementDocumentQueryCpMySelfList")
    @ApiOperation(value = "通过CP结算ID查询CP自己的当前账期结算信息",notes = "返回Json格式数据")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> settlementDocumentQueryCpMySelfList(@ApiParam(value = "结算Id",required = true) @RequestParam(value = "id")Integer id){
        return settlementDocumentService.settlementDocumentQueryCpMySelfList(id);
    }

    @GetMapping("/documentQueryHistoryCpMySelfList")
    @ApiOperation(value = "查询CP自己的历史结算信息",notes = "返回Json格式数据")
    @ResponseStatus(HttpStatus.OK)
    public Page<SettlementDocumentCPListExcelVM> documentQueryHistoryCpMySelfList(@ApiParam(value = "分账结算Id",required = true) @RequestParam(value = "masterId")Integer masterId,
                                                                                  @ApiParam(value = "CP编码",required = true) @RequestParam(value = "cpCode")String cpCode,
                                                                                  @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
                                                                                  @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){
        Page<SettlementDocumentCPListExcelVM> byConditions = settlementDocumentService.documentQueryHistoryCpMySelfList(masterId,cpCode,pageNum,pageSize);
        return byConditions;
    }

    @GetMapping("/excelCpSettlementInfo")
    @ApiOperation(value = "导出CP账单内的结算信息",notes = "返回Excel文件")
    public void excelCpSettlementInfo(HttpServletResponse response,@ApiParam(value = "结算ID",required = true) @RequestParam(value = "id")Integer id){
        settlementDocumentService.excelCpSettlementInfo(id,response);
    }

    @GetMapping("/excelSettlementInfoDynamic")
    @ApiOperation(value = "导出账期期间的结算信息,暂时请勿使用此接口",notes = "返回Excel文件")
    public void excelSettlementInfoDynamic(HttpServletResponse response,@ApiParam(value = "结算账单ID",required = true) @RequestParam(value = "masterId")Integer masterId) {
        settlementDocumentService.excelSettlementInfo(masterId,response);
    }

    @GetMapping("/excelSettlementInfo")
    @ApiOperation(value = "导出账期期间的结算信息,定制导出模板",notes = "返回Excel文件")
    public void excelSettlementInfo(HttpServletResponse response,@ApiParam(value = "结算账单ID",required = true) @RequestParam(value = "masterId")Integer masterId) {
        settlementDocumentService.excelSettlementInfoMode(masterId,response);
    }
}
