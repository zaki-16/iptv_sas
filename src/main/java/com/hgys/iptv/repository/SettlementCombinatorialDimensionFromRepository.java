package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementCombinatorialDimensionFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementCombinatorialDimensionFromRepository extends JpaRepository<SettlementCombinatorialDimensionFrom,Object>, JpaSpecificationExecutor<SettlementCombinatorialDimensionFrom> {
    /**
     * 通过code逻辑删除
     * @param code
     * @return
     */
    @Modifying
    @Query(value = "update SettlementCombinatorialDimensionFrom set isdelete = 1 WHERE master_code = ?1")
    void batchLogicDeleteByCode(String code);

    /**
     * 通过结算组合维度编码查询
     * @param masterCode
     * @return
     */
    @Query(value = "select o from SettlementCombinatorialDimensionFrom o where o.master_code = ?1")
    List<SettlementCombinatorialDimensionFrom> findByMasterCode(String masterCode);

    /**
     * 根据masterCode删除
     * @param code
     */
    @Modifying
    @Query(value = "delete from SettlementCombinatorialDimensionFrom where master_code = ?1")
    void deleteByMasterCode(String code);



}
