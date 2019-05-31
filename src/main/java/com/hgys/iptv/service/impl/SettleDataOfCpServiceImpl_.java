package com.hgys.iptv.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Maps;
import com.hgys.iptv.common.Criteria;
import com.hgys.iptv.common.Restrictions;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.CpSettlementMoney;
import com.hgys.iptv.model.dto.*;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AccountSettlementRepository;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import com.hgys.iptv.util.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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



    /**-======================================业务 图表 =====================================================*/

    private List<AccountSettlement> getBizAccountSettlementList(String startTime,String endTime){
        Criteria<AccountSettlement> criteria = new Criteria<>();
        if(StringUtils.isNotBlank(endTime))
            criteria.add(Restrictions.lte("setEndTime",Timestamp.valueOf(endTime)));
        if(StringUtils.isNotBlank(startTime))
            criteria.add(Restrictions.gte("setStartTime",Timestamp.valueOf(startTime)));

        criteria.add(Restrictions.eq("isdelete",0))
            .add(Restrictions.in("set_type",Arrays.asList(2,5),true));
        Pageable pageable = PageRequest.of(0, 12, Sort.Direction.DESC, "setStartTime");
        return accountSettlementRepository.findAll(criteria,pageable).getContent();
    }

    /**
     * 业务表格
     *
     * @param startTime
     * @param endTime
     * @param codes
     * @return 业务名称、关联CP、关联产品、结算金额（各账期结算金额之和）
     */
    public Set<ChartVMForBiz> getBizSettleDataOfChart(String startTime, String endTime, String codes){
        Set<ChartVMForBiz> chartVMForBizs = new HashSet<>();
        // 12 个账单的结算总额  == sum where masterCode in ();
        BigDecimal allIncome_12 = BigDecimal.ZERO;// 12 个账单的结算总额

        // 12 个账单的业务1 的结算总额 == where businessCode='' and masterCode in (); --每个账单是 b
        // 关联cp == findCpNameListByBizCode  set接收
        // 关联产品 == findProdNameListByBizCode set接收

        LinkedList<CpSettlementMoney> rawList = new LinkedList<>();
        // 12个 账单
        List<AccountSettlement> allAcs = getBizAccountSettlementList(startTime,endTime);
        for(AccountSettlement as:allAcs){
            String masterCode = as.getCode();
            BigDecimal allIncome_1 = cpSettlementMoneyRepository.sumByMasterCode(masterCode);
            allIncome_1=allIncome_1==null?BigDecimal.ZERO:allIncome_1;
            allIncome_12 = allIncome_12.add(allIncome_1);

            List<String> codeList=null;
            if(StringUtils.isNotBlank(codes)) {
                codeList = Arrays.asList(StringUtils.split(codes, ","));
            }
            // 查询
            Criteria<CpSettlementMoney> criteria = new Criteria<>();
            criteria.add(Restrictions.in("businessCode",codeList,true));
            criteria.add(Restrictions.eq("masterCode",masterCode));
            List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findAll(criteria);
            rawList.addAll(byMasterCode);
        }

        Map<String, ChartVMForBiz> countBizMoneyMap = countBizMoney(rawList);

        for(Map.Entry<String, ChartVMForBiz> entry:countBizMoneyMap.entrySet()){
            ChartVMForBiz chartVM = entry.getValue();
            BigDecimal oneIncome = chartVM.getSettlementMoney();
            BigDecimal ratio = oneIncome.divide(allIncome_12, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
            chartVM.setRatio(Double.toString(ratio.doubleValue()));

            chartVMForBizs.add(chartVM);
        }

        // 获取12个账单下的所有结算对象
        //Set<CpSettlementMoney> cps = new HashSet<>(rawList);//  去重后的所有 待结算的明细数据
        // 按 业务code分类--汇总金额==ChartVMForBiz
//        if(cps.size()>0){
//            for(CpSettlementMoney cp :cps){
//                ChartVMForBiz chartVMForBiz = new ChartVMForBiz();
//                chartVMForBiz.setName(cp.getBusinessName());// 业务名称
//                // 12 个账单的当前业务的结算总额
//                BigDecimal allIncome_12_biz = cpSettlementMoneyRepository.sumByBizCode(cp.getBusinessCode(), cp.getMasterCode());
//                allIncome_12_biz=allIncome_12_biz==null?BigDecimal.ZERO:allIncome_12_biz;
//                chartVMForBiz.setSettlementMoney(allIncome_12_biz);
//
//                BigDecimal ratio = allIncome_12_biz.divide(allIncome_12, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
//                chartVMForBiz.setRatio(Double.toString(ratio.doubleValue()));
//
//                List<String> cpNameList = cpSettlementMoneyRepository.findCpNameListByBizCode(cp.getMasterCode(), cp.getBusinessCode());
//                List<String> prodNameList = cpSettlementMoneyRepository.findProdNameListByBizCode(cp.getMasterCode(), cp.getBusinessCode());
//
//                chartVMForBiz.setCpNames(new HashSet<>(cpNameList));
//                chartVMForBiz.setProdNames(new HashSet<>(prodNameList));
//
//                chartVMForBizs.add(chartVMForBiz);
//            }
//        }
        // 直接找该 masterCode 下的数据有哪些 业务，放进 set--》对set中数据 按 masterCode+业务code查总额
        return chartVMForBizs;
    }
    /**
     * 业务饼状图
     *
     * CpSettlementMoney:
     * @param startTime
     * @param endTime
     */
    public ResultVO getBizSettleDataOfPie(String startTime, String endTime, String codes){
        List<String> codeList=null;
        if(StringUtils.isNotBlank(codes)) {
            codeList = Arrays.asList(StringUtils.split(codes, ","));
        }
        Set<PieVMForBiz> pieVMForBizs = new HashSet<>();
        // 账期数据 -- 每个账单下有不同业务code 计算每个code的总金额
        List<AccountSettlement> settlementList = getBizAccountSettlementList(startTime, endTime);
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
                details.setBizName(biz.getBusinessName());// 业务名称
                details.setSettlementMoney(one);
                BigDecimal ratio = one.divide(all, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
                details.setRatio(Double.toString(ratio.doubleValue()));

                // 关联的cp
                List<String> cpNames = cpSettlementMoneyRepository.findCpNameListByBizCode(masterCode, biz.getBusinessCode());
                details.setCpNames(new HashSet<>(cpNames));
                // 关联的产品
                List<String> prodNames = cpSettlementMoneyRepository.findProdNameListByBizCode(masterCode, biz.getBusinessCode());
                details.setProdNames(new HashSet<>(prodNames));
                detailsList.add(details);
            }
            pieVMForBiz.setList(detailsList);
            pieVMForBizs.add(pieVMForBiz);
        }

        return ResultVOUtil.success(pieVMForBizs);
    }


    private Map<String, ChartVMForBiz> countBizMoney(List<CpSettlementMoney> rawList){
        Map<String, ChartVMForBiz> map = Maps.newHashMap();
        for(CpSettlementMoney cp:rawList) {
            ChartVMForBiz chartVM = new ChartVMForBiz();
            if (cp.getSettlementMoney() == null)
                cp.setSettlementMoney(BigDecimal.ZERO);

            BeanUtils.copyProperties(cp, chartVM);
            chartVM.setName(cp.getProductName());

            List<String> cpNameList = cpSettlementMoneyRepository.findCpNameListByBizCode(cp.getMasterCode(), cp.getBusinessCode());
            List<String> prodNameList = cpSettlementMoneyRepository.findProdNameListByBizCode(cp.getMasterCode(), cp.getBusinessCode());

            if (cpNameList.size() > 0)
                chartVM.setCpNames(new HashSet<>(cpNameList));
            if (prodNameList.size() > 0)
                chartVM.setProdNames(new HashSet<>(prodNameList));

            if (map.get(cp.getBusinessCode()) != null) {
                // 同名cp金额汇总
                BigDecimal sumMoney = map.get(cp.getBusinessCode()).getSettlementMoney();
                sumMoney = sumMoney.add(chartVM.getSettlementMoney());
                chartVM.setSettlementMoney(sumMoney);
            }
            map.put(cp.getBusinessCode(), chartVM);
        }

        return map;
    }

    /**-======================================cp 图表 =====================================================*/

    private List<AccountSettlement> getCpAccountSettlementList(String startTime,String endTime){
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
     * cp表格
     * @param startTime
     * @param endTime
     * @param cpName
     * @return
     */
    public Set<ChartVMForCp> getCpSettleDataOfChart(String startTime, String endTime, String cpName){
        Set<ChartVMForCp> chartVMForCps = new HashSet<>();
        // 12 个账单的结算总额  == sum where masterCode in ();
        BigDecimal allIncome_12 = BigDecimal.ZERO;// 12 个账单的结算总额

        LinkedList<CpSettlementMoney> rawList = new LinkedList<>();
        // 12个 账单
        List<AccountSettlement> allAcs = getCpAccountSettlementList(startTime,endTime);
        for(AccountSettlement as:allAcs){
            String masterCode = as.getCode();
            BigDecimal allIncome_1 = cpSettlementMoneyRepository.sumByMasterCode(masterCode);
            allIncome_1=allIncome_1==null?BigDecimal.ZERO:allIncome_1;
            allIncome_12 = allIncome_12.add(allIncome_1);

            // 查询
            Criteria<CpSettlementMoney> criteria = new Criteria<>();
            criteria.add(Restrictions.like("cpname",cpName));
            criteria.add(Restrictions.eq("masterCode",masterCode));
            List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findAll(criteria);
            rawList.addAll(byMasterCode);
        }

        // 获取12个账单下的所有结算对象
//        Set<CpSettlementMoney> cps = new HashSet<>(rawList);//  去重后的所有 待结算的明细数据
        Map<String, ChartVMForCp> countCpMoneyMap = countCpMoney(rawList);

        for(Map.Entry<String, ChartVMForCp> entry:countCpMoneyMap.entrySet()){
            ChartVMForCp chartVMForCp = entry.getValue();
            BigDecimal oneCpIncome = chartVMForCp.getSettlementMoney();
            BigDecimal ratio = oneCpIncome.divide(allIncome_12, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
            chartVMForCp.setRatio(Double.toString(ratio.doubleValue()));

            chartVMForCps.add(chartVMForCp);
        }

        // 按 业务code分类--汇总金额==ChartVMForBiz
//        if(cps.size()>0){
//            for(CpSettlementMoney cp :cps){
//                ChartVMForCp chartVMForCp = new ChartVMForCp();
//                chartVMForCp.setName(cp.getCpname());
//                // 12 个账单的当前cp的结算总额
//                BigDecimal allIncome_12_cp = cpSettlementMoneyRepository.sumByCpCode(cp.getCpcode(), cp.getMasterCode());
//                allIncome_12_cp=allIncome_12_cp==null?BigDecimal.ZERO:allIncome_12_cp;
//                chartVMForCp.setSettlementMoney(allIncome_12_cp);
//
//                BigDecimal ratio = allIncome_12_cp.divide(allIncome_12, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
//                chartVMForCp.setRatio(Double.toString(ratio.doubleValue()));
//
//                List<String> bizNameList = cpSettlementMoneyRepository.findBizNameListByCpCode(cp.getMasterCode(), cp.getCpcode());
//                List<String> prodNameList = cpSettlementMoneyRepository.findProdNameListByCpCode(cp.getMasterCode(), cp.getCpcode());
//
//                chartVMForCp.setBizNames(new HashSet<>(bizNameList));
//                chartVMForCp.setProdNames(new HashSet<>(prodNameList));
//
//                chartVMForCps.add(chartVMForCp);
//            }
//        }
        // 直接找该 masterCode 下的数据有哪些 业务，放进 set--》对set中数据 按 masterCode+业务code查总额
        return chartVMForCps;
    }

    /**
     * 计算 给定账期内的各个cp的占比、总金额详情
     * @param rawList
     * @return
     */
    private Map<String, ChartVMForCp> countCpMoney(List<CpSettlementMoney> rawList){
        Map<String, ChartVMForCp> map = Maps.newHashMap();
//        BigDecimal sumAll = BigDecimal.ZERO;

        for(CpSettlementMoney cp:rawList) {
            ChartVMForCp chartVMForCp = new ChartVMForCp();
            if (cp.getSettlementMoney() == null)
                cp.setSettlementMoney(BigDecimal.ZERO);
            // 统计总金额
//            sumAll = sumAll.add(cp.getSettlementMoney());
            BeanUtils.copyProperties(cp, chartVMForCp);
            chartVMForCp.setName(cp.getCpname());
            List<String> productNameList = cpSettlementMoneyRepository.findProdNameListByCpCode(cp.getMasterCode(), cp.getCpcode());
            if (productNameList.size() > 0)
                chartVMForCp.setProdNames(new HashSet<>(productNameList));
            List<String> businessNameList = cpSettlementMoneyRepository.findBizNameListByCpCode(cp.getMasterCode(), cp.getCpcode());
            if (businessNameList.size() > 0)
                chartVMForCp.setBizNames(new HashSet<>(businessNameList));
            if (map.get(cp.getCpcode()) != null) {
                // 同名cp金额汇总
                BigDecimal sumMoney = map.get(cp.getCpcode()).getSettlementMoney();
                sumMoney = sumMoney.add(chartVMForCp.getSettlementMoney());
                chartVMForCp.setSettlementMoney(sumMoney);
            }
            map.put(cp.getCpcode(), chartVMForCp);
        }

//        for(Map.Entry<String, ChartVMForCp> entry:map.entrySet()){
//            ChartVMForCp vm = entry.getValue();
//            BigDecimal one = vm.getSettlementMoney();
//            BigDecimal ratio = one.divide(sumAll, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
//            vm.setRatio(Double.toString(ratio.doubleValue()));
//        }
        return map;
    }


    /**
     * 饼状图
     *
     * @param startTime
     * @param endTime
     * @param cpName
     * @return
     */
    public ResultVO getCpSettleDataOfPie(String startTime, String endTime, String cpName){
        Set<PieVMForCp> pieVMForCps = new HashSet<>();

        // 账期数据 -- 每个账单下有不同业务code 计算每个code的总金额
        List<AccountSettlement> settlementList = getCpAccountSettlementList(startTime, endTime);

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
                details.setCpName(biz.getCpname());// cp名称
                details.setSettlementMoney(one);
                BigDecimal ratio = one.divide(all, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
                details.setRatio(Double.toString(ratio.doubleValue()));

                // 关联的业务
                List<String> bizNames = cpSettlementMoneyRepository.findBizNameListByCpCode(masterCode, biz.getCpcode());
                details.setBizNames(new HashSet<>(bizNames));
                // 关联的产品
                List<String> prodNames = cpSettlementMoneyRepository.findProdNameListByCpCode(masterCode, biz.getCpcode());
                details.setProdNames(new HashSet<>(prodNames));
                detailsList.add(details);
            }

            pieVMForBiz.setList(detailsList);

            ArrayList<PieVMForCp.Details> sortList = new ArrayList<>(detailsList);
            // 处理 top5+1
            listDesc(sortList);
            List<PieVMForCp.TopSixListVM> topSix = getTopSix(sortList, all);
            pieVMForBiz.setTopSixList(topSix);

            pieVMForCps.add(pieVMForBiz);
        }


        return ResultVOUtil.success(pieVMForCps);
    }

    /**
     *
     * @param list
     * @param allIncome
     * @return
     */
    private List<PieVMForCp.TopSixListVM> getTopSix(List<PieVMForCp.Details> list,BigDecimal allIncome){
        LinkedList<PieVMForCp.TopSixListVM> topSixListVMS = CollUtil.newLinkedList();
        int size = list.size();
        if(size<=5 && size>0){
            for(int i=0;i<size;i++){
                // 将已排好序的 cpSettlementMoneyVM 复制到 topSixListVM
                PieVMForCp.TopSixListVM topSixListVM = new PieVMForCp.TopSixListVM();
                BeanUtils.copyProperties(list.get(i),topSixListVM);
                topSixListVM.setRank(i+1);
                topSixListVMS.add(topSixListVM);
            }
        }else if(size>5){//>5 时取前5 +1
            double other= 0;
            BigDecimal grossIncome = BigDecimal.ZERO;

            for(int i=0;i<6;i++){
                // 将已排好序的 cpSettlementMoneyVM 复制到 topSixListVM
                PieVMForCp.TopSixListVM topSixListVM = new PieVMForCp.TopSixListVM();
                BeanUtils.copyProperties(list.get(i),topSixListVM);
                topSixListVM.setRank(i+1);
                /**
                 *                 对第6个需要特殊计算：
                 *                 rank==6
                 *                 cpname ==其他
                 *                 grossIncome==allSettlementMoney-sum(grossIncome)
                 *                 ratio==1-sum(ratio)
                 */
                if(i==5){
                    topSixListVM.setCpName("其他");
                    topSixListVM.setSettlementMoney(allIncome.subtract(grossIncome));
                    topSixListVM.setRatio(Double.toString(100-other));
                }
                other+=Double.valueOf(topSixListVM.getRatio());// i==4即第5名时，需要对第6名计算剩余ratio
                grossIncome=grossIncome.add(topSixListVM.getSettlementMoney());
                topSixListVMS.add(topSixListVM);
            }
        }
        return topSixListVMS;
    }

    /**
     * 按金额排序
     * @param list
     */
    private void listDesc(List<PieVMForCp.Details> list){
        //对history进行排序 升序排序
        Collections.sort(list, (o1,o2)-> {
//            Comparator<CpSettlementMoneyVM> comparing = Comparator.comparing(CpSettlementMoneyVM::getRatio);
            return o2.getSettlementMoney().compareTo(o1.getSettlementMoney());
        });
    }

    //=================================================产品图表==================================================

    private List<AccountSettlement> getProdAccountSettlementList(String startTime,String endTime){
        Criteria<AccountSettlement> criteria = new Criteria<>();
        if(StringUtils.isNotBlank(endTime))
            criteria.add(Restrictions.lte("setEndTime",Timestamp.valueOf(endTime)));
        if(StringUtils.isNotBlank(startTime))
            criteria.add(Restrictions.gte("setStartTime",Timestamp.valueOf(startTime)));

        criteria.add(Restrictions.eq("isdelete",0))
                .add(Restrictions.eq("set_type",3));
        Pageable pageable = PageRequest.of(0, 12, Sort.Direction.DESC, "setStartTime");
        return accountSettlementRepository.findAll(criteria,pageable).getContent();
    }

    public Set<ChartVMForProd> getProdSettleDataOfChart(String startTime, String endTime, String codes){
        Set<ChartVMForProd> chartVMForProds = new HashSet<>();
        // 12 个账单的结算总额  == sum where masterCode in ();
        BigDecimal allIncome_12 = BigDecimal.ZERO;// 12 个账单的结算总额

        LinkedList<CpSettlementMoney> rawList = new LinkedList<>();
        // 12个 账单
        List<AccountSettlement> allAcs = getProdAccountSettlementList(startTime,endTime);
        for(AccountSettlement as:allAcs){
            String masterCode = as.getCode();
            BigDecimal allIncome_1 = cpSettlementMoneyRepository.sumByMasterCode(masterCode);
            allIncome_1=allIncome_1==null?BigDecimal.ZERO:allIncome_1;
            allIncome_12 = allIncome_12.add(allIncome_1);

            List<String> codeList=null;
            if(StringUtils.isNotBlank(codes)) {
                codeList = Arrays.asList(StringUtils.split(codes, ","));
            }
            // 查询
            Criteria<CpSettlementMoney> criteria = new Criteria<>();
            criteria.add(Restrictions.in("productCode",codeList,true));
            criteria.add(Restrictions.eq("masterCode",masterCode));
            List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findAll(criteria);
            rawList.addAll(byMasterCode);
        }

        Map<String, ChartVMForProd> countProdMoneyMap = countProdMoney(rawList);

        for(Map.Entry<String, ChartVMForProd> entry:countProdMoneyMap.entrySet()){
            ChartVMForProd chartVM = entry.getValue();
            BigDecimal oneIncome = chartVM.getSettlementMoney();
            BigDecimal ratio = oneIncome.divide(allIncome_12, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
            chartVM.setRatio(Double.toString(ratio.doubleValue()));

            chartVMForProds.add(chartVM);
        }

//        // 获取12个账单下的所有结算对象
//        Set<CpSettlementMoney> cps = new HashSet<>(rawList);//  去重后的所有 待结算的明细数据
//
//        // 按 业务code分类--汇总金额==ChartVMForBiz
//        if(cps.size()>0){
//            for(CpSettlementMoney cp :cps){
//                ChartVMForProd chartVMForProd = new ChartVMForProd();
//                chartVMForProd.setName(cp.getProductName());// 业务名称
//                // 12 个账单的当前业务的结算总额
//                BigDecimal allIncome_12_biz = cpSettlementMoneyRepository.sumByProdCode(cp.getProductCode(), cp.getMasterCode());
//                allIncome_12_biz=allIncome_12_biz==null?BigDecimal.ZERO:allIncome_12_biz;
//                chartVMForProd.setSettlementMoney(allIncome_12_biz);
//
//                BigDecimal ratio = allIncome_12_biz.divide(allIncome_12, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
//                chartVMForProd.setRatio(Double.toString(ratio.doubleValue()));
//
//                List<String> cpNameList = cpSettlementMoneyRepository.findCpNameListByProdCode(cp.getMasterCode(), cp.getProductCode());
//                List<String> bizNameList = cpSettlementMoneyRepository.findBizNameListByProdCode(cp.getMasterCode(), cp.getProductCode());
//
//                chartVMForProd.setCpNames(new HashSet<>(cpNameList));
//                chartVMForProd.setBizNames(new HashSet<>(bizNameList));
//
//                chartVMForProds.add(chartVMForProd);
//            }
//        }
        // 直接找该 masterCode 下的数据有哪些 业务，放进 set--》对set中数据 按 masterCode+业务code查总额
        return chartVMForProds;
    }



    private Map<String, ChartVMForProd> countProdMoney(List<CpSettlementMoney> rawList){
        Map<String, ChartVMForProd> map = Maps.newHashMap();
        for(CpSettlementMoney cp:rawList) {
            ChartVMForProd chartVM = new ChartVMForProd();
            if (cp.getSettlementMoney() == null)
                cp.setSettlementMoney(BigDecimal.ZERO);

            BeanUtils.copyProperties(cp, chartVM);
            chartVM.setName(cp.getProductName());

            List<String> cpNameList = cpSettlementMoneyRepository.findCpNameListByProdCode(cp.getMasterCode(), cp.getProductCode());
            List<String> bizNameList = cpSettlementMoneyRepository.findBizNameListByProdCode(cp.getMasterCode(), cp.getProductCode());

            if (cpNameList.size() > 0)
                chartVM.setCpNames(new HashSet<>(cpNameList));
            if (bizNameList.size() > 0)
                chartVM.setBizNames(new HashSet<>(bizNameList));

            if (map.get(cp.getProductCode()) != null) {
                // 同名cp金额汇总
                BigDecimal sumMoney = map.get(cp.getProductCode()).getSettlementMoney();
                sumMoney = sumMoney.add(chartVM.getSettlementMoney());
                chartVM.setSettlementMoney(sumMoney);
            }
            map.put(cp.getProductCode(), chartVM);
        }

        return map;
    }

}
