package com.hgys.iptv.repository;

import com.hgys.iptv.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByPid(Integer pid);
    Product findByCode(String code);
}
