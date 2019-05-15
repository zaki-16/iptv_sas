package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementProductMany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementProductManyRepository extends JpaRepository<SettlementProductMany,Object>, JpaSpecificationExecutor<SettlementProductMany> {
}
