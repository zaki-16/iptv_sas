package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementProductSingle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementProductSingleRepository extends JpaRepository<SettlementProductSingle,Object>, JpaSpecificationExecutor<SettlementProductSingle> {
}
