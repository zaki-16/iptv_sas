package com.hgys.iptv.controller;

import com.hgys.iptv.model.dto.ChartVMForBiz;
import com.hgys.iptv.model.dto.ChartVMForCp;
import com.hgys.iptv.model.dto.ChartVMForProd;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.impl.SettleDataOfCpServiceImpl_;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @ClassName SettleDataOfCpController
 * @Auther: wangz
 * @Date: 2019/5/28 16:09
 * @Description: TODO
 */
@RestController
@RequestMapping("/data")
@Api(value = "SettleDataOfCpController",tags = "CP结算统计Api接口")
public class SettleDataOfCpController {

    @Autowired
    private SettleDataOfCpServiceImpl_ settleDataOfCpServiceImpl_;

    @GetMapping("/getCpSettleDataOfChart")
    @ApiOperation(value = "cp结算统计表")
    public Set<ChartVMForCp> getCpSettleDataOfChart(String startTime, String endTime,
                                                    @ApiParam("cp名称")String cpName){
        return settleDataOfCpServiceImpl_.getCpSettleDataOfChart(startTime,endTime,cpName);
    }

    @ApiOperation(value = "cp结算统计饼状图")
    @GetMapping("/getCpSettleDataOfPie")
    public ResultVO getCpSettleDataOfPie(String startTime,String endTime,
                                         @ApiParam("cp名称")String cpName){
        return settleDataOfCpServiceImpl_.getCpSettleDataOfPie(startTime,endTime,cpName);
    }

//    @GetMapping("/getCpSettleDataForPie")
//    public ResultVO getCpSettleDataForPie(CpSettlementMoneyDTO cpSettlementMoneyDTO){
//        return settleDataOfCpService.getCpSettleDataForPie(cpSettlementMoneyDTO);
//    }


    @ApiOperation(value = "业务结算统计表")
    @GetMapping("/getBizSettleDataOfChart")
    public Set<ChartVMForBiz> getBizSettleDataOfChart(String startTime,
                                                      String endTime,
                                                      @ApiParam("业务编码集合字符串") String codes){
        return settleDataOfCpServiceImpl_.getBizSettleDataOfChart(startTime,endTime,codes);
    }


    @ApiOperation(value = "业务结算统计饼状图")
    @GetMapping("/getBizSettleDataForPie")
    public ResultVO getBizSettleDataForPie(
            @ApiParam("业务结算起始时间")String startTime,
            @ApiParam("业务结算结束时间")String endTime,
            @ApiParam("业务编码集合字符串")String codes){
        return settleDataOfCpServiceImpl_.getBizSettleDataOfPie(startTime,endTime,codes);
    }



    @ApiOperation(value = "产品结算统计表")
    @GetMapping("/getProdSettleDataOfChart")
    public Set<ChartVMForProd> getProdSettleDataOfChart(String startTime,
                                                        String endTime,
                                                        @ApiParam("产品编码集合字符串") String codes){
        return settleDataOfCpServiceImpl_.getProdSettleDataOfChart(startTime,endTime,codes);
    }



}
