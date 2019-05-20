package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpSettlementMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CpSettlementMoneyRepository extends JpaRepository<CpSettlementMoney,Object>, JpaSpecificationExecutor<CpSettlementMoney> {

    @Modifying
    @Query(value = "delete from CpSettlementMoney  where masterCode = ?1")
    void deleteByMasterCode(String masterCode);
}
