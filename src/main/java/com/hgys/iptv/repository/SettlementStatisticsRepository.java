package com.hgys.iptv.repository;


import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.OrderQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
    public interface SettlementStatisticsRepository extends JpaRepository<AccountSettlement,Object>, JpaSpecificationExecutor<AccountSettlement> {


  /*  @Modifying
    @Query(value = "SELECT o.setStartTime, o.name, o.set_ruleName, o.setEndTime, o.set_type, o.inputTime, o.total_sum FROM AccountSettlement o GROUP BY o.setStartTime ORDER BY o.setStartTime DESC, o.setEndTime DESC")
    List<AccountSettlement> findsettlement();*/
    @Modifying
    @Query(value = "SELECT * FROM account_settlement  GROUP BY DATE_FORMAT(set_startTime, '%Y-%M-%D') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
    List<AccountSettlement> findsettlement();

  @Modifying
  @Query(value = "SELECT * FROM account_settlement where name = ?1 GROUP BY DATE_FORMAT(set_startTime, '%Y-%M-%D') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> findsettlementname(String name);

    @Modifying
    @Query(value = "SELECT * FROM account_settlement WHERE  (set_startTime>=? and  set_endTime<=?) AND name=? GROUP BY DATE_FORMAT(set_startTime, '%Y-%M-%D') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
    List<AccountSettlement> finddatesettlementname(String startTime, String endTime,String name);

  @Modifying
  @Query(value = "SELECT * FROM account_settlement WHERE  set_startTime>=? and  set_endTime<=? GROUP BY DATE_FORMAT(set_startTime, '%Y-%M-%D') ORDER BY set_startTime DESC, set_endTime DESC LIMIT 12", nativeQuery = true)
  List<AccountSettlement> finddatesettlement(String startTime, String endTime);
}



