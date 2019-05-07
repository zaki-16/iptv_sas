package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpOrderBusinessComparison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CpOrderBusinessComparisonRepository extends JpaRepository<CpOrderBusinessComparison,Object>, JpaSpecificationExecutor<CpOrderBusinessComparison> {
}
