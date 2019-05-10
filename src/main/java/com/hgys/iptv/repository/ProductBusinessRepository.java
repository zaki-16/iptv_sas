package com.hgys.iptv.repository;

import com.hgys.iptv.model.ProductBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface ProductBusinessRepository extends JpaRepository<ProductBusiness,Integer>, JpaSpecificationExecutor<ProductBusiness> {

    /**
     * 根据产品id查询中间表下属业务bid集合
     * @param pid
     * @return
     */
    @Query(value = "select pb.bid from ProductBusiness pb where pb.pid = ?1")
    Set<Integer> findAllBid(Integer pid);
}
