package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CpProductRepository extends JpaRepository<CpProduct,Integer>, JpaSpecificationExecutor<CpProduct> {

}
