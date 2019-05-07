package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementDimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettlementDimensionRepository extends JpaRepository<SettlementDimension,Object>, JpaSpecificationExecutor<SettlementDimension> {
    /**
     * 通过code查询
     * @param code
     * @return
     */
    Optional<SettlementDimension> findByCode(String code);

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    SettlementDimension findByName(String name);

    /**
     * 通过ID逻辑删除
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update SettlementDimension set isdelete = 1 WHERE id = ?1")
    void batchLogicDelete(int id);

}
