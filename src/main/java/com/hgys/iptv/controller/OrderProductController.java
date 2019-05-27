package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.controller.vm.OrderProductWithSettlementAddVM;
import com.hgys.iptv.controller.vm.OrderProductWithSettlementfindVM;
import com.hgys.iptv.model.OrderProduct;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionMasterRepository;
import com.hgys.iptv.service.OrderProductService;
import com.hgys.iptv.service.ProductService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderproduct")
@Api(value = "orderproduct",tags = "结算类型-产品级Api接口")
public class OrderProductController {

    @Autowired
    private OrderProductService orderproductService;

    @Autowired
    private SettlementDimensionService settlementDimensionService;

    @Autowired
    private SettlementCombinatorialDimensionService settlementCombinatorialDimensionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SettlementCombinatorialDimensionMasterRepository settlementCombinatorialDimensionMasterRepository;





    @GetMapping("/findByIds")
    @ApiOperation(value = "通过结算组合Id编码查询",notes = "返回json数据")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('OrderProduct', 'view')")
    public ResultVO<?> findByIds(@ApiParam(value = "结算组合Id编码",required = true) @RequestParam("id")String id){
        if (StringUtils.isBlank(id)){
            new IllegalArgumentException(" 不能为空");
        }
        OrderProductWithSettlementAddVM byId = orderproductService.findById(id);
        return ResultVOUtil.success(byId);
    }





    @DeleteMapping("/batchDeleteop")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    @PreAuthorize(value = "hasPermission('OrderProduct', 'remove')")
    public ResultVO<?> batchDeleteop(@ApiParam(value = "名称",required = true) @RequestParam("ids")String ids){

        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","ids不能为空");
        }

        return orderproductService.batchDeleteop(ids);
    }






    @PostMapping("/addOrderProduct")
    @ApiOperation(value = "新增",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('OrderProduct', 'add')")
    public ResultVO<?> addOrderProduct(@ApiParam(value = "结算类型订单量VM") @RequestBody() OrderProductWithSettlementAddVM vo){

        return orderproductService.addOrderBusinessComparison(vo);
    }









    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询结算类型-产品级",notes = "JSON类型格式数据")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('OrderProduct', 'view')")
    public Page<OrderProductWithSettlementfindVM> findByConditions(@ApiParam(value = "结算类型-产品级名称") @RequestParam(value = "name",required = false )String name,
                                                                   @ApiParam(value = "结算类型-产品级编码") @RequestParam(value = "code",required = false)String code,
                                                                   @ApiParam(value = "产品编码") @RequestParam(value = "productcode",required = false)String productcode,
                                                                   @ApiParam(value = "产品名称") @RequestParam(value = "productname",required = false)String productname,
                                                                   @ApiParam(value = "状态") @RequestParam(value = "status",required = false)String status,
                                                                   @ApiParam(value = "结算方式") @RequestParam(value = "mode",required = false)String mode,
                                                                   @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
                                                                   @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Sort sort = new Sort(Sort.Direction.DESC,"inputTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize),sort);
        Page<OrderProductWithSettlementfindVM> byConditions = orderproductService.findByConditions(name, code, productcode,productname,status, mode,pageable);
        return byConditions;
    }


    @PostMapping("/updateOrderproduct")
    @ApiOperation(value = "修改查询结算类型-业务定比例",notes = "返回处理结果")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('OrderProduct', 'update')")
    public ResultVO<?> updateOrderproduct(@ApiParam(value = "结算类型-业务定比例VM") @RequestBody()OrderProductWithSettlementAddVM vm){
        return orderproductService.updateOrderproduct(vm);
    }

    @GetMapping("/queryDimensionLists")
    @ApiOperation(value = "查询维度列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> queryDimensionLists(){
        List<SettlementDimension> all = settlementDimensionService.findAll();
        return ResultVOUtil.success(all);
    }

    @GetMapping("/queryProductLists")
    @ApiOperation(value = "查产品列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> queryProductLists(){
        ResultVO<?> all = productService.findplist();
        return all;
    }

  /*  @GetMapping("/queryComDimensionLists")
    @ApiOperation(value = "查询多维度列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> queryComDimensionLists(){
        List<SettlementCombinatorialDimensionMaster> all = settlementCombinatorialDimensionMasterRepository.findAll();
        return ResultVOUtil.success(all);
    }*/


    @GetMapping("/queryComDimensionLists")
    @ApiOperation(value = "查询多维度列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> queryComDimensionLists(){
        ResultVO<?> all = settlementCombinatorialDimensionService.findcdslist();
        return all;
    }


}
