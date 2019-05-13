package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.ProductAddVM;
import com.hgys.iptv.controller.vm.ProductControllerListVM;
import com.hgys.iptv.controller.vm.ProductListVM;
import com.hgys.iptv.controller.vm.ProductVM;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.ProductService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            @ApiParam(value = "产品新增VM")  @RequestBody() ProductAddVM vm){
        return productService.save(vm);
    }

    @PostMapping("/UpdateProduct")
    @ApiOperation(value = "更新产品",notes = "@return：产品对象")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> updateProduct(
            @ApiParam(value = "产品修改VM") @RequestBody() ProductAddVM vm){
        return productService.update(vm);
    }

//    /**
//     * 产品删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
//     */
////    @DeleteMapping("/logicDeleteProduct")
////    @ApiOperation(value = "逻辑删除产品",notes = "@return：true/false")
////    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResultVO<?> logicDelete(
//            @ApiParam(value = "产品对象",required = true)Integer id){
//        return productService.logicDelete(id);
//    }

    @DeleteMapping("/batchLogicDeleteProduct")
    @ApiOperation(value = "批量逻辑删除产品",notes = "@return：true/false")
    @ResponseStatus(HttpStatus.OK)
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

//    @GetMapping("/findProductByCode")
//    @ApiOperation(value = "按code查询产品",notes = "@return：产品对象")
//    @ResponseStatus(HttpStatus.OK)
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


    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductVM> findByConditions(
            @ApiParam(value = "产品名称") @RequestParam(value = "name",required = false )String name,
            @ApiParam(value = "产品编码") @RequestParam(value = "code",required = false)String code,
            @ApiParam(value = "状态") @RequestParam(value = "status",required = false)Integer status,
            @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
            @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Sort sort = new Sort(Sort.Direction.DESC,"inputTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize),sort);
        return productService.findByConditions(name,code,status, pageable);
    }
}
