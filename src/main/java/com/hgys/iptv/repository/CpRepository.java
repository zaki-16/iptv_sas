package com.hgys.iptv.repository;

import com.hgys.iptv.controller.vm.CpListVm;
import com.hgys.iptv.model.Cp;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CpRepository extends BaseRepository<Cp,Integer> {

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


    @Query(value = "select o.code from Cp o where o.id = ?1")
    String findByMastercpcode(Integer code);

    List<Cp> findByStatusAndIsdelete(int status, int Isdelete);


    /**
     * SELECT * FROM cp cp,product p,cp_product cpp,business b,cp_business cpb
     * WHERE cp.id=cpp.cpid AND cpp.pid=p.id AND p.name='电竞世界'
     * AND cp.id=cpb.cpid AND cpb.bid=b.id AND b.id=36
     * AND cp.name='炫佳' AND cp.status=4 AND 1=1
     */
    @Query(value = "SELECT DISTINCT cpp.id id,cp.name cpname,cp.cpAbbr cpAbbr,cp.status cpstatus, " +
            "p.id pid,p.name pname, " +
            "b.id bid " +
            "FROM cp cp,product p,cp_product cpp,business b,cp_business cpb " +
            "WHERE cp.id=cpp.cpid " +
            "AND cpp.pid=p.id " +
            "AND cp.id=cpb.cpid " +
            "AND cpb.bid=b.id " +
            "AND 1=1",nativeQuery = true)
    List<CpListVm> findCp();



//    Page<Cp> findAll(Specification<Cp> specification, Pageable pageable);
//    @Param("pname") String pname,
//            @Param("bid") int bid,
//            @Param("name") String name,
//            @Param("status") int status


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
