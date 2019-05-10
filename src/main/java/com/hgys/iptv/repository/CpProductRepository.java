package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CpProductRepository extends JpaRepository<CpProduct,Integer>, JpaSpecificationExecutor<CpProduct> {

    /**
     * 通过cpid查询中间表其下属产品pid集合
     */
    @Query(value = "select cpp.pid from CpProduct cpp where cpp.cpid=?1")
    Set<Integer> findAllPid(Integer cpid);
}
