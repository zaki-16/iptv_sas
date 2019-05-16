package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.*;
import com.hgys.iptv.model.QOrderBusinessCp;
import com.hgys.iptv.model.bean.SettlementBusinessQuery;
import com.hgys.iptv.model.qmodel.QOrderBusinessWithCp;
import com.hgys.iptv.model.qmodel.QSettlementMoney;
import com.hgys.iptv.model.qmodel.QSettlementOrder;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.SettlementService;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.SettleUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class SettlementServiceImpl implements SettlementService {

    @Autowired
    private AccountSettlementRepository accountSettlementRepository;

    @Autowired
    private SmallOrderCpRepository smallOrderCpRepository;

    @Autowired
    private OrderBuinessWithCpRepository orderBuinessWithCpRepository;

    @Autowired
    private OrderQuantityWithCpRepository orderQuantityWithCpRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;

    @Autowired
    private OrderCpWithCpRepository orderCpWithCpRepository;
    /**
     * 根据分账结算Id结算
     * @param id
     * @return
     */
    @Override
    public ResultVO<?> settlement(String id) {
        Optional<AccountSettlement> byId = accountSettlementRepository.findById(Integer.parseInt(id));
        if (!byId.isPresent()){
            return ResultVOUtil.error("1","未查询到该分账结算数据");
        }
        AccountSettlement accountSettlement = byId.get();
        //查询当前分账结算类型、规则编码(1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算))
        if (1 == accountSettlement.getSet_type()){
            return dealWithOrderSettlement(accountSettlement);
        } else if (2 == accountSettlement.getSet_type()){
            return dealWithBusiness(accountSettlement);
        }else if (4 == accountSettlement.getSet_type()){
            return dealWithCpSettlement(accountSettlement);
        }
        return ResultVOUtil.success();
    }

    /**
     * 结算业务级结算
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> dealWithBusiness(AccountSettlement accountSettlement){
        QOrderBusinessWithCp qOrderBusinessWithCp = QOrderBusinessWithCp.orderBusinessWithCp;
        QOrderBusinessCp cp = QOrderBusinessCp.orderBusinessCp;
        //查询该业务级下所有关联的业务然后查询业务下关联的cp
        List<SettlementBusinessQuery> fetch = jpaQueryFactory.select(Projections.bean(
                SettlementBusinessQuery.class,
                qOrderBusinessWithCp.bucode.as("businessCode"),
                qOrderBusinessWithCp.buname.as("businessName"),
                qOrderBusinessWithCp.weight.as("businessWeight"),
                cp.cpcode.as("cpCode"),
                cp.cpname.as("cpName"),
                cp.weight.as("cpWeight")
        )).from(qOrderBusinessWithCp)
                .innerJoin(cp).on(qOrderBusinessWithCp.bucode.eq(cp.bucode))
                .where(qOrderBusinessWithCp.obcode.eq(accountSettlement.getSet_ruleCode().trim())).fetch();
        //查询业务级结算总金额
        QSettlementMoney qSettlementMoney = QSettlementMoney.settlementMoney;
        SettlementMoney settlementMoney = jpaQueryFactory.selectFrom(qSettlementMoney).where(qSettlementMoney.masterCode.eq(accountSettlement.getCode().trim())).fetchFirst();
        BigDecimal allMoney = settlementMoney.getMoney();

        return ResultVOUtil.success();
    }

    /**
     * 处理订购量结算
     * @param accountSettlement
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> dealWithOrderSettlement(AccountSettlement accountSettlement){
        try{
            //查询订购量结算数据源并完成结算
            QSettlementOrder qSettlementOrder = QSettlementOrder.settlementOrder;
            List<SettlementOrder> fetch = jpaQueryFactory.selectFrom(qSettlementOrder).where(qSettlementOrder.masterCode.eq(accountSettlement.getCode().trim()))
                    .fetch();
            BigDecimal allMoney = BigDecimal.ZERO;
            for (SettlementOrder order : fetch){
                allMoney = SettleUtil.add(allMoney,order.getOrderMoney());
            }

            for (SettlementOrder order : fetch){
                CpSettlementMoney money = new CpSettlementMoney();
                money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                money.setMasterCode(accountSettlement.getCode());
                money.setMasterName(accountSettlement.getName());
                money.setCpcode(StringUtils.trimToEmpty(order.getCpcode()));
                money.setCpname(StringUtils.trimToEmpty(order.getCpname()));
                money.setSettlementMoney(order.getOrderQuantity().divide(allMoney).multiply(order.getOrderMoney()).setScale(2));
                cpSettlementMoneyRepository.save(money);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","系统内部错误!");
        }
        return ResultVOUtil.success();
    }

    /**
     * 处理cp定比列结算
     * @param accountSettlement
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> dealWithCpSettlement(AccountSettlement accountSettlement){
        try{
            //查询cp定比例结算总收入
            QSettlementMoney qSettlementMoney = QSettlementMoney.settlementMoney;
            SettlementMoney settlementMoney = jpaQueryFactory.selectFrom(qSettlementMoney).where(qSettlementMoney.masterCode.eq(accountSettlement.getCode())).fetchFirst();

            //查询所关联的cp信息
            List<OrderCpWithCp> byMasterCode = orderCpWithCpRepository.findByMasterCode(accountSettlement.getSet_ruleCode().trim());
            //计算cp分账总金额
            BigDecimal a = BigDecimal.ZERO;
            for (OrderCpWithCp cp : byMasterCode){
                a = a.add(BigDecimal.valueOf(cp.getMoney()));
            }
            //1:比列结算；2:金额结算
            if (1 == settlementMoney.getType()){
                BigDecimal allMoney = settlementMoney.getMoney();
                for (OrderCpWithCp cp : byMasterCode){
                    CpSettlementMoney money = new CpSettlementMoney();
                    money.setMasterName(accountSettlement.getName());
                    money.setMasterCode(accountSettlement.getCode());
                    money.setCpname(StringUtils.trimToEmpty(cp.getCpname()));
                    money.setCpcode(StringUtils.trimToEmpty(cp.getCode()));
                    money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    //权重
                    Integer  i = Integer.parseInt(cp.getWeight()) / 100;
                    money.setSettlementMoney(allMoney.multiply(BigDecimal.valueOf(i)).setScale(2));
                    cpSettlementMoneyRepository.save(money);
                }
            }else if (2 == settlementMoney.getType()){
                if (a.compareTo(settlementMoney.getMoney()) == 1){
                    return ResultVOUtil.error("1","CP分账总金额大于总收入!");
                }else {
                    for (OrderCpWithCp cp : byMasterCode){
                        CpSettlementMoney money = new CpSettlementMoney();
                        money.setMasterName(accountSettlement.getName());
                        money.setMasterCode(accountSettlement.getCode());
                        money.setCpname(StringUtils.trimToEmpty(cp.getCpname()));
                        money.setCpcode(StringUtils.trimToEmpty(cp.getCode()));
                        money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        money.setSettlementMoney(BigDecimal.valueOf(cp.getMoney()).setScale(2));
                        cpSettlementMoneyRepository.save(money);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","系统内部错误!");
        }
        return ResultVOUtil.success();
    }

    /**
     * 处理业务定比列结算
     */
    public ResultVO<?> dealWithBusinessBiLi(AccountSettlement accountSettlement){
        return ResultVOUtil.success();
    }

    /**
     * 处理产品级结算
     */
    public ResultVO<?> dealWithProduct(AccountSettlement accountSettlement){
        return ResultVOUtil.success();
    }
}
