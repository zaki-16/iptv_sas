package com.hgys.iptv.repository;

import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    /**
     * 通过code查询
     * @param code
     * @return
     */
    Product findByCode(String code);

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    Product findByName(String name);

    /**
     * 通过ID逻辑删除
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update Cp set isdelete = 1 WHERE id = ?1")
    void logicDelete(int id);
}
