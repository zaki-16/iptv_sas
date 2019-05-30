package com.hgys.iptv.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Maps;
import com.hgys.iptv.common.Criteria;
import com.hgys.iptv.common.Restrictions;
import com.hgys.iptv.controller.vm.CpSettleStatisticsVM;
import com.hgys.iptv.controller.vm.CpSettlementMoneyVM;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.CpSettlementMoney;
import com.hgys.iptv.model.QAccountSettlement;
import com.hgys.iptv.model.dto.CpSettlementMoneyDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import com.hgys.iptv.service.SettleDataOfCpService;
import com.hgys.iptv.util.ResultVOUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName SettleDataOfCpServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/28 16:11
 * @Description: TODO
 */
@Service
public class SettleDataOfCpServiceImpl implements SettleDataOfCpService {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;

    /**
     * 获取最近12个账期数据
     *
     * @param cpSettlementMoneyDTO
     * @return
     */
    private List<AccountSettlement> getAccountSettlementList(CpSettlementMoneyDTO cpSettlementMoneyDTO){
        QAccountSettlement accountSettlement = QAccountSettlement.accountSettlement;

        //查询12个账期
        JPAQuery<AccountSettlement> acc = jpaQueryFactory.selectFrom(accountSettlement);
        String startTime = cpSettlementMoneyDTO.getSetStartTime();
        if (StringUtils.isNotBlank(startTime)){
            acc.where(accountSettlement.setStartTime.goe(Timestamp.valueOf(startTime)));
        }
        String endTime = cpSettlementMoneyDTO.getSetEndTime();
        if (StringUtils.isNotBlank(endTime)){
            acc.where(accountSettlement.setEndTime.loe(Timestamp.valueOf(endTime)));
        }

        List<AccountSettlement> fetch = acc.where(accountSettlement.status.ne(1))
                .where(accountSettlement.set_type.eq(3)).orderBy(accountSettlement.setStartTime.desc())
                .offset(0).limit(12).fetch();

        return fetch;
    }


