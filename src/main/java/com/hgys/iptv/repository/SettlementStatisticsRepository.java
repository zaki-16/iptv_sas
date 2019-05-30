package com.hgys.iptv.repository;


import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.OrderQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
    public interface SettlementStatisticsRepository extends JpaRepository<AccountSettlement,Object>, JpaSpecificationExecutor<AccountSettlement> {


  /*  @Modifying
    @Query(value = "SELECT o.setStartTime, o.name, o.set_ruleName, o.setEndTime, o.set_type, o.inputTime, o.total_sum FROM AccountSettlement o GROUP BY o.setStartTime ORDER BY o.setStartTime DESC, o.setEndTime DESC")
    List<AccountSettlement> findsettlement();*/
    @Modifying
    @Query(value = "SELECT * FROM account_settlement WHERE isdelete=0 GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
    List<AccountSettlement> findsettlement();

  @Modifying
  @Query(value = "SELECT * FROM account_settlement where name like  %?%  GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> findsettlementname( String name);

  @Modifying
  @Query(value = "SELECT * FROM account_settlement where name like  %?% GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> findsettlementnames( String name);


  @Modifying
  @Query(value = "SELECT * FROM account_settlement where name like  %?% and set_type=? GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> findsettlementnamess( String name,String set_type);


  @Modifying
  @Query(value = "SELECT * FROM account_settlement where name like  %?% and set_type=? and set_ruleName=? GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> findsettlementnamesss( String name,String set_type,String set_ruleName);

  @Modifying
  @Query(value = "SELECT * FROM account_settlement where name like  %?% and set_ruleName=? GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> findsettlementnamessss( String name,String set_ruleName);
    @Modifying
    @Query(value = "SELECT * FROM account_settlement WHERE  (set_startTime>= ? and  set_endTime<= ?) AND name like %?% GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
    List<AccountSettlement> finddatesettlementname(String startTime, String endTime, String name);


  @Modifying
  @Query(value = "SELECT * FROM account_settlement WHERE  (set_startTime>= ? and  set_endTime<= ?) AND name like %?%  and set_type=? GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> finddatesettlementnames(String startTime, String endTime, String name,String set_type);

  @Modifying
  @Query(value = "SELECT * FROM account_settlement WHERE  (set_startTime>= ? and  set_endTime<= ?) AND name like %?%  and set_type=? and set_ruleName=? GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> finddatesettlementnamess(String startTime, String endTime, String name,String set_type,String set_ruleName);

  @Modifying
  @Query(value = "SELECT * FROM account_settlement WHERE  (set_startTime>= ? and  set_endTime<= ?) AND name like %?%   and set_ruleName=? GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> finddatesettlementnamesss(String startTime, String endTime, String name,String set_ruleName);

  @Modifying
  @Query(value = "SELECT * FROM account_settlement WHERE  set_startTime>=? and  set_endTime<=? GROUP BY DATE_FORMAT(set_startTime, '%Y-%M') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> finddatesettlement(String startTime, String endTime);
}



