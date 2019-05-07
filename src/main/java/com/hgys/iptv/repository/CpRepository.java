package com.hgys.iptv.repository;

import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.SettlementDimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CpRepository extends JpaRepository<Cp,Integer> {

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
}
