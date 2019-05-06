package com.hgys.iptv.repository;

import com.hgys.iptv.model.SettlementDimension;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementDimensionRepository extends BaseRepository<SettlementDimension,String> {
    /**
     * 通过code查询
     * @param code
     * @return
     */
    SettlementDimension findByCode(String code);

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    SettlementDimension findByName(String name);

    /**
     * 通过ID逻辑删除
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update SettlementDimension set isdelete = 1 WHERE id = ?1")
    void batchLogicDelete(int id);

}
