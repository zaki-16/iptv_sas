package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.OrderCp;
import com.hgys.iptv.model.OrderCpWithCp;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.CpService;
import com.hgys.iptv.service.OrderCpService;
import com.hgys.iptv.util.CodeUtil;
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

import java.sql.Timestamp;


@RestController
@RequestMapping("/ordercp")
@Api(value = "ordercp",tags = "结算类型-CP定比例Api接口")
public class OrderCpController {

    @Autowired
    private OrderCpService ordercpService;

    @Autowired
    private CpService cpService;


    @PostMapping("/selectById")
    @ApiOperation(value = "通过id查询",notes = "返回json数据类型")
    public ResultVO<?> findById(@ApiParam(value = "用户ID",required = true) @RequestParam("id")String id){
        if (StringUtils.isBlank(id)){
            return ResultVOUtil.error("1","id不能为空");
        }
        OrderCp oc = ordercpService.findById(Integer.valueOf(id.trim()));
        if (null == oc){
            return ResultVOUtil.error("1","未查询到id为：" + id + "的信息");
        }
        return ResultVOUtil.success(oc);
    }

    @PostMapping("/batchDeleteoc")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    public ResultVO<?> batchDeleteoc(@ApiParam(value = "结算单维度名称",required = true) @RequestParam("ids")String ids){

        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","结算单维度ids不能为空");
        }

        return ordercpService.batchDeleteoc(ids);
    }

    @PostMapping("/addOrderCp")
    @ApiOperation(value = "新增结算类型-订购量",notes = "返回处理结果，false或true")
    public ResultVO<?> addOrderCp(@ApiParam(value = "名称",required = true) @RequestParam("name")String name,
                                        @ApiParam(value = "状态(0:启用;1:禁用;默认启用)",required = true) @RequestParam("status")String status,
                                        @ApiParam(value = "备注",required = false) @RequestParam("note")String note) {

        if (StringUtils.isBlank(name)){
            return ResultVOUtil.error("1","结算类型-业务级name不能为空");
        }

        if (StringUtils.isBlank(status)){
            return ResultVOUtil.error("1","结算类型-业务级status不能为空");
        }

        return ordercpService.insterOrderCp(name,status,note);
    }


 /*   @PostMapping("/addOrderCp")
    @ApiOperation(value = "新增",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> addOrderCp(@ApiParam(value = "VM") @RequestBody() SettlementCombinatorialDimensionAddVM vo){

        return settlementCombinatorialDimensionService.addSettlementCombinatorialDimension(vo);
    }
*/



 /*   @GetMapping("/getOrderCp")
    @ApiOperation(value = "通过编码查询",notes = "返回json数据")
    @ResponseStatus(HttpStatus.OK)
    public OrderCPWithCPListVM getOrderCp(@ApiParam(value = "编码",required = true) @RequestParam("code")String code){
        if (StringUtils.isBlank(code)){
            new IllegalArgumentException("Code不能为空");
        }

        return ordercpService.getOrderCp(code);
    }*/


    @GetMapping("/getOrderCp")
    @ApiOperation(value = "通过编码查询",notes = "返回json数据")
    @ResponseStatus(HttpStatus.OK)
    public OrderCPWithCPListVM getOrderCp(@ApiParam(value = "结算组合维度编码",required = true) @RequestParam("code")String code){
        if (StringUtils.isBlank(code)){
            new IllegalArgumentException("Code不能为空");
        }

        return ordercpService.getOrderCp(code);
    }











    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询结算组合维度",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderCPWithCPListVM> findByConditions(@ApiParam(value = "结算单维度名称") @RequestParam(value = "name",required = false )String name,
                                                                                   @ApiParam(value = "编码") @RequestParam(value = "code",required = false)String code,
                                                                                   @ApiParam(value = "状态") @RequestParam(value = "status",required = false)String status,
                                                                                   @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
                                                                                   @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Sort sort = new Sort(Sort.Direction.DESC,"inputTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize),sort);
        Page<OrderCPWithCPListVM> byConditions = ordercpService.findByConditions(name, code, status, pageable);
        return byConditions;
    }



    @PutMapping("/updateOrderCp")
    @ApiOperation(value = "修改结算类型-CP定比例",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> updateOrderCp(@ApiParam(value = "结算单组合维度VM") @RequestBody() OrderCPWithCPListVM vo){
        return ordercpService.updateOrderCp(vo);
    }



    @GetMapping("/queryCPList")
    @ApiOperation(value = "查询CP列表")
    public ResultVO<?> queryCPList(){
        ResultVO<?> all = cpService.findAll();
        return ResultVOUtil.success(all);
    }

}
