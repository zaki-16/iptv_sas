package com.hgys.iptv.controller;

import com.hgys.iptv.aop.SystemControllerLog;
import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.CpService;
import com.hgys.iptv.service.OrderQuantityService;
import com.hgys.iptv.service.SettlementStatisticsService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/settlementstatistics")
@Api(value = "settlementstatistics",tags = "结算统计")
public class SettlementStatisticsController {
    @Autowired
    private SettlementStatisticsService settlementStatisticsService;

    @GetMapping("/findsettlement")
    @ApiOperation(value = "通过条件，查询",notes = "返回处理结果")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO findsettlement(@ApiParam(value = "名称") @RequestParam(value = "name",required = false )String name,
                                                  @ApiParam(value = "结算开始时间") @RequestParam(value = "startTime",required = false)String startTime,
                                                  @ApiParam(value = "结算结束时间") @RequestParam(value = "endTime",required = false)String endTime ){
        return settlementStatisticsService.findsettlement(name, startTime, endTime );

    }

}
