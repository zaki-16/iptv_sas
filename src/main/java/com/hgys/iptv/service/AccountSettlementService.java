package com.hgys.iptv.service;

import com.hgys.iptv.model.vo.ResultVO;

import java.util.List;

public interface AccountSettlementService {

    ResultVO<?> addAccountSettlement();

    List<?> excelExport(Integer type,String code);
}
