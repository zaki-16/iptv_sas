package com.hgys.iptv.controller;

import com.hgys.iptv.model.OrderBusiness;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.OrderBusinessService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orderbusiness")
@Api(value = "orderbusiness",tags = "结算类型-业务级Api接口")
public class OrderBusinessController {

    @Autowired
    private OrderBusinessService orderbusinessService;

    @PostMapping("/selectById")
    @ApiOperation(value = "通过id查询",notes = "返回json数据类型")
    public ResultVO<?> findById(@ApiParam(value = "用户ID",required = true) @RequestParam("id")String id){
        if (StringUtils.isBlank(id)){
            return ResultVOUtil.error("1","id不能为空");
        }
        OrderBusiness ob = orderbusinessService.findById(Integer.valueOf(id.trim()));
        if (null == ob){
            return ResultVOUtil.error("1","未查询到id为：" + id + "的信息");
        }
        return ResultVOUtil.success(ob);
    }


    @PostMapping("/batchDeleteob")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    public ResultVO<?> batchDeleteob(@ApiParam(value = "结算单维度名称",required = true) @RequestParam("ids")String ids){

        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","结算单维度ids不能为空");
        }

        return orderbusinessService.batchDeleteob(ids);
    }


    @PostMapping("/addOrderBusiness")
    @ApiOperation(value = "新增结算类型-订购量",notes = "返回处理结果，false或true")
    public ResultVO<?> addOrderBusiness(@ApiParam(value = "名称",required = true) @RequestParam("name")String name,
                                        @ApiParam(value = "状态(0:启用;1:禁用;默认启用)",required = true) @RequestParam("status")String status,
                                        @ApiParam(value = "备注",required = false) @RequestParam("note")String note) {

        if (StringUtils.isBlank(name)){
            return ResultVOUtil.error("1","结算类型-业务级name不能为空");
        }

        if (StringUtils.isBlank(status)){
            return ResultVOUtil.error("1","结算类型-业务级status不能为空");
        }

        return orderbusinessService.insterOrderBusiness(name,status,note);
    }

}
