package com.hgys.iptv.controller;

import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.impl.CpServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private CpServiceImpl cpService;

    @PostMapping("/saveCp")
    @ApiOperation(value = "新增cp",notes = "@return：cp对象")
    public ResultVO<?> saveCp(Cp cp){
       return cpService.save(cp);
    }

    @PutMapping("/UpdateCp")
    @ApiOperation(value = "更新cp",notes = "@return：cp对象")
    public ResultVO<?> updateCp(Cp cp){
        return cpService.update(cp);
    }

    /**
     * cp删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @PostMapping("/logicDeleteCp")
    @ApiOperation(value = "逻辑删除cp",notes = "@return：true/false")
    public ResultVO<?> logicDelete(Cp cp){
        return cpService.logicDelete(cp);
    }

    @PostMapping("/batchLogicDeleteCp")
    @ApiOperation(value = "批量逻辑删除cp",notes = "@return：true/false")
    public ResultVO<?> batchLogicDelete(List<Cp> cps){
        return cpService.batchLogicDelete(cps);
    }


    @GetMapping("/findCpById")
    @ApiOperation(value = "按id查询cp",notes = "@return：cp对象")
    public ResultVO<?> findById(Integer id) {
        return cpService.findById(id);
    }

    @GetMapping("/findCpByCode")
    @ApiOperation(value = "按code查询cp",notes = "@return：cp对象")
    public ResultVO<?> findByCode(String code) {
        return cpService.findByCode(code);
    }


    @GetMapping("/findAllCp")
    @ApiOperation(value = "查询cp列表",notes = "@return：cp对象列表")
    public ResultVO<?> findAll() {
        return cpService.findAll();
    }

}
