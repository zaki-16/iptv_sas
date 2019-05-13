package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.BusinessAddVM;
import com.hgys.iptv.controller.vm.BusinessControllerListVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.BusinessService;
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
@RequestMapping("/business")
@Api(value = "BusinessController",tags = "业务管理Api接口")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @PostMapping("/saveBusiness")
    @ApiOperation(value = "新增业务",notes = "@return：业务对象")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> saveBusiness(
            @ApiParam(value = "业务新增VM")  @RequestBody() BusinessAddVM vm){
        return businessService.save(vm);
    }

    @PostMapping("/UpdateBusiness")
    @ApiOperation(value = "更新业务",notes = "@return：业务对象")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> updateBusiness(
            @ApiParam(value = "业务修改VM") @RequestBody() BusinessAddVM vo){
        Business business = new Business();
        BeanUtils.copyProperties(vo,business);
        return businessService.update(business);
    }

    /**
     * 业务删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
//    @DeleteMapping("/logicDeleteBusiness")
//    @ApiOperation(value = "逻辑删除业务",notes = "@return：true/false")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultVO<?> logicDelete(
//            @ApiParam(value = "业务id",required = true)Integer id){
//        return businessService.logicDelete(id);
//    }

    @DeleteMapping("/batchLogicDeleteBusiness")
    @ApiOperation(value = "批量逻辑删除业务",notes = "@return：true/false")
    @ResponseStatus(HttpStatus.OK)
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

//    @GetMapping("/findBusinessByCode")
//    @ApiOperation(value = "按code查询业务",notes = "@return：业务对象")
//    @ResponseStatus(HttpStatus.OK)
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

    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public Page<BusinessControllerListVM> findByConditions(
            @ApiParam(value = "业务名称") @RequestParam(value = "name",required = false )String name,
            @ApiParam(value = "业务编码") @RequestParam(value = "code",required = false)String code,
            @ApiParam(value = "业务类型",example = "0:视频类、1:非视频类") @RequestParam(value = "bizType",required = false)Integer bizType,
            @ApiParam(value = "结算类型",example = "1:按比例结算、2:按订购量结算") @RequestParam(value = "settleType",required = false)Integer settleType,
            @ApiParam(value = "状态") @RequestParam(value = "status",required = false)Integer status,
            @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
            @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Sort sort = new Sort(Sort.Direction.DESC,"inputTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize),sort);
        Page<BusinessControllerListVM> byConditions = businessService.findByConditions(name,code,bizType,settleType,status, pageable);
        ResultVOUtil.success(byConditions);
        return byConditions;
    }

}
