package com.hgys.iptv.controller;

import com.hgys.iptv.model.OrderProduct;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.OrderProductService;
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
@RequestMapping("/orderproduct")
@Api(value = "orderproduct",tags = "结算类型-产品级Api接口")
public class OrderProductController {

    @Autowired
    private OrderProductService orderproductService;

    @PostMapping("/selectById")
    @ApiOperation(value = "通过id查询",notes = "返回json数据类型")
    public ResultVO<?> findById(@ApiParam(value = "用户ID",required = true) @RequestParam("id")String id){
        if (StringUtils.isBlank(id)){
            return ResultVOUtil.error("1","id不能为空");
        }
        OrderProduct op = orderproductService.findById(Integer.valueOf(id.trim()));
        if (null == op){
            return ResultVOUtil.error("1","未查询到id为：" + id + "的信息");
        }
        return ResultVOUtil.success(op);
    }


    @PostMapping("/batchDeleteop")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    public ResultVO<?> batchDeleteop(@ApiParam(value = "结算单维度名称",required = true) @RequestParam("ids")String ids){

        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","结算单维度ids不能为空");
        }

        return orderproductService.batchDeleteop(ids);
    }



}
