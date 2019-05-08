package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpOrderBusinessComparison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CpOrderBusinessComparisonRepository extends JpaRepository<CpOrderBusinessComparison,Object>, JpaSpecificationExecutor<CpOrderBusinessComparison> {

    @Query(value = "select o from CpOrderBusinessComparison o where o.masterCode = ?1")
    List<CpOrderBusinessComparison> findByMasterCode(String code);

    void deleteByMasterCode(String masterCode);
}
