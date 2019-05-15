package com.hgys.iptv.service;

import com.hgys.iptv.model.vo.ResultVO;

public interface SettlementService {

    ResultVO<?> settlement(String id);
}
