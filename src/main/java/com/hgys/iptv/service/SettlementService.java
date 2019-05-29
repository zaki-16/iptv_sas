package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.ProductLevelStatisticsVM;
import com.hgys.iptv.model.vo.ResultVO;

import java.util.List;

public interface SettlementService {

    ResultVO<?> settlement(String id);

    ResultVO<?> cancel(String id);

    List<ProductLevelStatisticsVM> productLevelStatistics(String startTime, String endTime, String productCodeList);
}
