package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementCombinatorialDimensionFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementCombinatorialDimensionRepository extends JpaRepository<SettlementCombinatorialDimensionFrom,Object> , JpaSpecificationExecutor<SettlementCombinatorialDimensionFrom> {

}

