package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionAddVM;
import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionControllerListVM;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SettlementCombinatorialDimensionService {

    /**
     * 新增结算组合维度
     *
     * @param vo
     */
    ResultVO<?> addSettlementCombinatorialDimension(SettlementCombinatorialDimensionAddVM vo);

    /**
     * 批量逻辑删除
     *
     * @param ids
     * @return
     */
    ResultVO<?> batchLogicDelete(String ids);

    /**
     * 通过code查询结算组合数据
     * @param code
     * @return
     */
    SettlementCombinatorialDimensionControllerListVM getSettlementCombinatorialDimension(String code);

    /**
     * 通过id查询结算组合数据
     * @param id
     * @return
     */
    SettlementCombinatorialDimensionControllerListVM findById(String id);

    /**
     * 根据条件分页查询
     * @return
     */
    Page<SettlementCombinatorialDimensionControllerListVM> findByConditions(String name, String code, String status, Pageable pageable);

    /**
     * 修改
     * @param vo
     * @return
     */
    ResultVO<?> updateCombinatorialDimension(SettlementCombinatorialDimensionAddVM vo);

    /**
     * 通过结算组合维度编码查询结算单维度信息
     * @param code
     * @return
     */
    ResultVO<?> getSettlementDimensionList(String code);



    ResultVO<?> findcdslist();
}
