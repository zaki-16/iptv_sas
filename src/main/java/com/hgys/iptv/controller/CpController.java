package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.controller.vm.SettlementDimensionControllerUpdateVM;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.CpService;
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
@RequestMapping("/cp")
@Api(value = "CpController",tags = "cp管理Api接口")
public class CpController {
    @Autowired
    private CpService cpService;

    @PostMapping("/saveCp")
    @ApiOperation(value = "新增cp",notes = "@return：cp对象")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> saveCp(
            @ApiParam(value = "cp新增VM")  @RequestBody() CpControllerListVM vo){
        Cp cp = new Cp();
        BeanUtils.copyProperties(vo,cp);
       return cpService.save(cp);
    }

    @PutMapping("/UpdateCp")
    @ApiOperation(value = "更新cp",notes = "@return：cp对象")
    public ResultVO<?> updateCp(
            @ApiParam(value = "cp修改VM") @RequestBody() CpControllerListVM vo){
        Cp cp = new Cp();
        BeanUtils.copyProperties(vo,cp);
        return cpService.update(cp);
    }

    /**
     * cp删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @DeleteMapping("/logicDeleteCp")
    @ApiOperation(value = "逻辑删除cp",notes = "@return：true/false")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultVO<?> logicDelete(
            @ApiParam(value = "cp对象",required = true)Integer id){
        return cpService.logicDelete(id);
    }

    @DeleteMapping("/batchLogicDeleteCp")
    @ApiOperation(value = "批量逻辑删除cp",notes = "@return：true/false")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultVO<?> batchLogicDelete(String ids){
        return cpService.batchLogicDelete(ids);
    }


    @GetMapping("/findCpById")
    @ApiOperation(value = "按id查询cp",notes = "@return：cp对象")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findById(
            @ApiParam(value = "cpid",required = true) @RequestParam("id")Integer id) {
        return cpService.findById(id);
    }

    @GetMapping("/findCpByCode")
    @ApiOperation(value = "按code查询cp",notes = "@return：cp对象")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findByCode(
            @ApiParam(value = "cp编码code",required = true) @RequestParam("code")String code) {
        return cpService.findByCode(code);
    }


    @GetMapping("/findAllCp")
    @ApiOperation(value = "查询cp列表",notes = "@return：cp对象列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findAll() {
        return cpService.findAll();
    }

}
