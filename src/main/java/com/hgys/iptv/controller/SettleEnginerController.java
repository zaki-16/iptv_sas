package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.AccountSettlementAddVM;
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
     *@param vm
     */
    @PostMapping
    @ApiOperation(value = "结算统一接口",notes = "@return：处理结果")
    @ResponseStatus(HttpStatus.OK)
    public void settleRulerDispatcher(AccountSettlementAddVM vm){
        SettleDTO settleDTO = new SettleDTO();
        String set_ruleName = vm.getSet_ruleName();//结算规则名称
        Integer settleType = vm.getSet_type();//结算类型
        String settleRuleCode = vm.getSet_ruleCode();//结算规则编码

        Integer settleModeType=0;
//        settleDTO.setSettleModeType(settleModeType);
        /**
         * 1.根据 settleType 确定找哪张源数据表
         * 2.根据 settleRuleCode 查所有的源数据List, 取第一个对象中的 结算方式字段
         * 确定是按金额还是按比例结算，有些 settleType 不需要取，直接按比例
         * 3.取每个对象的权重对总金额划分，set 对象的分账字段
         * 如 业务级结算 直接按总收入定比例，如果是cp级，需要先确定是按金额还是按比例结算
         * 4.
         */
        settleDTO.setSettleName(set_ruleName);
        settleDTO.setSettleName(settleRuleCode);
        settleDTO.setSettleType(settleType);

        //1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算
        if(settleType==1){//1:订购量结算;直接按比例
            service.settleByQuantity(settleDTO);
        }else if(settleType==2){//2:业务级结算;直接按比例
            service.settleByBusiness(settleDTO);
        }else if(settleType==3){//3:产品级结算
//            settleModeType = 按 settleRuleCode 取出所有的分账对象，取settleModeType字段
        settleDTO.setSettleModeType(settleModeType);
            if(settleModeType==1){
                service.settleByProdWithSingleDime(settleDTO);//单维度
            }else if(settleModeType==2){
                service.settleByProdWithCombDime(settleDTO);//多维度
            }
        }else if(settleType==4){//4:CP定比例结算
            service.settleByCp(settleDTO);
        }else if(settleType==5){
            if(settleModeType==1){
                //业务级-按比例
                service.settleByBusinessWithRatio(settleDTO);
            }else if(settleModeType==2){
                //业务级-按金额
                service.settleByBusinessWithAmount(settleDTO);
            }
        }
    }
}