    /**
     *
     * @param cpSettlementMoneyDTO
     * @return
     */
    public ResultVO getCpSettleDataForPie(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        // 12个账期分别 作为list 计算得到一个账期内所有cp详情的 map-->CpSettleStatisticsVM
        /**
         * 1.获取给定账期(e.g 单账期)内的待结算的所有cp数据（cp可以被条件查询）集合 getRawList();
         * 2.对该票数据进行计算 每个cp在当前账期内的占比和总额详情
         */
        List<AccountSettlement> accountSettlementList = getAccountSettlementList(cpSettlementMoneyDTO);
        ArrayList<CpSettleStatisticsVM> singleDataVMList = new ArrayList<>();
        for(AccountSettlement as:accountSettlementList){
            ArrayList<AccountSettlement> singleAcs = new ArrayList<>();// 单账期数据
            singleAcs.add(as);
            // 1.
            List<CpSettlementMoney> rawList = getRawList(singleAcs, cpSettlementMoneyDTO);
            // 2. 单账期内的所有cp结算详情
            Map<String, CpSettlementMoneyVM> oneMap = countOne(rawList);
            //3. oneMap.getValue() == CpSettlementMoneyVM
            // -----------------------------------------------开始装配单账期内的详情-----------------------------------------------------------
            // ---------------------------需要：账期名，该账期内的总收入，该账期内的所有cp结算详情列表，top 6-----------------------------------------
            CpSettleStatisticsVM singleDataVM = new CpSettleStatisticsVM();
            // 账期名
            singleDataVM.setName(as.getName());
            // 该账期内的所有cp的总收入（根据masterCode）
            BigDecimal allIncome = cpSettlementMoneyRepository.sumByMasterCode(as.getCode());
            singleDataVM.setGrossIncome(allIncome);

            // 该账期内的所有cp结算详情列表
            ArrayList<CpSettlementMoneyVM> cpList = new ArrayList<>();
            for(Map.Entry<String,CpSettlementMoneyVM> entry: oneMap.entrySet()){
                CpSettlementMoneyVM vm = entry.getValue();
                cpList.add(vm);
            }
            singleDataVM.setList(cpList);

            // top 6 ---对 cpList 进行按 占比 排序
            int size = cpList.size();
            listDesc(cpList);
            // 取前5+1 ==要考虑 cpSettlementMoneyVMS的size是否不足5个
            /**
             * 0<size<=5 只能取 size的个数，做完排行后
             * 5<size 直接复制
             */
            LinkedList<CpSettleStatisticsVM.TopSixListVM> topSixListVMS = CollUtil.newLinkedList();
            if(size<=5 && size>0){
                for(int i=0;i<size;i++){
                    // 将已排好序的 cpSettlementMoneyVM 复制到 topSixListVM
                    CpSettleStatisticsVM.TopSixListVM topSixListVM = new CpSettleStatisticsVM.TopSixListVM();
                    BeanUtils.copyProperties(cpList.get(i),topSixListVM);
                    topSixListVM.setRank(i+1);
                    topSixListVMS.add(topSixListVM);
                }
            }else if(size>5){//>5 时取前5 +1
                double other= Double.valueOf(0);
                BigDecimal grossIncome = BigDecimal.ZERO;

                for(int i=0;i<6;i++){
                    // 将已排好序的 cpSettlementMoneyVM 复制到 topSixListVM
                    CpSettleStatisticsVM.TopSixListVM topSixListVM = new CpSettleStatisticsVM.TopSixListVM();
                    BeanUtils.copyProperties(cpList.get(i),topSixListVM);
                    topSixListVM.setRank(i+1);
                    /**
                     *                 对第6个需要特殊计算：
                     *                 rank==6
                     *                 cpname ==其他
                     *                 grossIncome==allSettlementMoney-sum(grossIncome)
                     *                 ratio==1-sum(ratio)
                     */
                    if(i==5){
                        topSixListVM.setCpname("其他");
                        topSixListVM.setSettlementMoney(allIncome.subtract(grossIncome));
                        topSixListVM.setRatio(Double.toString(100-other));
                    }
                    other+=Double.valueOf(topSixListVM.getRatio());// i==4即第5名时，需要对第6名计算剩余ratio
                    grossIncome=grossIncome.add(topSixListVM.getSettlementMoney());
                    topSixListVMS.add(topSixListVM);
                }
                singleDataVM.setTopSixList(topSixListVMS);
                singleDataVMList.add(singleDataVM);
            }

        }
        return ResultVOUtil.success(singleDataVMList);
    }

    /**
     * 按占比排序
     * @param list
     */
    private void listDesc(List<CpSettlementMoneyVM> list){
        //对history进行排序 升序排序
        Collections.sort(list, (o1,o2)-> {
//            Comparator<CpSettlementMoneyVM> comparing = Comparator.comparing(CpSettlementMoneyVM::getRatio);
            return o2.getRatio().compareTo(o1.getRatio());
        });
    }


    /**
     *获取给定账期内的 按查询条件所得的cp的原始结算数据集合
     *
     * @param cpSettlementMoneyDTO 根据cpname查询
     * @return
     */
    private List<CpSettlementMoney> getRawList(List<AccountSettlement> settlementList,CpSettlementMoneyDTO cpSettlementMoneyDTO){
        ArrayList<CpSettlementMoney> cpSettlementMonies = new ArrayList<>();
//        List<AccountSettlement> settlementList = getAccountSettlementList(cpSettlementMoneyDTO);

        settlementList.forEach(accountSettlement -> {
            Criteria<CpSettlementMoney> criteria = new Criteria<>();
            String bCodes = cpSettlementMoneyDTO.getBCodes();
            String pCodes = cpSettlementMoneyDTO.getPCodes();
            List<String> bCodeList=null;
            List<String> pCodeList=null;
            if(StringUtils.isNotBlank(bCodes)){
                bCodeList= Arrays.asList(StringUtils.split(bCodes, ","));
            }

            if(StringUtils.isNotBlank(pCodes)){
                pCodeList= Arrays.asList(StringUtils.split(pCodes, ","));
            }
            criteria
                    .add(Restrictions.like("cpname",cpSettlementMoneyDTO.getCpname()))
                    .add(Restrictions.in("businessCode",bCodeList,true))
                    .add(Restrictions.in("productCode",pCodeList,true))
                    .add(Restrictions.eq("masterCode",accountSettlement.getCode()))
            ;
            List<CpSettlementMoney> cpSettlementMoneyList = cpSettlementMoneyRepository.findAll();
            cpSettlementMoneyList.forEach(cp->{
                Collections.addAll(cpSettlementMonies,cp);
            });
        });
        return cpSettlementMonies;
    }

