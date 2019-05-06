package com.hgys.iptv.controller;

import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SettlementDimensionService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author yangpeng
 */
@RestController
@RequestMapping("/settlementDimensionController")
@Api(value = "settlementDimensionController",tags = "结算单维度Api接口")
public class SettlementDimensionController {

    @Autowired
    private SettlementDimensionService settlementDimensionService;

    @PostMapping("/selectById")
    @ApiOperation(value = "通过id查询",notes = "返回json数据类型")
    public ResultVO<?> findById(@ApiParam(value = "用户ID",required = true) @RequestParam("id")String id){

        if (StringUtils.isBlank(id)){
            return ResultVOUtil.error("1","id不能为空");
        }
        SettlementDimension vo = settlementDimensionService.findById(Integer.valueOf(id.trim()));
        if (null == vo){
            return ResultVOUtil.error("1","未查询到id为：" + id + "的信息");
        }

        return ResultVOUtil.success(vo);
    }
}
