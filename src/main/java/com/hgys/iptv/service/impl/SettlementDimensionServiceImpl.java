package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.repository.SettlementDimensionRepository;
import com.hgys.iptv.service.SettlementDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettlementDimensionServiceImpl implements SettlementDimensionService {
    @Autowired
    private SettlementDimensionRepository settlementDimensionRepository;

    @Override
    public void insterSettlementDimension(SettlementDimension vo) {
        settlementDimensionRepository.save(vo);
    }

    @Override
    public SettlementDimension findById(Integer id) {
        //如果未查询到返回null
        return settlementDimensionRepository.findById(id).orElse(null);
    }
}
