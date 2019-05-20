package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementProductSingle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementProductSingleRepository extends JpaRepository<SettlementProductSingle,Object>, JpaSpecificationExecutor<SettlementProductSingle> {

    List<SettlementProductSingle> findByMasterCode(String masterCode);

    @Modifying
    @Query(value = "delete from SettlementProductSingle where masterCode = ?1")
    void deleteByMasterCode(String masterCode);
}
