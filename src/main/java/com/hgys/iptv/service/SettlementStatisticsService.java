package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface SettlementStatisticsService {



    /**
     * 查询
     * @return
     */
    List<AccountSettlement> findsettlement(String name, String startTime, String endTime );




}
