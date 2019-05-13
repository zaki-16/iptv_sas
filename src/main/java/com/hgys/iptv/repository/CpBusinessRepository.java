package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface CpBusinessRepository extends JpaRepository<CpBusiness,Integer>, JpaSpecificationExecutor<CpBusiness> {

    /**
     * 通过cpid查询中间表其下属业务bid集合
     */
    @Query(value = "select cpb.bid from CpBusiness cpb where cpb.cpid=?1")
    Set<Integer> findAllBid(Integer cpid);

    @Query(value = "select cpb.cpid from CpBusiness cpb where cpb.bid=?1")
    Set<Integer> findAllCpid(Integer bid);

//    按cpid删中间表
    @Query("delete from CpBusiness where cpid=?1")
    @Modifying
    @Transactional
    void deleteAllByCpid(Integer cpid);

    //    按bid删中间表
    @Query("delete from CpBusiness where bid=?1")
    @Modifying
    @Transactional
    void deleteAllByBid(Integer cpid);
}
