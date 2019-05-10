package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.controller.vm.OrderProductWithSettlementAddVM;
import com.hgys.iptv.model.OrderProduct;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionMasterRepository;
import com.hgys.iptv.service.OrderProductService;
import com.hgys.iptv.service.ProductService;
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

@RestController
@RequestMapping("/orderproduct")
@Api(value = "orderproduct",tags = "结算类型-产品级Api接口")
public class OrderProductController {

    @Autowired
    private OrderProductService orderproductService;

    @Autowired
    private SettlementDimensionService settlementDimensionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SettlementCombinatorialDimensionMasterRepository settlementCombinatorialDimensionMasterRepository;



    @PostMapping("/selectById")
    @ApiOperation(value = "通过id查询",notes = "返回json数据类型")
    public ResultVO<?> findById(@ApiParam(value = "用户ID",required = true) @RequestParam("id")String id){
        if (StringUtils.isBlank(id)){
            return ResultVOUtil.error("1","id不能为空");
        }
        OrderProduct op = orderproductService.findById(Integer.valueOf(id.trim()));
        if (null == op){
            return ResultVOUtil.error("1","未查询到id为：" + id + "的信息");
        }
        return ResultVOUtil.success(op);
    }


    @DeleteMapping("/batchDeleteop")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    public ResultVO<?> batchDeleteop(@ApiParam(value = "名称",required = true) @RequestParam("ids")String ids){

        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","ids不能为空");
        }

        return orderproductService.batchDeleteop(ids);
    }


    @PostMapping("/addOrderProduct")
    @ApiOperation(value = "新增结算类型-产品级",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultVO<?> addOrderProduct(@ApiParam(value = "结算类型-产品级VM") @RequestBody() OrderProductWithSettlementAddVM vm){
        return orderproductService.addOrderBusinessComparison(vm);
    }

    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询结算类型-产品级",notes = "JSON类型格式数据")
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderProductWithSettlementAddVM> findByConditions(@ApiParam(value = "结算类型-产品级名称") @RequestParam(value = "name",required = false )String name,
                                                                 @ApiParam(value = "结算类型-产品级编码") @RequestParam(value = "code",required = false)String code,
                                                                 @ApiParam(value = "产品编码") @RequestParam(value = "productcode",required = false)String productcode,
                                                                 @ApiParam(value = "产品名称") @RequestParam(value = "productname",required = false)String productname,
                                                                 @ApiParam(value = "状态") @RequestParam(value = "status",required = false)String status,
                                                                 @ApiParam(value = "结算方式") @RequestParam(value = "mode",required = false)String mode,
                                                                 @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
                                                                 @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Sort sort = new Sort(Sort.Direction.DESC,"inputTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize),sort);
        Page<OrderProductWithSettlementAddVM> byConditions = orderproductService.findByConditions(name, code, productcode,productname,status, mode,pageable);
        return byConditions;
    }


    @PostMapping("/updateOrderproduct")
    @ApiOperation(value = "修改查询结算类型-业务定比例",notes = "返回处理结果")
    @ResponseStatus(HttpStatus.OK)
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
        ResultVO<?> all = productService.findAll();
        return all;
    }

    @GetMapping("/queryComDimensionLists")
    @ApiOperation(value = "查询多维度列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> queryComDimensionLists(){
        List<SettlementCombinatorialDimensionMaster> all = settlementCombinatorialDimensionMasterRepository.findAll();
        return ResultVOUtil.success(all);
    }

}
