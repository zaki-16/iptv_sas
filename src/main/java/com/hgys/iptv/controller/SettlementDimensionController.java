package com.hgys.iptv.controller;

import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SettlementDimensionService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


/**
 * @author yangpeng
 */
@RestController
@RequestMapping("/settlementDimensionController")
@Api(value = "settlementDimensionController",tags = "结算单维度Api接口")
public class SettlementDimensionController {

    @Autowired
    private SettlementDimensionService settlementDimensionService;

    @PostMapping("/selectByCode")
    @ApiOperation(value = "通过code查询",notes = "返回json数据类型")
    public ResultVO<?> findByCode(@ApiParam(value = "结算单维度code",required = true) @RequestParam("code")String code){

        if (StringUtils.isBlank(code)){
            return ResultVOUtil.error("1","结算单维度code不能为空");
        }
        SettlementDimension vo = settlementDimensionService.findByCode(code.trim());
        if (null == vo){
            return ResultVOUtil.error("1","未查询到code为：" + code + "的信息");
        }

        return ResultVOUtil.success(vo);
    }

    @PostMapping("/addSettlementDimension")
    @ApiOperation(value = "新增结算维度",notes = "返回处理结果，false或true")
    public ResultVO<?> addSettlementDimension(@ApiParam(value = "结算单维度名称",required = true) @RequestParam("name")String name,
                                              @ApiParam(value = "结算单维度状态(0:启用;1:禁用;默认启用)",required = true) @RequestParam("status")String status,
                                              @ApiParam(value = "结算单维度备注",required = false) @RequestParam("remarks")String remarks){

        if (StringUtils.isBlank(name)){
            return ResultVOUtil.error("1","结算单维度name不能为空");
        }

        if (StringUtils.isBlank(status)){
            return ResultVOUtil.error("1","结算单维度status不能为空");
        }

        return settlementDimensionService.insterSettlementDimension(name,status,remarks);
    }

    @PostMapping("/batchLogicDelete")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    public ResultVO<?> batchLogicDelete(@ApiParam(value = "结算单维度名称",required = true) @RequestParam("ids")String ids){

        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","结算单维度ids不能为空");
        }

        return settlementDimensionService.batchLogicDelete(ids);
    }

    @PostMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询",notes = "返回处理结果，false或true")
    public ResultVO<?> findByConditions(@ApiParam(value = "结算单维度名称",required = false ) @RequestParam("name")String name,
                                        @ApiParam(value = "结算单维度编码",required = false) @RequestParam("code")String code,
                                        @ApiParam(value = "状态",required = false) @RequestParam("status")String status,
                                        @ApiParam(value = "当前页",required = true) @RequestParam("pageNum")String pageNum,
                                        @ApiParam(value = "当前页数量",required = true) @RequestParam("pageSize")String pageSize){

        SettlementDimension vo = new SettlementDimension();
        vo.setCode(CodeUtil.getOnlyCode("SDS",5));
        vo.setInputTime(new Timestamp(System.currentTimeMillis()));
        vo.setName(name);

        return settlementDimensionService.findByConditions(name,code,status,pageNum,pageSize);
    }
}
