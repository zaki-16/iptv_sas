package com.hgys.iptv.repository;

import com.hgys.iptv.model.OrderCpWithCp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCpWithCpRepository extends JpaRepository<OrderCpWithCp,Object>, JpaSpecificationExecutor<OrderCpWithCp> {

    /**
     * 通过结算组合维度编码查询
     * @param cpcode
     * @return
     */
    @Query(value = "select o from OrderCpWithCp o where o.cpcode = ?1")
    List<OrderCpWithCp> findByMasterCode(String cpcode);


    /**
     * 通过code逻辑删除
     * @param occode
     * @return
     */
    @Modifying
    @Query(value = "update OrderCpWithCp set isdelete = 1 WHERE occode = ?1")
    void batchLogicDeleteByCode(String occode);


    /**
     * 根据masterCode删除
     * @param occode
     */
    @Modifying
    @Query(value = "delete from OrderCpWithCp where occode = ?1")
    void deleteByMasterCode(String occode);
}
