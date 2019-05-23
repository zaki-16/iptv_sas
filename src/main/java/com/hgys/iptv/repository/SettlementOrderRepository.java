package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SettlementOrderRepository extends JpaRepository<SettlementOrder,Object>, JpaSpecificationExecutor<SettlementOrder> {
    List<SettlementOrder> findByMasterCode(String masterCode);

    
    @Modifying
    @Query(value = "delete from SettlementOrder where masterCode = ?1")
    void deleteByMasterCode(String masterCode);


    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o.orderMoney from settlement_order o where o.masterCode= ?1 limit 1",nativeQuery = true)
    BigDecimal findByMastermoney(String Code);

}
