package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpSettlementMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CpSettlementMoneyRepository extends JpaRepository<CpSettlementMoney,Object>, JpaSpecificationExecutor<CpSettlementMoney> {

    @Modifying
    @Query(value = "delete from CpSettlementMoney  where masterCode = ?1")
    void deleteByMasterCode(String masterCode);

    List<CpSettlementMoney> findByMasterCode(String masterCode);

    @Query(value = "select SUM(settlementMoney) from CpSettlementMoney where masterCode = ?1")
    BigDecimal jsAllmoney(String masterCode);

    @Query(value = "select SUM(settlementMoney) from CpSettlementMoney where masterCode = ?1 and cpcode = ?2")
    BigDecimal jsAllmoneyByMasterCodeAndCpcode(String masterCode,String cpCode);

    @Query(value = "select SUM(settlementMoney) from CpSettlementMoney where masterCode = ?1 and productCode = ?2")
    BigDecimal jsAllmoneyByMasterCodeAndProductCode(String masterCode,String productCode);

    @Query(value = "select SUM(settlementMoney) from CpSettlementMoney where masterCode = ?1 and businessCode = ?2")
    BigDecimal jsAllmoneyByMasterCodeAndBusinessCode(String masterCode,String businessCode);


    /**
     *
     * @param cpcode
     * @return
     */
    @Query(value = "SELECT SUM(settlementMoney) FROM CpSettlementMoney WHERE cpcode=?1")
    BigDecimal sumSettleMoneyByCpCode(String cpcode);

    @Query(value = "SELECT SUM(settlementMoney) FROM CpSettlementMoney WHERE businessCode=?1")
    BigDecimal sumSettleMoneyByBizCode(String businessCode);

    @Query(value = "SELECT SUM(settlementMoney) FROM CpSettlementMoney WHERE productCode=?1")
    BigDecimal sumSettleMoneyByProdCode(String productCode);

    /**
     * 获取最近12个月的金额 AND cpcode=
     * @return
     */
    @Query(value = "SELECT SUM(settlementMoney) FROM cp_settlement_money " +
            "WHERE DATE_FORMAT(CONCAT(createTime),'%Y-%M')> " +
            "DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL :recent MONTH),'%Y-%M');",nativeQuery = true)
    BigDecimal sumAllSettlementMoney(
            @Param("recent") int recent
    );

    @Query(value = "SELECT SUM(settlementMoney) FROM cp_settlement_money " +
            "WHERE DATE_FORMAT(CONCAT(createTime),'%Y-%M')> " +
            "DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL :recent MONTH),'%Y-%M') " +
            "AND cpcode=:cpcode",nativeQuery = true)
    BigDecimal sumSettleMoneyByCpCode(
            @Param("recent") int recent,
            @Param("cpcode") String cpcode
    );

    @Query(value = "SELECT SUM(settlementMoney) FROM cp_settlement_money " +
            "WHERE DATE_FORMAT(CONCAT(createTime),'%Y-%M')> " +
            "DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL :recent MONTH),'%Y-%M') " +
            "AND businessCode=:businessCode",nativeQuery = true)
    BigDecimal sumSettleMoneyByBizCode(
            @Param("recent") int recent,
            @Param("businessCode") String businessCode
    );

    @Query(value = "SELECT SUM(settlementMoney) FROM cp_settlement_money " +
            "WHERE DATE_FORMAT(CONCAT(createTime),'%Y-%M')> " +
            "DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL :recent MONTH),'%Y-%M') " +
            "AND productCode=:productCode",nativeQuery = true)
    BigDecimal sumSettleMoneyByProdCode(
            @Param("recent") int recent,
            @Param("productCode") String productCode
    );





    /**
     * 通过结算组合维度编码查询
     * @param Code
     * @return
     */
    @Query(value = "SELECT SUM(settlementMoney) FROM CpSettlementMoney WHERE masterCode=?1")
    BigDecimal findByMastermoney(String Code);


    /**
     * 按 cpcode、masterCode
     * 查询 某个账期内的某个cp的结算总额
     * SELECT SUM(settlementMoney)  FROM cp_settlement_money WHERE cpcode='CP2019052311193203512' AND masterCode='FZ2019052712112319925'
     */

    @Query(value = "SELECT SUM(settlementMoney) FROM CpSettlementMoney WHERE cpcode=?1 and masterCode=?1")
    BigDecimal sumByCpCode(String cpcode,String masterCode);

    @Query(value = "SELECT SUM(settlementMoney) FROM CpSettlementMoney WHERE businessCode=?1 and masterCode=?1")
    BigDecimal sumByBizCode(String businessCode,String masterCode);

    @Query(value = "SELECT SUM(settlementMoney) FROM CpSettlementMoney WHERE productCode=?1 and masterCode=?1")
    BigDecimal sumByProdCode(String productCode,String masterCode);

    /**
     * 获取一个账期的总金额
     *
     * @param masterCode
     * @return
     */
    @Query(value = "SELECT SUM(settlementMoney) FROM CpSettlementMoney WHERE masterCode=?1")
    BigDecimal sumByMasterCode(String masterCode);
}
