package com.hgys.iptv.controller;


import com.hgys.iptv.aop.SystemControllerLog;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.controller.vm.OrderBusinessWithCPAddVM;
import com.hgys.iptv.model.OrderBusiness;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.CpService;
import com.hgys.iptv.service.OrderBusinessService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orderbusiness")
@Api(value = "orderbusiness",tags = "结算类型-业务级Api接口")
public class OrderBusinessController {

    @Autowired
    private OrderBusinessService orderbusinessService;

    @Autowired
    private CpService cpService;

    private static final String target="业务级结算";
/*
    @PostMapping("/selectById")
    @ApiOperation(value = "通过id查询",notes = "返回json数据类型")
    public ResultVO<?> findById(@ApiParam(value = "用户ID",required = true) @RequestParam("id")String id){
        if (StringUtils.isBlank(id)){
            return ResultVOUtil.error("1","id不能为空");
        }
        OrderBusiness ob = orderbusinessService.findById(Integer.valueOf(id.trim()));
        if (null == ob){
            return ResultVOUtil.error("1","未查询到id为：" + id + "的信息");
        }
        return ResultVOUtil.success(ob);
    }
*/



    @DeleteMapping("/batchDeleteob")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('OrderBusiness', 'remove')")
    @SystemControllerLog(target = target,methodName = "OrderBusinessController.Delete",type = "删除")
    public ResultVO<?> batchDeleteob(@ApiParam(value = "结算类型-业务定比例ids",required = true) @RequestParam("ids")String ids){
        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","结算类型-业务定比例ids不能为空");
        }
        ResultVO<?> resultVO = orderbusinessService.batchDeleteob(ids);
        return resultVO;
    }



    @PostMapping("/addOrderBusiness")
    @ApiOperation(value = "新增结算类型-业务级",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(value = "hasPermission('OrderBusiness', 'add')")
    @SystemControllerLog(target = target,methodName = "OrderBusinessController.save",type = "新增")
    public ResultVO<?> addOrderBusiness(@ApiParam(value = "结算类型-业务级VM") @RequestBody() OrderBusinessWithCPAddVM vo){

        return orderbusinessService.addOrderBusiness(vo);
    }

    @GetMapping("/getBusinessList")
    @ApiOperation(value = "查询业务列表",notes = "返回JSON格式数据")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('OrderBusiness', 'view')")
    public ResultVO<?> getBusinessList(){
        return orderbusinessService.getBusinessList();
    }


    @PostMapping("/updateOrderBusiness")
    @ApiOperation(value = "修改结算类型-业务级",notes = "返回处理结果")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('OrderBusiness', 'update')")
    @SystemControllerLog(target = target,methodName = "OrderBusinessController.update",type = "修改")
    public ResultVO<?> updateOrderBusiness(@ApiParam(value = "结算类型-业务级VM") @RequestBody() OrderBusinessWithCPAddVM vm){
        return orderbusinessService.updateOrderBusiness(vm);
    }

    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询修改结算类型-业务级",notes = "JSON类型格式数据")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('OrderBusiness', 'view')")
    public Page<OrderBusinessWithCPAddVM> findByConditions(@ApiParam(value = "业务级结算名称") @RequestParam(value = "name",required = false )String name,
                                                                 @ApiParam(value = "业务级结算编码") @RequestParam(value = "code",required = false)String code,
                                                                 @ApiParam(value = "状态") @RequestParam(value = "status",required = false)String status,
                                                                 @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
                                                                 @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Sort sort = new Sort(Sort.Direction.DESC,"inputTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize),sort);
        Page<OrderBusinessWithCPAddVM> byConditions = orderbusinessService.findByConditions(name, code,status,pageable);
        return byConditions;
    }

    @GetMapping("/getOrderBusiness")
    @ApiOperation(value = "通过编码查询单条记录",notes = "JSON格式数据")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(value = "hasPermission('OrderBusiness', 'view')")
    public ResultVO<?> getOrderBusiness(@ApiParam(value = "结算类型-业务级Code",required = true) @RequestParam("code")String code){
        if (StringUtils.isBlank(code)){
            return ResultVOUtil.error("1","结算类型-业务级code不能为空");
        }
        OrderBusinessWithCPAddVM vm = orderbusinessService.getOrderBusiness(code);
        return ResultVOUtil.success(vm);
    }

    @GetMapping("/queryCPList")
    @ApiOperation(value = "查询CP列表")
    @PreAuthorize(value = "hasPermission('OrderBusiness', 'view')")
    public ResultVO<?> queryCPList(){
        ResultVO<?> all = cpService.findcplist();
        return all;
    }


    @GetMapping("/findByIds")
    @ApiOperation(value = "通过结算组合Id编码查询",notes = "返回json数据")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('OrderBusiness', 'view')")
    public ResultVO<?> findByIds(@ApiParam(value = "结算组合Id编码",required = true) @RequestParam("id")String id){
        if (StringUtils.isBlank(id)){
            new IllegalArgumentException(" 不能为空");
        }
        OrderBusinessWithCPAddVM byId = orderbusinessService.findByIds(id);
        return ResultVOUtil.success(byId);
    }



}
