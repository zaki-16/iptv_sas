package com.hgys.iptv.service;

import com.hgys.iptv.model.vo.ResultVO;


public interface SettlementStatisticsService {



    /**
     * 查询
     * @return
     */
    ResultVO findsettlement(String name, String startTime, String endTime );




}
