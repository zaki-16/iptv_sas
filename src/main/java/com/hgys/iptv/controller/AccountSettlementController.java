package com.hgys.iptv.controller;

import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.AccountSettlementService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@RestController()
@RequestMapping("/accountSettlement")
@Api(value = "accountSettlement",tags = "分账结算Api接口")
public class AccountSettlementController {

    @Autowired
    private AccountSettlementService accountSettlementService;

    @PostMapping(value = "addAccountSettlement")
    @ApiOperation(value = "新增分配结算",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> addAccountSettlement(@ApiParam(value = "上传的excel文件") MultipartFile file,
                                            @ApiParam(value = "名称",required = true) @RequestParam(value = "name") String name,
                                            @ApiParam(value = "结算规则编码",required = true) @RequestParam(value = "setRuleCode",required = true) String setRuleCode,
                                            @ApiParam(value = "状态",required = true) @RequestParam(value = "status") Integer status,
                                            @ApiParam(value = "备注") @RequestParam(value = "remakes",required = false) String remakes,
                                            @ApiParam(value = "结算开始时间",required = true) @RequestParam(value = "setStartTime") Timestamp setStartTime,
                                            @ApiParam(value = "结算结束时间",required = true) @RequestParam(value = "setEndTime") Timestamp setEndTime
                                            ){

        if (StringUtils.isBlank(name)){
            return ResultVOUtil.error("1","分配结算name不能为空");
        }

        if (StringUtils.isBlank(setRuleCode)){
            return ResultVOUtil.error("1","分配结算setRuleCode不能为空");
        }

        return accountSettlementService.addAccountSettlement();
    }
}
