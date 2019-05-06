package com.hgys.iptv.service;


import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.vo.ResultVO;

public interface SettlementDimensionService {
    /**
     * 新增
     * @param name
     * @param status
     * @param remarks
     */
    ResultVO<?> insterSettlementDimension(String name, String status, String remarks);

    /**
     * 通过code查询
     * @param code
     * @return
     */
    SettlementDimension findByCode(String code);

    /**
     * 通过Id,批量逻辑删除
     * @param ids
     * @return
     */
    ResultVO<?> batchLogicDelete(String ids);

    /**
     * 修改结算维度信息
     * @param vo
     * @return
     */
    ResultVO<?> updateSettlementDimension(SettlementDimension vo);

    /**
     * 根据条件分页查询
     * @return
     */
    ResultVO<?> findByConditions(String name,String code,String status,String pageNum,String pageSize);

}
