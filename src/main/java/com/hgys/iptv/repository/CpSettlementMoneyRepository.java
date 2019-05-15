package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpSettlementMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CpSettlementMoneyRepository extends JpaRepository<CpSettlementMoney,Object>, JpaSpecificationExecutor<CpSettlementMoney> {
}
