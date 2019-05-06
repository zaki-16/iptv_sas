package com.hgys.iptv.repository;

import com.hgys.iptv.model.OrderCp;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCpRepository extends BaseRepository<OrderCp,Integer> {
    /**
     * 通过ID逻辑删除
     */
    @Modifying
    @Query(value = "update OrderQuantity set isdelete = 1 WHERE id = ?1")
    void batchDeleteoc(int id);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    OrderCp findById(String id);


}
