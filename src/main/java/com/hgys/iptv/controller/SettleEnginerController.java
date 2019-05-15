package com.hgys.iptv.controller;

import com.hgys.iptv.model.dto.SettleDTO;
import com.hgys.iptv.service.SettleEnginerService;
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
    private SettleEnginerService service;
    /**
     * 根据结算类型和结算方式统一调度结算规则引擎
     *     约定-
     *      * 结算类型 settleType:
     *      * CP定比例结算=1
     *      * 业务级结算=2
     *      * 业务定比例结算=3
     *      * 订购量结算=4
     *      * 产品级结算=5
     *      * *
     *     结算方式类型 settleModeType：
     *     * 无=0，即直接根据结算类型就可完成分账的方式，如cp定比例
     *     * 定比例=1
     *     * 定金额=2
     *@param settleDTO
     */
    @PostMapping
    @ApiOperation(value = "结算统一接口",notes = "@return：处理结果")
    @ResponseStatus(HttpStatus.OK)
    public void settleRulerDispatcher(SettleDTO settleDTO){
        Integer settleType = settleDTO.getSettleType();
        Integer settleModeType = settleDTO.getSettleModeType();
        if(settleType==1){// CP定比例结算
            if(settleModeType==0){//没有进一步细分规则
                service.settleByCp(settleDTO);
            }
        }else if(settleType==2){//业务级结算
            if(settleModeType==0){//没有进一步细分规则
                service.settleByBusiness(settleDTO);
            }
        }else if(settleType==3){// 业务定比例结算
            if(settleModeType==1){//业务级-按比例
                service.settleByBusinessWithRatio(settleDTO);
            }else if(settleModeType==2){//业务级-按金额
                service.settleByBusinessWithAmount(settleDTO);
            }
        }else if(settleType==4){// 订购量结算
            if(settleModeType==0){
                service.settleByQuantity(settleDTO);
            }
        }else if(settleType==5){// 产品级结算
            if(settleModeType==1){//产品级-单维度
                service.settleByProdWithSingleDime(settleDTO);
            }else if(settleModeType==2){//产品级-多维度
                service.settleByProdWithCombDime(settleDTO);
            }
        }
    }
}
