package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.SettlementStatisticsListVM;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.vo.ResultVO;


public interface RuleStatisticsService {



    /**
     * 查询
     * @return
     */
    SettlementStatisticsListVM findsettlement(AccountSettlement a);

    /**
     * 查询
     * @return
     */
    ResultVO findsettlementList(String name, String startTime, String endTime,String set_type,String set_ruleName );


}
