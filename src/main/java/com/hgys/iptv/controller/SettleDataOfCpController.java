package com.hgys.iptv.controller;

import com.hgys.iptv.model.dto.CpSettlementMoneyDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SettleDataOfCpService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private SettleDataOfCpService settleDataOfCpService;

    @GetMapping("/getCpSettleData")
    public ResultVO getCpSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO){
        return settleDataOfCpService.getCpSettleData(cpSettlementMoneyDTO);
    }

    @GetMapping("/getCpSettleDataForPie")
    public ResultVO getCpSettleDataForPie(CpSettlementMoneyDTO cpSettlementMoneyDTO){
        return settleDataOfCpService.getCpSettleDataForPie(cpSettlementMoneyDTO);
    }

    @GetMapping("/getBizSettleData")
    public ResultVO getBizSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO){
        return settleDataOfCpService.getBizSettleData(cpSettlementMoneyDTO);
    }

    @GetMapping("/getProdSettleData")
    public ResultVO getProdSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO){
        return settleDataOfCpService.getProdSettleData(cpSettlementMoneyDTO);
    }
}
