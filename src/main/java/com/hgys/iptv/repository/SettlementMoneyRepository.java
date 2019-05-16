package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SettlementMoneyRepository extends JpaRepository<SettlementMoney,Object>, JpaSpecificationExecutor<SettlementMoney> {

    List<SettlementMoney> findByMasterCode(String masterCode);
}
