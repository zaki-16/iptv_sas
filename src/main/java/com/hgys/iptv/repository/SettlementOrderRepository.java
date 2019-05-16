package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementOrderRepository extends JpaRepository<SettlementOrder,Object>, JpaSpecificationExecutor<SettlementOrder> {
}
