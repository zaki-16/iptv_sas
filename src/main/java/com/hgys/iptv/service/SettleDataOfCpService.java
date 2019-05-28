package com.hgys.iptv.service;

import com.hgys.iptv.model.dto.CpSettlementMoneyDTO;
import com.hgys.iptv.model.vo.ResultVO;

public interface SettleDataOfCpService {
    ResultVO getCpSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO);
}
