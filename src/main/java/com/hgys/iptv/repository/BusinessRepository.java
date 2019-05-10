package com.hgys.iptv.repository;

import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.SettlementCombinatorialDimensionFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BusinessRepository extends JpaRepository<Business,Integer>, JpaSpecificationExecutor<Business> {
    /**
     * 通过code查询
     * @param code
     * @return
     */
    Business findByCode(String code);

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    Business findByName(String name);

    /**
     * 通过ID逻辑删除
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update Business set isdelete = 1 WHERE id = ?1")
    void logicDelete(int id);

    List<Business> findByStatusAndIsdelete(int status, int Isdelete);



    /**
     * 通过业务Code查询业务的名称
     * @param Code
     * @return
     */
    @Query(value = "select o.name from Business o where o.code = ?1")
    String findByMasterCodes(String Code);



}
