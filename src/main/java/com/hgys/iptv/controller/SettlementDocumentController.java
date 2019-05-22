package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.SettlementDimensionControllerListVM;
import com.hgys.iptv.controller.vm.SettlementDocumentCPListVM;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SettlementDocumentService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
