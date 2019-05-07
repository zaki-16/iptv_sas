package com.hgys.iptv.repository;


import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.SettlementDimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
    public interface OrderQuantityRepository extends JpaRepository<OrderQuantity,Object>, JpaSpecificationExecutor<OrderQuantity> {
    /**
     * 通过ID逻辑删除
     */
    @Modifying
    @Query(value = "update OrderQuantity set isdelete = 1 WHERE id = ?1")
    void batchDelete(int id);

    /**
     * 通过code查询
     * @param code
     * @return
     */
    Optional<OrderQuantity> findByCode(String code);



}



