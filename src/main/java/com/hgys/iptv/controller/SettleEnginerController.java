package com.hgys.iptv.controller;

import com.hgys.iptv.model.dto.SettleByCpDTO;
import com.hgys.iptv.model.dto.SettleMetaResource;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.impl.SettleEnginerServiceImpl;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SettleEnginerController
 * @Auther: wangz
 * @Date: 2019/5/14 15:44
 * @Description: TODO
 */
@RestController
@RequestMapping("/settle")
@Api(value = "SettleEnginerController",tags = "结算策略Api接口")
public class SettleEnginerController {

    @Autowired
    private SettleEnginerServiceImpl service;

    @PostMapping
    @ApiOperation(value = "结算策略统一接口",notes = "@return：处理结果")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> settleDispather(SettleMetaResource resource, String settleType){
        switch (settleType){
            case "1":service.settleByCp((SettleByCpDTO)resource);
            default:
                return ResultVOUtil.error("1","没有对应的结算策略，请核对结算类型！");
        }
    }
}