    /**
     * 计算 给定账期内的各个cp的占比、总金额详情
     * @param rawList
     * @return
     */
    private Map<String, CpSettlementMoneyVM> countOne(List<CpSettlementMoney> rawList){
            Map<String, CpSettlementMoneyVM> map = Maps.newHashMap();
            BigDecimal sumAll = BigDecimal.ZERO;

            for(CpSettlementMoney cp:rawList) {
                CpSettlementMoneyVM cpSettlementMoneyVM = new CpSettlementMoneyVM();
                if (cp.getSettlementMoney() == null)
                    cp.setSettlementMoney(BigDecimal.ZERO);
                // 统计总金额
                sumAll = sumAll.add(cp.getSettlementMoney());
                BeanUtils.copyProperties(cp, cpSettlementMoneyVM);
                List<String> productNameList = cpSettlementMoneyRepository.findProdNameListByCpCode(cp.getMasterCode(), cp.getCpcode());
                if (productNameList.size() > 0)
                    cpSettlementMoneyVM.setPNmaeList(productNameList);
                List<String> businessNameList = cpSettlementMoneyRepository.findBizNameListByCpCode(cp.getMasterCode(), cp.getCpcode());
                if (businessNameList.size() > 0)
                    cpSettlementMoneyVM.setBNameList(businessNameList);
                if (map.get(cpSettlementMoneyVM.getCpcode()) != null) {
                    // 同名cp金额汇总
                    BigDecimal sumMoney = map.get(cpSettlementMoneyVM.getCpcode()).getSettlementMoney();
                    sumMoney = sumMoney.add(cpSettlementMoneyVM.getSettlementMoney());
                    cpSettlementMoneyVM.setSettlementMoney(sumMoney);
                }
                map.put(cpSettlementMoneyVM.getCpcode(), cpSettlementMoneyVM);
            }

            for(Map.Entry<String,CpSettlementMoneyVM> entry:map.entrySet()){
                CpSettlementMoneyVM vm = entry.getValue();
                BigDecimal one = vm.getSettlementMoney();
                BigDecimal ratio = one.divide(sumAll, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
                vm.setRatio(Double.toString(ratio.doubleValue()));
            }
            return map;
    }

    /**
     * 计算 每个cp在所有账期的占比和总金额
     * @param cpSettlementMoneyDTO
     * @return
     */
    @Override
    public ResultVO getCpSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        /**获取账期数据*/
        List<AccountSettlement> settlementList = getAccountSettlementList(cpSettlementMoneyDTO);
        /**获取所有账期的所有待处理的数据*/
        List<CpSettlementMoney> rawList = getRawList(settlementList,cpSettlementMoneyDTO);
        // 按cpCode处理
        Map<String, CpSettlementMoneyVM> moneyVMMap = countOne(rawList);
        return ResultVOUtil.success(moneyVMMap);
    }

