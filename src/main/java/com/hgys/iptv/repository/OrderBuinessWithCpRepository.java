package com.hgys.iptv.repository;

import com.hgys.iptv.model.OrderBusinessWithCp;
import com.hgys.iptv.model.OrderCpWithCp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBuinessWithCpRepository extends JpaRepository<OrderBusinessWithCp,Object>, JpaSpecificationExecutor<OrderBusinessWithCp> {

    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o from OrderBusinessWithCp o where o.code = ?1")
    List<OrderBusinessWithCp> findByMasterCode(String Code);


    /**
     * 通过code逻辑删除
     * @param obcode
     * @return
     */
    @Modifying
    @Query(value = "update OrderBusinessWithCp set isdelete = 1 WHERE obcode = ?1")
    void batchLogicDeleteByCode(String obcode);


    /**
     * 根据masterCode删除
     * @param occode
     */
    @Modifying
    @Query(value = "delete from OrderBusinessWithCp where occode = ?1")
    void deleteByMasterCode(String occode);
}
