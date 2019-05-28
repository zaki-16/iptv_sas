package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpSettlementMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CpSettlementMoneyRepository extends JpaRepository<CpSettlementMoney,Object>, JpaSpecificationExecutor<CpSettlementMoney> {

    @Modifying
    @Query(value = "delete from CpSettlementMoney  where masterCode = ?1")
    void deleteByMasterCode(String masterCode);

    List<CpSettlementMoney> findByMasterCode(String masterCdoe);

    @Query(value = "select SUM(settlementMoney) from CpSettlementMoney where masterCode = ?1")
    BigDecimal jsAllmoney(String masterCode);

    @Query(value = "select SUM(settlementMoney) from CpSettlementMoney where masterCode = ?1 and cpcode = ?2")
    BigDecimal jsAllmoneyByMasterCodeAndCpcode(String masterCode,String cpCode);

    @Query(value = "select SUM(settlementMoney) from CpSettlementMoney where masterCode = ?1 and productCode = ?2")
    BigDecimal jsAllmoneyByMasterCodeAndProductCode(String masterCode,String productCode);

    @Query(value = "select SUM(settlementMoney) from CpSettlementMoney where masterCode = ?1 and businessCode = ?2")
    BigDecimal jsAllmoneyByMasterCodeAndBusinessCode(String masterCode,String businessCode);
    BigDecimal jsAllmoney(String masterCdoe);

    @Query(value = "SELECT SUM(settlementMoney) FROM CpSettlementMoney WHERE cpcode=?1")
    BigDecimal sumSettlementMoney(String cpcode);

    /**
     * 获取最近12个月的金额
     * @return
     */
//    @Query(value = "SELECT SUM(settlementMoney) FROM CpSettlementMoney")
    @Query(value = "SELECT SUM(settlementMoney) FROM cp_settlement_money " +
            "WHERE DATE_FORMAT(CONCAT(createTime),'%Y-%M')> " +
            "DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 12 MONTH),'%Y-%M');",nativeQuery = true)
    BigDecimal sumAllSettlementMoney();
}
