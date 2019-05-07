package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettlementCombinatorialDimensionMasterRepository extends JpaRepository<SettlementCombinatorialDimensionMaster,Object> , JpaSpecificationExecutor<SettlementCombinatorialDimensionMaster> {

    Optional<SettlementCombinatorialDimensionMaster> findByCode(String code);

    Optional<SettlementCombinatorialDimensionMaster> findByName(String name);

    /**
     * 通过ID逻辑删除
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update SettlementCombinatorialDimensionMaster set isdelete = 1 WHERE id = ?1")
    void batchLogicDelete(int id);
}

