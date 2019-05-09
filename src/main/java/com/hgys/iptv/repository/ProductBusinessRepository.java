package com.hgys.iptv.repository;

import com.hgys.iptv.model.ProductBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductBusinessRepository extends JpaRepository<ProductBusiness,Integer>, JpaSpecificationExecutor<ProductBusiness> {
        
}
