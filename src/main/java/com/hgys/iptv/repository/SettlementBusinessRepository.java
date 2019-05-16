package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementBusinessRepository extends JpaRepository<SettlementBusiness,Object>, JpaSpecificationExecutor<SettlementBusiness> {
}
