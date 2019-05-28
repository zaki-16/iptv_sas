package com.hgys.iptv.service.impl;


import com.hgys.iptv.controller.assemlber.OrderQuantityControllerAssemlber;
import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.OrderQuantityWithCp;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.OrderQuantityService;
import com.hgys.iptv.service.SettlementStatisticsService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.event.service.internal.EventListenerServiceInitiator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SettlementStatisticsServiceImpl implements SettlementStatisticsService {

    @Autowired
    private SettlementStatisticsRepository settlementStatisticsRepository;


    @Override
    public List<AccountSettlement> findsettlement(String name, String startTime, String endTime) {
        if (startTime == null & endTime == null ) {
            if(name==null||name=="") {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlement();
                return settlementList;
            }else{
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlementname(name);
                return settlementList;
            }
        } else {
            if(name==null||name=="") {
            List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlement(startTime,endTime);
            return settlementList;
        }else{
                List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlementname(startTime,endTime,name);
                return settlementList;
            }
        }

    }
}