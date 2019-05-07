package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettlementCombinatorialDimensionMasterRepository extends JpaRepository<SettlementCombinatorialDimensionMaster,Object> , JpaSpecificationExecutor<SettlementCombinatorialDimensionMaster> {

    Optional<SettlementCombinatorialDimensionMaster> findByCode(String code);

    Optional<SettlementCombinatorialDimensionMaster> findByName(String name);
}

