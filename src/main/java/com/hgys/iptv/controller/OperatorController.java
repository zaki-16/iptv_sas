package com.hgys.iptv.controller;

import com.hgys.iptv.model.Operator;
import com.hgys.iptv.service.impl.OperatorServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oper")
@Api(value = "operator管理")
public class OperatorController {
    @Autowired
    private OperatorServiceImpl operatorService;

    @PostMapping("/saveOperator")
    @ApiOperation(value="新增运营方信息")
    public void saveOperator(Operator operator){
        Operator op = new Operator();
        op.setOpNm("jay_idea");
        op.setOpAbbr("jay_abbr");
        operatorService.save(op);
    }

    @GetMapping("/findByOpNm")
    @ApiOperation(value="根据名称获取运营方信息")
//    @ApiImplicitParam(paramType="Operator", name = "opNm", value = "运营方名称", required = true, dataType = "String")
    public Operator findByOpNm(String name){
        return operatorService.findByOpNm("wangzhen");
    }

    @DeleteMapping("/deleteOperatorById")
    @ApiOperation(value="删除运营方信息")
    public void delete(Operator oper){
        Operator op = new Operator();
        op.setOpid(3);
        //op.setOpNm("jay_nm");
      //  op.setOpAbbr("jay_abbr");
        operatorService.delete(op);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "获取所有运营方信息")
    public List<Operator> findAll(){
        return operatorService.findAll();
    }
}
