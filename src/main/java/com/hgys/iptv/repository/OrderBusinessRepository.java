package com.hgys.iptv.repository;

import com.hgys.iptv.model.OrderBusiness;
import com.hgys.iptv.model.OrderBusinessComparison;
import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderBusinessRepository extends JpaRepository<OrderBusiness,Object>, JpaSpecificationExecutor<OrderBusiness> {


/**
 * 通过ID逻辑删除
 */
    @Modifying
    @Query(value = "update OrderBusiness set isdelete = 1 WHERE id = ?1")
    void batchDeleteob(int id);
    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o.name from OrderBusiness o where o.code = ?1")
    String findByMasterCodes(String Code);


    /**
     * 通过id查询
     * @param id
     * @return
     */
    OrderBusiness findById(String id);
    Optional<OrderBusiness> findByName(String name);

    Optional<OrderBusiness> findByCode(String code);
}
