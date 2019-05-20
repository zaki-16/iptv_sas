package com.hgys.iptv.repository;

import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.CpProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "select o.name from Cp o where o.code = ?1")
    String findByMasterCodes(String Code);


     /**
     * 只查询未删除的cp
     * @param
     * @return
     */
    @Query(value = "select o from Cp o where o.isdelete = 0")
    List<Cp> findcplist();


    @Query(value = "select o.name from Cp o where o.id = ?1")
    String findByMastercpname(Integer code);


    List<Cp> findByStatusAndIsdelete(int status, int Isdelete);



//    Page<Cp> findAll(Specification<Cp> specification, Pageable pageable);


    /**
     *
     * @return
     */
//    @Query(value = "select new map(t1,t2) from  TCity t1 left  join THotel t2 on t1.id=t2.city where t2.name =:name")
//    List<Map<String, Object>> findCpAndProductByHQL(@Param("id") Integer id);
//
//    @Query(value = "select new map(t1,t2) from  TCity t1 left  join THotel t2 on t1.id=t2.city where t2.name =:name")
//    List<Map<String, Object>> findCpAndBusinessByHQL(@Param("id") Integer id);

}