    @Override
    public ResultVO getBizSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        /**获取账期数据*/
        List<AccountSettlement> settlementList = getAccountSettlementList(cpSettlementMoneyDTO);
        /**获取所有账期的所有待处理的数据*/
        List<CpSettlementMoney> rawList = getRawList(settlementList,cpSettlementMoneyDTO);
        // 按bizCode处理
        Map<String, CpSettlementMoneyVM> map = countOneByBizCode(rawList);
        return ResultVOUtil.success(map);
    }


    private Map<String, CpSettlementMoneyVM> countOneByBizCode(List<CpSettlementMoney> rawList){
        Map<String, CpSettlementMoneyVM> map = Maps.newHashMap();
        BigDecimal sumAll = BigDecimal.ZERO;

        for(CpSettlementMoney cp:rawList) {
            CpSettlementMoneyVM cpSettlementMoneyVM = new CpSettlementMoneyVM();
            if(cp.getBusinessCode()==null)
                continue;
            if (cp.getSettlementMoney() == null)
                cp.setSettlementMoney(BigDecimal.ZERO);
            // 统计总金额
            sumAll = sumAll.add(cp.getSettlementMoney());
            BeanUtils.copyProperties(cp, cpSettlementMoneyVM);
            List<String> productNameList = cpSettlementMoneyRepository.findProdNameListByBizCode(cp.getMasterCode(), cp.getBusinessCode());
            if (productNameList.size() > 0)
                cpSettlementMoneyVM.setPNmaeList(productNameList);
            List<String> cpNameList =  cpSettlementMoneyRepository.findCpNameListByBizCode(cp.getMasterCode(), cp.getBusinessCode());
            if (cpNameList.size() > 0)
                cpSettlementMoneyVM.setCpNameList(cpNameList);
            if (map.get(cpSettlementMoneyVM.getBusinessCode()) != null) {
                // 同名cp金额汇总
                BigDecimal sumMoney = map.get(cpSettlementMoneyVM.getBusinessCode()).getSettlementMoney();
                sumMoney = sumMoney.add(cpSettlementMoneyVM.getSettlementMoney());
                cpSettlementMoneyVM.setSettlementMoney(sumMoney);
            }
            map.put(cpSettlementMoneyVM.getBusinessCode(), cpSettlementMoneyVM);
        }

        for(Map.Entry<String,CpSettlementMoneyVM> entry:map.entrySet()){
            CpSettlementMoneyVM vm = entry.getValue();
            BigDecimal one = vm.getSettlementMoney();
            BigDecimal ratio = one.divide(sumAll, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
            vm.setRatio(Double.toString(ratio.doubleValue()));
        }
        return map;
    }

    @Override
    public ResultVO getProdSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        /**获取账期数据*/
        List<AccountSettlement> settlementList = getAccountSettlementList(cpSettlementMoneyDTO);
        /**获取所有账期的所有待处理的数据*/
        List<CpSettlementMoney> rawList = getRawList(settlementList,cpSettlementMoneyDTO);
        // 按prodCode处理
        Map<String, CpSettlementMoneyVM> map = Maps.newHashMap();
        BigDecimal sumAll = BigDecimal.ZERO;

        for(CpSettlementMoney cp:rawList) {
            CpSettlementMoneyVM cpSettlementMoneyVM = new CpSettlementMoneyVM();
            if(cp.getProductCode()==null)
                continue;
            if (cp.getSettlementMoney() == null)
                cp.setSettlementMoney(BigDecimal.ZERO);
            // 统计总金额
            sumAll = sumAll.add(cp.getSettlementMoney());
            BeanUtils.copyProperties(cp, cpSettlementMoneyVM);
            List<String> bizNameList = cpSettlementMoneyRepository.findBizNameListByProdCode(cp.getMasterCode(), cp.getProductCode());
            if (bizNameList.size() > 0)
                cpSettlementMoneyVM.setBNameList(bizNameList);
            List<String> cpNameList =  cpSettlementMoneyRepository.findCpNameListByProdCode(cp.getMasterCode(), cp.getProductCode());
            if (cpNameList.size() > 0)
                cpSettlementMoneyVM.setCpNameList(cpNameList);
            if (map.get(cpSettlementMoneyVM.getProductCode()) != null) {
                // 同名cp金额汇总
                BigDecimal sumMoney = map.get(cpSettlementMoneyVM.getProductCode()).getSettlementMoney();
                sumMoney = sumMoney.add(cpSettlementMoneyVM.getSettlementMoney());
                cpSettlementMoneyVM.setSettlementMoney(sumMoney);
            }
            map.put(cpSettlementMoneyVM.getProductCode(), cpSettlementMoneyVM);
        }

        for(Map.Entry<String,CpSettlementMoneyVM> entry:map.entrySet()){
            CpSettlementMoneyVM vm = entry.getValue();
            BigDecimal one = vm.getSettlementMoney();
            BigDecimal ratio = one.divide(sumAll, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
            vm.setRatio(Double.toString(ratio.doubleValue()));
        }
        return ResultVOUtil.success(map);
    }



}
