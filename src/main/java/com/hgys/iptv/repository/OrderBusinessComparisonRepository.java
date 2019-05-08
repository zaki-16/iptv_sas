package com.hgys.iptv.repository;

import com.hgys.iptv.model.OrderBusinessComparison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBusinessComparisonRepository extends JpaRepository<OrderBusinessComparison,Object>, JpaSpecificationExecutor<OrderBusinessComparison> {
}
