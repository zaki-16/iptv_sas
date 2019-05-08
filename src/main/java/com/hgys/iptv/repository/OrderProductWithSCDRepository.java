package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpOrderBusinessComparison;
import com.hgys.iptv.model.OrderProductWithSCD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductWithSCDRepository extends JpaRepository<OrderProductWithSCD,Object>, JpaSpecificationExecutor<OrderProductWithSCD> {

    @Query(value = "select o from OrderProductWithSCD o where o.opcode = ?1")
    List<OrderProductWithSCD> findByMasterCode(String code);

    void deleteByMasterCode(String masterCode);
}
