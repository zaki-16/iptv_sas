package com.hgys.iptv.repository;

import com.hgys.iptv.model.OrderBusiness;
import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderBusinessRepository extends BaseRepository<OrderBusiness,Integer> {

    /**
     * 通过ID逻辑删除
     */
    @Modifying
    @Query(value = "update OrderBusiness set isdelete = 1 WHERE id = ?1")
    void batchDeleteob(int id);


    /**
     * 通过id查询
     * @param id
     * @return
     */
    OrderBusiness findById(String id);
    Optional<OrderBusiness> findByName(String name);
}
