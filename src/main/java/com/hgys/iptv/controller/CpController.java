package com.hgys.iptv.controller;

import com.hgys.iptv.aop.SystemControllerLog;
import com.hgys.iptv.controller.vm.CpAddVM;
import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.model.enums.LogTypeEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.CpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * @Auther: wangz
 * @Date: 2019/5/5 18:17
 * @Description:
 */
@RestController()
@RequestMapping("/cp")
@Api(value = "cpManager",tags = "cp管理Api接口")
public class CpController {
    @Autowired
    private CpService cpService;

    //操作对象
    private static final String target = "CP管理";


    @PostMapping("/saveCp")
    @ApiOperation(value = "新增cp",notes = "@return：cp对象")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(value = "hasPermission('cpManager', 'add')")
    @SystemControllerLog(target = target,methodName = "CpController.saveCp",type = "新增")
    public ResultVO<?> saveCp(
            @ApiParam(value = "cp新增VM")  @RequestBody CpAddVM vo){
        return  cpService.save(vo);
    }

    @PostMapping("/UpdateCp")
    @ApiOperation(value = "修改cp",notes = "@return：cp对象")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('cpManager', 'update')")
    @SystemControllerLog(target = target,methodName = "CpController.updateCp",type = "修改")
    public ResultVO<?> updateCp(
            @ApiParam(value = "cp修改VM") @RequestBody CpAddVM vm){
        return cpService.update(vm);
    }


    @DeleteMapping("/batchLogicDeleteCp")
    @ApiOperation(value = "批量逻辑删除cp",notes = "@return：true/false")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('cpManager', 'remove')")
    @SystemControllerLog(target = target,methodName = "CpController.batchLogicDelete",type = "修改")
    public ResultVO<?> batchLogicDelete(String ids){
        return cpService.batchLogicDelete(ids);
    }


    @GetMapping("/findCpById")
    @ApiOperation(value = "按id查询cp",notes = "@return：cp对象")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('cpManager', 'view')")
    public ResultVO<?> findById(
            @ApiParam(value = "id",required = true) @RequestParam("id")Integer id) {
        return cpService.findById(id);
    }

    @GetMapping("/findCpByCode")
    @ApiOperation(value = "按code查询cp",notes = "@return：cp对象")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('cpManager', 'view')")
    public ResultVO<?> findByCode(
            @ApiParam(value = "cp编码code",required = true) @RequestParam("code")String code) {
        return cpService.findByCode(code);
    }


    @GetMapping("/findAllCp")
    @ApiOperation(value = "查询cp列表",notes = "@return：cp对象列表")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('cpManager', 'view')")
    public ResultVO<?> findAll() {
        return cpService.findAll();
    }


    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('cpManager', 'view')")
    public Page<CpControllerListVM>findByConditions(
            @ApiParam(value = "cp名称") @RequestParam(value = "name",required = false )String name,
            @ApiParam(value = "cp编码") @RequestParam(value = "code",required = false)String code,
            @ApiParam(value = "cp简称") @RequestParam(value = "cpAbbr",required = false)String cpAbbr,
            @ApiParam(value = "状态") @RequestParam(value = "status",required = false)Integer status,
            @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
            @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Sort sort = new Sort(Sort.Direction.DESC,"regisTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageSize),sort);
        return cpService.findByConditions(name,code,cpAbbr, status, pageable);
    }
}
