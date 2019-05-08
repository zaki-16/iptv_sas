package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.BusinessControllerListVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.BusinessService;
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
@RequestMapping("/business")
@Api(value = "BusinessController",tags = "业务管理Api接口")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @PostMapping("/saveBusiness")
    @ApiOperation(value = "新增业务",notes = "@return：业务对象")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> saveBusiness(
            @ApiParam(value = "业务新增VM")  @RequestBody() BusinessControllerListVM vo){
        Business business = new Business();
        BeanUtils.copyProperties(vo,business);
        return businessService.save(business);
    }

    @PutMapping("/UpdateBusiness")
    @ApiOperation(value = "更新业务",notes = "@return：业务对象")
    public ResultVO<?> updateBusiness(
            @ApiParam(value = "业务修改VM") @RequestBody() BusinessControllerListVM vo){
        Business business = new Business();
        BeanUtils.copyProperties(vo,business);
        return businessService.update(business);
    }

    /**
     * 业务删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @DeleteMapping("/logicDeleteBusiness")
    @ApiOperation(value = "逻辑删除业务",notes = "@return：true/false")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultVO<?> logicDelete(
            @ApiParam(value = "业务对象",required = true)Integer id){
        return businessService.logicDelete(id);
    }

    @DeleteMapping("/batchLogicDeleteBusiness")
    @ApiOperation(value = "批量逻辑删除业务",notes = "@return：true/false")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultVO<?> batchLogicDelete(String ids){
        return businessService.batchLogicDelete(ids);
    }


    @GetMapping("/findBusinessById")
    @ApiOperation(value = "按id查询业务",notes = "@return：业务对象")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findById(
            @ApiParam(value = "id",required = true) @RequestParam("id")Integer id) {
        return businessService.findById(id);
    }

    @GetMapping("/findBusinessByCode")
    @ApiOperation(value = "按code查询业务",notes = "@return：业务对象")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findByCode(
            @ApiParam(value = "业务编码code",required = true) @RequestParam("code")String code) {
        return businessService.findByCode(code);
    }


    @GetMapping("/findAllBusiness")
    @ApiOperation(value = "查询业务列表",notes = "@return：业务对象列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findAll() {
        return businessService.findAll();
    }

}
