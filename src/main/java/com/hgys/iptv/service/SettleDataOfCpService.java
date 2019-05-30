package com.hgys.iptv.service;

import com.hgys.iptv.model.dto.CpSettlementMoneyDTO;
import com.hgys.iptv.model.vo.ResultVO;

public interface SettleDataOfCpService {
    ResultVO getCpSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO);
    ResultVO getCpSettleDataForPie(CpSettlementMoneyDTO cpSettlementMoneyDTO);
    ResultVO getBizSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO);
    ResultVO getBizSettleDataForPie(CpSettlementMoneyDTO cpSettlementMoneyDTO);
    ResultVO getProdSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO);
}
