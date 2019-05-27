package com.hgys.iptv.repository;

import com.hgys.iptv.model.AccountSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSettlementRepository extends JpaRepository<AccountSettlement,Object>, JpaSpecificationExecutor<AccountSettlement> {

    /**
     * 通过ID逻辑删除
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update AccountSettlement set isdelete = 1 WHERE id = ?1")
    void batchLogicDelete(int id);


    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o.set_ruleName from AccountSettlement o where o.set_ruleCode= ?1")
    String findByMastername(String Code);


}
