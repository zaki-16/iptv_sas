package com.hgys.iptv.repository;

import com.hgys.iptv.model.OrderQuantityWithCp;
import com.hgys.iptv.model.SettlementCombinatorialDimensionFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderQuantityWithCpRepository extends JpaRepository<OrderQuantityWithCp,Object>, JpaSpecificationExecutor<OrderQuantityWithCp> {

    /**
     * 编码查询
     * @param code
     * @return
     */
    @Query(value = "select o from OrderQuantityWithCp o where o.code = ?1")
    List<OrderQuantityWithCp> findByMasterCode(String code);


}
