package com.hgys.iptv.service.impl;

import com.google.common.collect.Maps;
import com.hgys.iptv.common.Criteria;
import com.hgys.iptv.common.Restrictions;
import com.hgys.iptv.controller.vm.CpSettlementMoneyVM;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.CpSettlementMoney;
import com.hgys.iptv.model.QAccountSettlement;
import com.hgys.iptv.model.dto.CpSettlementMoneyDTO;
import com.hgys.iptv.model.dto.PieVMForBiz;
import com.hgys.iptv.model.dto.PieVMForCp;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AccountSettlementRepository;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import com.hgys.iptv.util.ResultVOUtil;
import com.querydsl.jpa.impl.JPAQuery;
import io.lettuce.core.GeoArgs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName SettleDataOfCpServiceImpl_
 * @Auther: wangz
 * @Date: 2019/5/30 15:46
 * @Description: TODO
 */
@Service
public class SettleDataOfCpServiceImpl_ {
    /**
     * 列表展示字段：业务名称、关联CP、关联产品、结算金额（各账期结算金额之和）。默认取最近12个账期数据。
     * 查询条件：账期（日期区间）、业务名称（下拉选择，多选）。
     */
    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;
    @Autowired
    private AccountSettlementRepository accountSettlementRepository;

    private List<AccountSettlement> getAccountSettlementList(String startTime,String endTime){
        Criteria<AccountSettlement> criteria = new Criteria<>();
        if(StringUtils.isNotBlank(endTime))
            criteria.add(Restrictions.lte("setEndTime",Timestamp.valueOf(endTime)));
        if(StringUtils.isNotBlank(startTime))
            criteria.add(Restrictions.gte("setStartTime",Timestamp.valueOf(startTime)));

        criteria.add(Restrictions.eq("isdelete",0));
        Pageable pageable = PageRequest.of(0, 12, Sort.Direction.DESC, "setStartTime");
        return accountSettlementRepository.findAll(criteria,pageable).getContent();
    }



    /**
     * CpSettlementMoney:
     * @param startTime
     * @param endTime
     */
    public ResultVO getBizSettleDataOfPie(String startTime, String endTime, String codes){
        List<String> codeList=null;
        if(StringUtils.isNotBlank(codes)) {
            codeList = Arrays.asList(StringUtils.split(codes, ","));
        }
        Map<String, PieVMForBiz.Details> map = Maps.newHashMap();
        List<PieVMForBiz> pieVMForBizs = new ArrayList<>();
        // 账期数据 -- 每个账单下有不同业务code 计算每个code的总金额
        List<AccountSettlement> settlementList = getAccountSettlementList(startTime, endTime);
        // 根据
        for(AccountSettlement as : settlementList){
            String masterCode = as.getCode();
            BigDecimal all = cpSettlementMoneyRepository.sumByMasterCode(masterCode);
            all=all==null?BigDecimal.ZERO:all;

            PieVMForBiz pieVMForBiz = new PieVMForBiz();
            pieVMForBiz.setName(as.getName());//账单名
            pieVMForBiz.setSettlementMoney(all);//账单总金额

            // 查询
            Criteria<CpSettlementMoney> criteria = new Criteria<>();
            criteria.add(Restrictions.in("businessCode",codeList,true));
            criteria.add(Restrictions.eq("masterCode",masterCode));
            List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findAll(criteria);

            Set<PieVMForBiz.Details> detailsList = new HashSet<>();
            for(CpSettlementMoney biz : byMasterCode){
                PieVMForBiz.Details details = new PieVMForBiz.Details();
                BigDecimal one = cpSettlementMoneyRepository.sumByBizCode(biz.getBusinessCode(), masterCode);
                one=one==null?BigDecimal.ZERO:one;
                details.setName(biz.getBusinessName());// 业务名称
                details.setSettlementMoney(one);
                BigDecimal ratio = one.divide(all, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
                details.setRatio(Double.toString(ratio.doubleValue()));

                // 关联的cp
                List<String> cpNames = cpSettlementMoneyRepository.findCpNameListByBizCode(masterCode, biz.getBusinessCode());
                details.setCpNames(cpNames);
                // 关联的产品
                List<String> prodNames = cpSettlementMoneyRepository.findProdNameListByBizCode(masterCode, biz.getBusinessCode());
                details.setProdNames(prodNames);
                detailsList.add(details);
            }
            pieVMForBiz.setList(detailsList);
            pieVMForBizs.add(pieVMForBiz);
        }

        return ResultVOUtil.success(pieVMForBizs);
    }



    public ResultVO getCpSettleDataOfPie(String startTime, String endTime, String cpName){
        List<PieVMForCp> pieVMForBizs = new ArrayList<>();
        // 账期数据 -- 每个账单下有不同业务code 计算每个code的总金额
        List<AccountSettlement> settlementList = getAccountSettlementList(startTime, endTime);
        // 根据
        for(AccountSettlement as : settlementList){
            String masterCode = as.getCode();
            BigDecimal all = cpSettlementMoneyRepository.sumByMasterCode(masterCode);
            all=all==null?BigDecimal.ZERO:all;

            PieVMForCp pieVMForBiz = new PieVMForCp();
            pieVMForBiz.setName(as.getName());//账单名
            pieVMForBiz.setSettlementMoney(all);//账单总金额

            // 查询
            Criteria<CpSettlementMoney> criteria = new Criteria<>();
            criteria.add(Restrictions.like("cpname",cpName));
            criteria.add(Restrictions.eq("masterCode",masterCode));
            List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findAll(criteria);

            Set<PieVMForCp.Details> detailsList = new HashSet<>();
            for(CpSettlementMoney biz : byMasterCode){
                PieVMForCp.Details details = new PieVMForCp.Details();
                BigDecimal one = cpSettlementMoneyRepository.sumByCpCode(biz.getCpcode(), masterCode);
                one=one==null?BigDecimal.ZERO:one;
                details.setName(biz.getCpname());// cp名称
                details.setSettlementMoney(one);
                BigDecimal ratio = one.divide(all, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
                details.setRatio(Double.toString(ratio.doubleValue()));

                // 关联的业务
                List<String> bizNames = cpSettlementMoneyRepository.findBizNameListByCpCode(masterCode, biz.getCpcode());
                details.setBizNames(bizNames);
                // 关联的产品
                List<String> prodNames = cpSettlementMoneyRepository.findProdNameListByCpCode(masterCode, biz.getCpcode());
                details.setProdNames(prodNames);
                detailsList.add(details);
            }
            pieVMForBiz.setList(detailsList);
            pieVMForBizs.add(pieVMForBiz);
        }

        return ResultVOUtil.success(pieVMForBizs);
    }
}
