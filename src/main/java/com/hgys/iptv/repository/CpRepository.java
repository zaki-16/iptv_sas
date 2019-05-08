package com.hgys.iptv.repository;

import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.SettlementDimension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CpRepository extends JpaRepository<Cp,Integer>, JpaSpecificationExecutor<Cp> {

    /**
     * 通过code查询
     * @param code
     * @return
     */
    Cp findByCode(String code);

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    Cp findByName(String name);

    /**
     * 通过ID逻辑删除
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update Cp set isdelete = 1 WHERE id = ?1")
    void logicDelete(int id);

//    Page<Cp> findAll(Specification<Cp> specification, Pageable pageable);
}
