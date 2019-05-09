package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpAccountSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CpAccountSettlementRepository extends JpaRepository<CpAccountSettlement,Object>, JpaSpecificationExecutor<CpAccountSettlement> {
}
