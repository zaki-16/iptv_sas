package com.hgys.iptv.repository;

import com.hgys.iptv.model.OrderCpWithCp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCpWithCpRepository extends JpaRepository<OrderCpWithCp,Object>, JpaSpecificationExecutor<OrderCpWithCp> {

    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o from OrderCpWithCp o where o.code = ?1")
    List<OrderCpWithCp> findByMasterCode(String Code);
}
