package com.hgys.iptv.controller;

import com.hgys.iptv.aop.SystemControllerLog;
import com.hgys.iptv.controller.vm.ProductLevelStatisticsVM;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SettlementService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yangpeng
 */
@RestController
@RequestMapping("/settlementController")
@Api(value = "settlementController",tags = "结算Api接口")
public class SettlementController {

    @Autowired
    private SettlementService settlementService;

    @PostMapping("/settlement")
    @ApiOperation(value = "分账结算结算接口",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    @SystemControllerLog(target = "分账结算",methodName = "SettlementDimensionController.settlement",type = "新增")
    public ResultVO<?> settlement(@ApiParam(value = "分账结算id") @RequestParam String id){
        if (StringUtils.isBlank(id)){
            return ResultVOUtil.error("1","分账结算ID不能为空!");
        }
        return settlementService.settlement(id);
    }

    @PostMapping("/cancel")
    @ApiOperation(value = "分账结算撤销接口",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    @SystemControllerLog(target = "分账结算",methodName = "SettlementDimensionController.cancel",type = "修改")
    public ResultVO<?> cancel(@ApiParam(value = "分账结算Id") @RequestParam String id){
        if (StringUtils.isBlank(id)){
            return ResultVOUtil.error("1","分账结算ID不能为空!");
        }
        return settlementService.cancel(id);
    }

    @GetMapping("/productLevelStatistics")
    @ApiOperation(value = "统计产品级数据",notes = "Json数据")
    public List<ProductLevelStatisticsVM> productLevelStatistics(@ApiParam(value = "结算开始时间(2019-01-01 12:12:12)") @RequestParam(value = "startTime",required = false)String startTime,
                                                                 @ApiParam(value = "结算结束时间(2019-01-01 12:12:12)") @RequestParam(value = "endTime",required = false)String endTime,
                                                                 @ApiParam(value = "产品编码") @RequestParam(value = "productCodeList",required = false)String productCodeList){

        return settlementService.productLevelStatistics(startTime,endTime,productCodeList);
    }
}
