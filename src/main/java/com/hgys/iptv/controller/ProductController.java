package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.ProductControllerListVM;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @Auther: wangz
 * @Date: 2019/5/5 18:17
 * @Description:
 */
@RestController()
@RequestMapping("/product")
@Api(value = "ProductController",tags = "产品管理Api接口")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/saveProduct")
    @ApiOperation(value = "新增产品",notes = "@return：产品对象")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> saveProduct(
            @ApiParam(value = "产品新增VM")  @RequestBody() ProductControllerListVM vo){
        Product prod = new Product();
        BeanUtils.copyProperties(vo,prod);
        return productService.save(prod);
    }

    @PutMapping("/UpdateProduct")
    @ApiOperation(value = "更新产品",notes = "@return：产品对象")
    public ResultVO<?> updateProduct(
            @ApiParam(value = "产品修改VM") @RequestBody() ProductControllerListVM vo){
        Product prod = new Product();
        BeanUtils.copyProperties(vo,prod);
        return productService.update(prod);
    }

    /**
     * 产品删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @DeleteMapping("/logicDeleteProduct")
    @ApiOperation(value = "逻辑删除产品",notes = "@return：true/false")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultVO<?> logicDelete(
            @ApiParam(value = "产品对象",required = true)Integer id){
        return productService.logicDelete(id);
    }

    @DeleteMapping("/batchLogicDeleteProduct")
    @ApiOperation(value = "批量逻辑删除产品",notes = "@return：true/false")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultVO<?> batchLogicDelete(String ids){
        return productService.batchLogicDelete(ids);
    }


    @GetMapping("/findProductById")
    @ApiOperation(value = "按id查询产品",notes = "@return：产品对象")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findById(
            @ApiParam(value = "id",required = true) @RequestParam("id")Integer id) {
        return productService.findById(id);
    }

    @GetMapping("/findProductByCode")
    @ApiOperation(value = "按code查询产品",notes = "@return：产品对象")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findByCode(
            @ApiParam(value = "产品编码code",required = true) @RequestParam("code")String code) {
        return productService.findByCode(code);
    }


    @GetMapping("/findAllProduct")
    @ApiOperation(value = "查询产品列表",notes = "@return：产品对象列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findAll() {
        return productService.findAll();
    }

}
