package com.hgys.iptv.repository;

import com.hgys.iptv.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
