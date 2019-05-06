package com.hgys.iptv.service;


import com.hgys.iptv.model.SettlementDimension;

public interface SettlementDimensionService {
    /**
     * 新增
     * @param vo
     */
   void insterSettlementDimension(SettlementDimension vo);

    /**
     * 通过Id查询
     * @param id
     * @return
     */
    SettlementDimension findById(Integer id);

}
