package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionAddVM;
import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionControllerListVM;
import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SettlementCombinatorialDimensionService;
import com.hgys.iptv.service.SettlementDimensionService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        return settlementCombinatorialDimensionService.addSettlementCombinatorialDimension(vo);
    }

    @GetMapping("/queryDimensionLists")
    @ApiOperation(value = "查询维度列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> queryDimensionLists(){
        List<SettlementDimension> all = settlementDimensionService.findAll();
        return ResultVOUtil.success(all);
    }

    @DeleteMapping("/batchLogicDelete")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultVO<?> batchLogicDelete(@ApiParam(value = "结算单维度ids",required = true) @RequestParam("ids")String ids){
        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","结算单维度ids不能为空");
        }

        return settlementCombinatorialDimensionService.batchLogicDelete(ids);
    }

    @GetMapping("/getSettlementCombinatorialDimension")
    @ApiOperation(value = "通过结算组合维度编码查询",notes = "返回json数据")
    @ResponseStatus(HttpStatus.OK)
    public SettlementCombinatorialDimensionControllerListVM getSettlementCombinatorialDimension(@ApiParam(value = "结算组合维度编码",required = true) @RequestParam("code")String code){
        if (StringUtils.isBlank(code)){
             new IllegalArgumentException("结算组合维度不能为空");
        }

        return settlementCombinatorialDimensionService.getSettlementCombinatorialDimension(code);
    }

    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询结算组合维度",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public Page<SettlementCombinatorialDimensionControllerListVM> findByConditions(@ApiParam(value = "结算单维度名称") @RequestParam(value = "name",required = false )String name,
                                                                      @ApiParam(value = "结算单维度编码") @RequestParam(value = "code",required = false)String code,
                                                                      @ApiParam(value = "状态") @RequestParam(value = "status",required = false)String status,
                                                                      @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
                                                                      @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Sort sort = new Sort(Sort.Direction.DESC,"inputTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize),sort);
        Page<SettlementCombinatorialDimensionControllerListVM> byConditions = settlementCombinatorialDimensionService.findByConditions(name, code, status, pageable);
        return byConditions;
    }
    @PutMapping("/updateCombinatorialDimension")
    @ApiOperation(value = "修改结算组合维度",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> updateCombinatorialDimension(@ApiParam(value = "结算单组合维度VM") @RequestBody() SettlementCombinatorialDimensionAddVM vo){
        return settlementCombinatorialDimensionService.updateCombinatorialDimension(vo);
    }
}
