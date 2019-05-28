package com.hgys.iptv.service.impl;


import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.SettlementStatisticsService;
import com.hgys.iptv.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettlementStatisticsServiceImpl implements SettlementStatisticsService {

    @Autowired
    private SettlementStatisticsRepository settlementStatisticsRepository;


    @Override
    public ResultVO findsettlement(String name, String startTime, String endTime) {
        if (startTime == null & endTime == null ) {
            if(name==null||name=="") {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlement();
                return  ResultVOUtil.success(settlementList);
            }else{
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlementname(name);
                return  ResultVOUtil.success(settlementList);
            }
        } else {
            if(name==null||name=="") {
            List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlement(startTime,endTime);
                return  ResultVOUtil.success(settlementList);
        }else{
                List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlementname(startTime,endTime,name);
                return  ResultVOUtil.success(settlementList);
            }
        }

    }
}