package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.*;
import com.hgys.iptv.model.QBusinessComparisonRelation;
import com.hgys.iptv.model.QCpOrderBusinessComparison;
import com.hgys.iptv.model.QOrderBusinessCp;
import com.hgys.iptv.model.QOrderBusinessWithCp;
import com.hgys.iptv.model.QOrderProduct;
import com.hgys.iptv.model.QSettlementBusiness;
import com.hgys.iptv.model.QSettlementMoney;
import com.hgys.iptv.model.QSettlementOrder;
import com.hgys.iptv.model.QSettlementProductMany;
import com.hgys.iptv.model.QSettlementProductSingle;
import com.hgys.iptv.model.bean.BusinessBiLiQuery;
import com.hgys.iptv.model.bean.SettlementBusinessQuery;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AccountSettlementRepository;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import com.hgys.iptv.repository.OrderCpWithCpRepository;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionFromRepository;
import com.hgys.iptv.service.SettlementService;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.SettleUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SettlementServiceImpl implements SettlementService {

    @Autowired
    private AccountSettlementRepository accountSettlementRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;

    @Autowired
    private OrderCpWithCpRepository orderCpWithCpRepository;

    @Autowired
    private SettlementCombinatorialDimensionFromRepository settlementCombinatorialDimensionFromRepository;
    /**
     * 根据分账结算Id结算
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> settlement(String id) {
        Optional<AccountSettlement> byId = accountSettlementRepository.findById(Integer.parseInt(id));
        if (!byId.isPresent()){
            return ResultVOUtil.error("1","未查询到该分账结算数据");
        }
        //验证是否已经结算(1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回)
        if (byId.get().getStatus() != 1){
            return ResultVOUtil.error("1","该分账结算信息已经结算,不能重复结算!");
        }
        AccountSettlement accountSettlement = byId.get();
        //修改结算状态
        accountSettlement.setStatus(2);
        accountSettlementRepository.saveAndFlush(accountSettlement);
        //查询当前分账结算类型、规则编码(1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算))
        if (1 == accountSettlement.getSet_type()){
            return dealWithOrderSettlement(accountSettlement);
        } else if (2 == accountSettlement.getSet_type()){
            return dealWithBusiness(accountSettlement);
        }else if (4 == accountSettlement.getSet_type()){
            return dealWithCpSettlement(accountSettlement);
        }else if (3 == accountSettlement.getSet_type()){
            return dealWithProduct(accountSettlement);
        }else if (5 == accountSettlement.getSet_type()){
            return dealWithBusinessBiLi(accountSettlement);
        }
        return ResultVOUtil.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> cancel(String id) {
        Optional<AccountSettlement> byId = accountSettlementRepository.findById(Integer.parseInt(id));
        if (!byId.isPresent()){
            return ResultVOUtil.error("1","未查询到该分账结算数据");
        }
        //验证是否已经结算(1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回)
        if (byId.get().getStatus() == 1){
            return ResultVOUtil.error("1","该分账结算信息没有结算,无需撤销!");
        }
        if (byId.get().getStatus() == 3 || byId.get().getStatus() == 4 || byId.get().getStatus() == 5){
            return ResultVOUtil.error("1","进入审核阶段的结算账单不能撤销!");
        }
        try{
            AccountSettlement accountSettlement = byId.get();
            //修改结算状态
            accountSettlement.setStatus(1);
            accountSettlementRepository.saveAndFlush(accountSettlement);
            //删除已经结算的无用信息
            cpSettlementMoneyRepository.deleteByMasterCode(accountSettlement.getCode());
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","系统内部异常!");
        }
        return ResultVOUtil.success();
    }

    /**
     * 结算业务级结算
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> dealWithBusiness(AccountSettlement accountSettlement){
        try{
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

            //计算每个cp的金额，然后再对每个cp进行求和计算
            for (SettlementBusinessQuery query : fetch){
                Long bw = Long.valueOf(query.getBusinessWeight());
                Long cw = Long.valueOf(query.getCpWeight());
                query.setMoney(BigDecimal.valueOf(bw).multiply(BigDecimal.valueOf(cw)).multiply(allMoney).setScale(2));

                CpSettlementMoney money = new CpSettlementMoney();
                money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                money.setMasterCode(accountSettlement.getCode());
                money.setMasterName(accountSettlement.getName());
                money.setCpcode(StringUtils.trimToEmpty(query.getCpCode()));
                money.setCpname(StringUtils.trimToEmpty(query.getCpName()));
                money.setSettlementMoney(query.getMoney());
                money.setBusinessCode(StringUtils.trimToEmpty(query.getBusinessCode()));
                money.setBusinessName(query.getBusinessName());
                cpSettlementMoneyRepository.save(money);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","系统内部错误!");
        }
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
                money.setSettlementMoney(order.getOrderQuantity().divide(allMoney,2, BigDecimal.ROUND_HALF_UP).multiply(order.getOrderMoney()).setScale(2));
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
                    money.setCpcode(StringUtils.trimToEmpty(cp.getCpcode()));
                    money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    //权重
                    BigDecimal  i = new BigDecimal(cp.getWeight()).divide(new BigDecimal(100));
                    money.setSettlementMoney(allMoney.multiply(i).setScale(2));
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
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> dealWithBusinessBiLi(AccountSettlement accountSettlement){
        try{
            //查询规则下所以业务下关联的cp信息
            QBusinessComparisonRelation relation = QBusinessComparisonRelation.businessComparisonRelation;
            QCpOrderBusinessComparison comparison = QCpOrderBusinessComparison.cpOrderBusinessComparison;
            List<BusinessBiLiQuery> fetch = jpaQueryFactory.select(Projections.bean(
                    BusinessBiLiQuery.class,
                    relation.businessCode.as("businessCode"),
                    relation.businessName.as("businessName"),
                    comparison.cp_code.as("cpcode"),
                    comparison.cp_name.as("cpname"),
                    comparison.money.as("money"),
                    comparison.proportion.as("proportion")
            )).from(relation).innerJoin(comparison).on(relation.businessCode.eq(comparison.masterCode))
                    .where(relation.masterCode.eq(accountSettlement.getSet_ruleCode().trim())).fetch();
            //查询业务定比例源数据
            QSettlementBusiness qSettlementBusiness = QSettlementBusiness.settlementBusiness;
            List<SettlementBusiness> business = jpaQueryFactory.selectFrom(qSettlementBusiness).where(qSettlementBusiness.masterCode.eq(accountSettlement.getCode().trim())).fetch();
            if (null != business && !business.isEmpty()){
                if (1 == business.get(0).getType()){
                    Map<String, SettlementBusiness> collect = business.stream().collect(Collectors.toMap(SettlementBusiness::getBusinessCode, SettlementBusiness -> SettlementBusiness));
                    for (BusinessBiLiQuery bi : fetch){
                        CpSettlementMoney money = new CpSettlementMoney();
                        money.setMasterName(accountSettlement.getName());
                        money.setMasterCode(accountSettlement.getCode());
                        money.setCpname(StringUtils.trimToEmpty(bi.getCpname()));
                        money.setCpcode(StringUtils.trimToEmpty(bi.getCpcode()));
                        money.setBusinessCode(StringUtils.trimToEmpty(bi.getBusinessCode()));
                        money.setBusinessName(StringUtils.trimToEmpty(bi.getBusinessName()));
                        money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        //计算cp计算金额，业务收入*cp在该业务下的比例
                        BigDecimal decimal =  BigDecimal.valueOf(bi.getProportion()/100).multiply(collect.get(bi.getBusinessCode()).getBusinessMoney()).setScale(2);
                        money.setSettlementMoney(decimal);
                        cpSettlementMoneyRepository.save(money);
                    }
                }else if (2 == business.get(0).getType()){
                    //计算业务总收入
                    BigDecimal sbMoney = BigDecimal.ZERO;
                    for (SettlementBusiness sb : business){
                        sbMoney = sbMoney.add(sb.getBusinessMoney()).setScale(2);
                    }
                    //计算cp分账总金额
                    BigDecimal cpMoney = BigDecimal.ZERO;
                    for (BusinessBiLiQuery bi : fetch){
                        cpMoney = cpMoney.add(bi.getMoney()).setScale(2);
                    }

                    if (cpMoney.compareTo(sbMoney) == 1){
                        return ResultVOUtil.error("1","CP分账总金额大于业务总收入");
                    }
                    //计算分账结算业务定比例金额结算
                    for (BusinessBiLiQuery bi : fetch){
                        CpSettlementMoney money = new CpSettlementMoney();
                        money.setMasterName(accountSettlement.getName());
                        money.setMasterCode(accountSettlement.getCode());
                        money.setCpname(StringUtils.trimToEmpty(bi.getCpname()));
                        money.setCpcode(StringUtils.trimToEmpty(bi.getCpcode()));
                        money.setBusinessCode(StringUtils.trimToEmpty(bi.getBusinessCode()));
                        money.setBusinessName(StringUtils.trimToEmpty(bi.getBusinessName()));
                        money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        money.setSettlementMoney(bi.getMoney().setScale(2));
                        cpSettlementMoneyRepository.save(money);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","系统内部错误!");
        }
        //查询业务定比例源数据
        return ResultVOUtil.success();
    }

    /**
     * 处理产品级结算
     */
    public ResultVO<?> dealWithProduct(AccountSettlement accountSettlement){
        try{
            //判断是单维度还是多维度结算
            QOrderProduct qOrderProduct = QOrderProduct.orderProduct;
            OrderProduct orderProduct = jpaQueryFactory.selectFrom(qOrderProduct).where(qOrderProduct.code.eq(accountSettlement.getSet_ruleCode().trim())).fetchFirst();
            //维度类型(1:单维度;2:多维度)
            if (1 == orderProduct.getMode()){
                //查询单维度数据源
                QSettlementProductSingle qSettlementProductSingle = QSettlementProductSingle.settlementProductSingle;
                List<SettlementProductSingle> fetch = jpaQueryFactory.selectFrom(qSettlementProductSingle)
                        .where(qSettlementProductSingle.masterCode.eq(accountSettlement.getCode())).fetch();
                //统计产品总数量
                Map<String, BigDecimal> collect = jpaQueryFactory.select(Projections.bean(
                        SettlementProductSingle.class,
                        qSettlementProductSingle.productCode,
                        qSettlementProductSingle.number.sum().as("number")
                )).from(qSettlementProductSingle).where(qSettlementProductSingle.masterCode.eq(accountSettlement.getCode()))
                        .groupBy(qSettlementProductSingle.productCode).fetch()
                        .stream().collect(Collectors.toMap(SettlementProductSingle::getProductCode, SettlementProductSingle::getNumber));

                for (SettlementProductSingle single : fetch){
                    CpSettlementMoney money = new CpSettlementMoney();
                    money.setMasterName(accountSettlement.getName());
                    money.setMasterCode(accountSettlement.getCode());
                    money.setCpname(StringUtils.trimToEmpty(single.getCpname()));
                    money.setCpcode(StringUtils.trimToEmpty(single.getCpcode()));
                    money.setProductCode(StringUtils.trimToEmpty(single.getProductCode()));
                    money.setProductName(StringUtils.trimToEmpty(single.getProductName()));
                    money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    //cp在该产品所占结算金额
                    BigDecimal cpmoney = single.getNumber().divide(collect.get(single.getProductCode()),2, BigDecimal.ROUND_HALF_UP).multiply(single.getSetMoney()).setScale(2);
                    money.setSettlementMoney(cpmoney);
                    cpSettlementMoneyRepository.save(money);
                }
            }else if (2 == orderProduct.getMode()){
                //查询多维度结算源
                QSettlementProductMany qSettlementProductMany = QSettlementProductMany.settlementProductMany;
                List<SettlementProductMany> fetch = jpaQueryFactory.selectFrom(qSettlementProductMany)
                        .where(qSettlementProductMany.masterCode
                                .eq(accountSettlement.getCode())).fetch();
                //统计产品各维度总数量
                Map<String, SettlementProductMany> collect = jpaQueryFactory.select(Projections.bean(
                        SettlementProductMany.class,
                        qSettlementProductMany.productCode,
                        qSettlementProductMany.numberA.sum().as("numberA"),
                        qSettlementProductMany.numberB.sum().as("numberB"),
                        qSettlementProductMany.numberC.sum().as("numberC")
                )).from(qSettlementProductMany).where(qSettlementProductMany.masterCode.eq(accountSettlement.getCode()))
                        .groupBy(qSettlementProductMany.productCode).fetch()
                        .stream().collect(Collectors.toMap(SettlementProductMany::getProductCode, SettlementProductMany -> SettlementProductMany));

                //查询多维度权重
                Map<String, Integer> wights = settlementCombinatorialDimensionFromRepository
                        .findByMasterCode(accountSettlement.getSet_ruleCode())
                        .stream()
                        .collect(Collectors.toMap(SettlementCombinatorialDimensionFrom::getDim_code, SettlementCombinatorialDimensionFrom::getWeight));

                for (SettlementProductMany many : fetch){
                    CpSettlementMoney money = new CpSettlementMoney();
                    money.setMasterName(accountSettlement.getName());
                    money.setMasterCode(accountSettlement.getCode());
                    money.setCpname(StringUtils.trimToEmpty(many.getCpname()));
                    money.setCpcode(StringUtils.trimToEmpty(many.getCpcode()));
                    money.setProductCode(StringUtils.trimToEmpty(many.getProductCode()));
                    money.setProductName(StringUtils.trimToEmpty(many.getProductName()));
                    money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    //cp在该产品下结算金额
                    BigDecimal a = many.getNumberA().divide(collect.get(many.getProductCode()).getNumberA(),2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(wights.get(many.getDimACode()))).setScale(2);
                    BigDecimal b = many.getNumberB().divide(collect.get(many.getProductCode()).getNumberB(),2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(wights.get(many.getDimBCode()))).setScale(2);
                    BigDecimal c = many.getNumberC().divide(collect.get(many.getProductCode()).getNumberC(),2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(wights.get(many.getDimCCode()))).setScale(2);

                    //计算cp在该产品下分账结算金额
                    BigDecimal allmoney = a.add(b).add(c).setScale(2);
                    money.setSettlementMoney(allmoney);
                    cpSettlementMoneyRepository.save(money);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","系统内部错误!");
        }
        return ResultVOUtil.success();
    }
}
