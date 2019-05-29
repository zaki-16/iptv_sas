package com.hgys.iptv.repository;

import com.hgys.iptv.model.OrderBusinessComparison;
import com.hgys.iptv.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct,Object>, JpaSpecificationExecutor<OrderProduct> {

    /**
     * 通过ID逻辑删除
     */
    @Modifying
    @Query(value = "update OrderProduct set isdelete = 1 WHERE id = ?1")
    void batchDeleteop(Integer id);

    Optional<OrderProduct> findByName(String name);
    /**
     * 通过id查询
     * @param id
     * @return
     */
    OrderProduct findById(String id);

    OrderProduct findByCode(String code);

    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o.id from OrderQuantity o where o.code = ?1")
    Integer findByMastid(String Code);
    /**
     * 通过code 查询mode
     * @param code
     * @return
     */
    @Query(value = "select o.mode from OrderProduct o where o.code = ?1")
    int findBymode(String code);

    @Query(value = "select o.name from OrderProduct o where o.code = ?1")
    String findByMasterCodes(String Code);

    @Query(value = "select o from OrderProduct o where o.code = ?1")
    List<OrderProduct> finddetail(String code);
}
