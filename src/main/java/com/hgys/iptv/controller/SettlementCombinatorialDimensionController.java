package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionAddVM;
import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SettlementCombinatorialDimensionService;
import com.hgys.iptv.service.SettlementDimensionService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangpeng
 */
@RestController
@RequestMapping("/settlementCombinatorialDimensionController")
@Api(value = "settlementCombinatorialDimensionController",tags = "结算组合维度Api接口")
public class SettlementCombinatorialDimensionController {

    @Autowired
    private SettlementCombinatorialDimensionService settlementCombinatorialDimensionService;

    @Autowired
    private SettlementDimensionService settlementDimensionService;

    @PostMapping("/addSettlementCombinatorialDimension")
    @ApiOperation(value = "新增结算组合维度",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> addSettlementCombinatorialDimension(@ApiParam(value = "结算单组合维度VM") @RequestBody() SettlementCombinatorialDimensionAddVM vo){

        settlementCombinatorialDimensionService.addSettlementCombinatorialDimension(vo);

        return null;
    }

    @GetMapping("/queryDimensionLists")
    @ApiOperation(value = "查询维度列表")
    public ResultVO<?> queryDimensionLists(){
        List<SettlementDimension> all = settlementDimensionService.findAll();
        return ResultVOUtil.success(all);
    }
}
