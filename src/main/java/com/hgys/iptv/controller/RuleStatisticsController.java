package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.ProductLevelStatisticsVM;
import com.hgys.iptv.controller.vm.SettlementStatisticsListVM;
import com.hgys.iptv.controller.vm.SettlementStatisticsListVMS;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.RuleStatisticsService;
import com.hgys.iptv.service.SettlementStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rulestatistics")
@Api(value = "rulestatistics",tags = "规则统计")
public class RuleStatisticsController {
    @Autowired
    private RuleStatisticsService ruleStatisticsService;

    @GetMapping("/findrulesettlement")
    @ApiOperation(value = "通过条件，查询",notes = "返回处理结果")
    @ResponseStatus(HttpStatus.OK)
    public SettlementStatisticsListVM findsettlement(AccountSettlement a){
        return ruleStatisticsService.findsettlement(a);

    }
    @GetMapping("/findsettlement")
    @ApiOperation(value = "通过条件，查询",notes = "返回处理结果")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO findsettlementList(@ApiParam(value = "名称") @RequestParam(value = "name",required = false )String name,
                                   @ApiParam(value = "结算开始时间") @RequestParam(value = "startTime",required = false)String startTime,
                                   @ApiParam(value = "结算结束时间") @RequestParam(value = "endTime",required = false)String endTime,
                                   @ApiParam(value = "结算类型") @RequestParam(value = "set_type",required = false)String set_type,
                                   @ApiParam(value = "结算方式") @RequestParam(value = "set_ruleName",required = false)String set_ruleName){
        return ruleStatisticsService.findsettlementList(name, startTime, endTime,set_type,set_ruleName );

    }


    @GetMapping("/LevelStatistics")
    @ApiOperation(value = "统计产品级数据",notes = "Json数据")
    public List<SettlementStatisticsListVMS> LevelStatistics(@ApiParam(value = "名称") @RequestParam(value = "name",required = false )String name,
                                                             @ApiParam(value = "结算开始时间") @RequestParam(value = "startTime",required = false)String startTime,
                                                             @ApiParam(value = "结算结束时间") @RequestParam(value = "endTime",required = false)String endTime,
                                                             @ApiParam(value = "结算类型") @RequestParam(value = "set_type",required = false)String set_type,
                                                             @ApiParam(value = "结算方式") @RequestParam(value = "set_ruleName",required = false)String set_ruleName){
        return ruleStatisticsService.LevelStatistics(name, startTime, endTime,set_type,set_ruleName);
    }



}
