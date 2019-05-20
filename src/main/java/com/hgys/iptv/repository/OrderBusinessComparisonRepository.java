package com.hgys.iptv.repository;

import com.hgys.iptv.model.OrderBusinessComparison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderBusinessComparisonRepository extends JpaRepository<OrderBusinessComparison,Object>, JpaSpecificationExecutor<OrderBusinessComparison> {

    @Modifying
    @Query(value = "update OrderBusinessComparison set isdelete = 1 WHERE id = ?1")
    void batchLogicDelete(Integer id);

    Optional<OrderBusinessComparison> findByCode(String code);

    Optional<OrderBusinessComparison> findByName(String name);
    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o.name from OrderBusinessComparison o where o.code = ?1")
    String findByMasterCodes(String Code);

}
