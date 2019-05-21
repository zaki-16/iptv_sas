package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementBusinessRepository extends JpaRepository<SettlementBusiness,Object>, JpaSpecificationExecutor<SettlementBusiness> {

    @Modifying
    @Query(value = "delete from SettlementBusiness where masterCode = ?1")
    void deleteByMasterCode(String masterCode);

    List<SettlementBusiness> findByMasterCode(String masterCode);
}
