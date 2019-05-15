package com.hgys.iptv.repository;


import com.hgys.iptv.model.OrderBusinessCp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SmallOrderCpRepository extends JpaRepository<OrderBusinessCp,Object>, JpaSpecificationExecutor<OrderBusinessCp> {

    /**
     * 根据masterCode删除
     * @param bucode
     */
    @Modifying
    @Query(value = "delete from OrderBusinessCp where bucode = ?1")
    void deleteByMasterCode(String bucode);

    /**
     * 根据业务表Code删除CP的数据
     * @param obcode
     */
    @Modifying
    @Query(value = "delete from OrderBusinessCp where obcode = ?1")
    void deleteByMastercodes(String obcode);


    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o from OrderBusinessCp o where o.bucode = ?1")
    List<OrderBusinessCp> findByBUCode(String Code);



    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o from OrderBusinessCp o where o.bucode = ?1")
    List<OrderBusinessCp> findByMasterCodes(String Code);

}

