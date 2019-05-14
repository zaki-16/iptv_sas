package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettlementCombinatorialDimensionMasterRepository extends JpaRepository<SettlementCombinatorialDimensionMaster,Object> , JpaSpecificationExecutor<SettlementCombinatorialDimensionMaster> {

    Optional<SettlementCombinatorialDimensionMaster> findByCode(String code);

    Optional<SettlementCombinatorialDimensionMaster> findByName(String name);

    /**
     * 通过ID逻辑删除
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update SettlementCombinatorialDimensionMaster set isdelete = 1 WHERE id = ?1")
    void batchLogicDelete(Integer id);




    /**
     * 通过code查询名字
     * @param Code
     * @return
     */
    @Query(value = "select o.name from SettlementCombinatorialDimensionMaster o where o.code = ?1")
    String findscdCodes(String Code);


    /**
     * 只查询未删除的产品
     * @param
     * @return
     */
    @Query(value = "select o from SettlementCombinatorialDimensionMaster o where o.isdelete = 0")
    List<SettlementCombinatorialDimensionMaster> findcdslist();



}

