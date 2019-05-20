package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementOrderRepository extends JpaRepository<SettlementOrder,Object>, JpaSpecificationExecutor<SettlementOrder> {
    List<SettlementOrder> findByMasterCode(String masterCode);

    
    @Modifying
    @Query(value = "delete from SettlementOrder where masterCode = ?1")
    void deleteByMasterCode(String masterCode);

}
