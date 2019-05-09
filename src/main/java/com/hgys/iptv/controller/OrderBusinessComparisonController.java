package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.OrderBusinessComparisonService;
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

@RestController
@RequestMapping("/orderBusinessComparisonController")
@Api(value = "orderBusinessComparisonController",tags = "结算类型-业务定比例Api接口")
public class OrderBusinessComparisonController {

    @Autowired
    private OrderBusinessComparisonService orderBusinessComparisonService;

    @PostMapping("/addOrderBusinessComparison")
    @ApiOperation(value = "新增结算类型-业务定比例",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> addOrderBusinessComparison(@ApiParam(value = "结算类型-业务定比例VM") @RequestBody()OrderBusinessComparisonAddVM vm){
        return orderBusinessComparisonService.addOrderBusinessComparison(vm);
    }

    @DeleteMapping("/batchLogicDelete")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> batchLogicDelete(@ApiParam(value = "结算类型-业务定比例ids",required = true) @RequestParam("ids")String ids){
        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","结算类型-业务定比例ids不能为空");
        }

        return orderBusinessComparisonService.batchLogicDelete(ids);
    }

    @GetMapping("/findByCode")
    @ApiOperation(value = "通过编码查询单条记录",notes = "JSON格式数据")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> findByCode(@ApiParam(value = "结算类型-业务定比例Code",required = true) @RequestParam("code")String code){
        if (StringUtils.isBlank(code)){
            return ResultVOUtil.error("1","结算类型-业务定比例code不能为空");
        }
        OrderBusinessComparisonQueryVM vm = orderBusinessComparisonService.getOrderBusinessComparison(code);
        return ResultVOUtil.success(vm);
    }

    @GetMapping("/findById")
    @ApiOperation(value = "通过Id查询单条记录",notes = "JSON格式数据")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> findById(@ApiParam(value = "结算类型-业务定比例Id",required = true) @RequestParam("id")String id){
        if (StringUtils.isBlank(id)){
            return ResultVOUtil.error("1","结算类型-业务定比例Id不能为空");
        }
        OrderBusinessComparisonQueryVM vm = orderBusinessComparisonService.findById(id);
        return ResultVOUtil.success(vm);
    }

    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询结算类型-业务定比例",notes = "JSON类型格式数据")
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderBusinessComparisonQueryVM> findByConditions(@ApiParam(value = "业务定比例名称") @RequestParam(value = "name",required = false )String name,
                                                                                   @ApiParam(value = "业务定比例编码") @RequestParam(value = "code",required = false)String code,
                                                                                   @ApiParam(value = "业务编码") @RequestParam(value = "businessCode",required = false)String businessCode,
                                                                                   @ApiParam(value = "业务名称") @RequestParam(value = "businessName",required = false)String businessName,
                                                                                   @ApiParam(value = "状态") @RequestParam(value = "status",required = false)String status,
                                                                                   @ApiParam(value = "结算方式") @RequestParam(value = "mode",required = false)String mode,
                                                                                   @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
                                                                                   @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Sort sort = new Sort(Sort.Direction.DESC,"inputTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize),sort);
        Page<OrderBusinessComparisonQueryVM> byConditions = orderBusinessComparisonService.findByConditions(name, code, businessCode,businessName,status, mode,pageable);
        return byConditions;
    }

    @PostMapping("/updateOrderBusinessComparison")
    @ApiOperation(value = "修改查询结算类型-业务定比例",notes = "返回处理结果")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> updateOrderBusinessComparison(@ApiParam(value = "结算类型-业务定比例VM") @RequestBody()OrderBusinessComparisonAddVM vm){
        return orderBusinessComparisonService.updateOrderBusinessComparison(vm);
    }

    @GetMapping("/getBusinessList")
    @ApiOperation(value = "查询业务列表",notes = "返回JSON格式数据")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> getBusinessList(){
        return orderBusinessComparisonService.getBusinessList();
    }
}
