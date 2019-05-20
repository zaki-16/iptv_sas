package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SettlementMoneyRepository extends JpaRepository<SettlementMoney,Object>, JpaSpecificationExecutor<SettlementMoney> {

    List<SettlementMoney> findByMasterCode(String masterCode);
    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o.money from SettlementMoney o where o.masterCode= ?1")
    BigDecimal findByMastermoney(String Code);

    @Modifying
    @Query(value = "delete from SettlementMoney where masterCode = ?1")
    void deleteByMasterCode(String masterCode);
}
