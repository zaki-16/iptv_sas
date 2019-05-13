package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.AccountSettlementService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountSettlementServiceImpl implements AccountSettlementService {
    /**
     * 新增分配结算
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> addAccountSettlement() {
        return null;
    }
}
