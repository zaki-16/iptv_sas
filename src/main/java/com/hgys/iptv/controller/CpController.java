package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.CpAddVM;
import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.controller.vm.CpVM;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.CpService;
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
@RequestMapping("/cp")
@Api(value = "CpController",tags = "cp管理Api接口")
public class CpController {
    @Autowired
    private CpService cpService;


    @PostMapping("/saveCp")
    @ApiOperation(value = "新增cp",notes = "@return：cp对象")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> saveCp(
            @ApiParam(value = "cp新增VM")  @RequestBody CpAddVM vo){
        return  cpService.save(vo);
    }

    @PostMapping("/UpdateCp")
    @ApiOperation(value = "更新cp",notes = "@return：cp对象")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> updateCp(
            @ApiParam(value = "cp修改VM") @RequestBody CpAddVM vo){
        Cp cp = new Cp();
        BeanUtils.copyProperties(vo,cp);
        return cpService.update(cp);
    }

    /**
     * cp删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
//    @DeleteMapping("/logicDeleteCp")
//    @ApiOperation(value = "逻辑删除cp",notes = "@return：true/false")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultVO<?> logicDelete(
            @ApiParam(value = "cp对象",required = true)Integer id){
        return cpService.logicDelete(id);
    }

    @DeleteMapping("/batchLogicDeleteCp")
    @ApiOperation(value = "批量逻辑删除cp",notes = "@return：true/false")
    @ResponseStatus(HttpStatus.OK)
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


    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
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
