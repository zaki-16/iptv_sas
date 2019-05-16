package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementMoneyRepository extends JpaRepository<SettlementMoney,Object>, JpaSpecificationExecutor<SettlementMoney> {
}
