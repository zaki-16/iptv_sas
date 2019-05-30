package com.hgys.iptv.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Maps;
import com.hgys.iptv.common.Criteria;
import com.hgys.iptv.common.Restrictions;
import com.hgys.iptv.controller.vm.CpSettleStatisticsVM;
import com.hgys.iptv.controller.vm.CpSettlementMoneyVM;
import com.hgys.iptv.controller.vm.SingleCpSettleMoneyVM;
import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.CpSettlementMoney;
import com.hgys.iptv.model.dto.CpSettlementMoneyDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import com.hgys.iptv.repository.SettlementStatisticsRepository;
import com.hgys.iptv.service.SettleDataOfCpService;
import com.hgys.iptv.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private SettlementStatisticsRepository settlementStatisticsRepository;
    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;

    /**
     *
     * @param cpSettlementMoneyDTO
     * @return
     */
    public ResultVO getCpSettleDataForPie(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        ResultVO cpSettleData = getCpSettleData_(cpSettlementMoneyDTO);
        List<SingleCpSettleMoneyVM> singleCpSettleMoneyVMS = (List<SingleCpSettleMoneyVM>)cpSettleData.getData();

        List<CpSettleStatisticsVM> cpSettleStatisticsVMS = new ArrayList<>();
        //
        singleCpSettleMoneyVMS.forEach(singleCpSettleMoneyVM -> {
            CpSettleStatisticsVM cpSettleStatisticsVM = new CpSettleStatisticsVM();
            BeanUtils.copyProperties(singleCpSettleMoneyVM,cpSettleStatisticsVM);// 开始组装topSixList
            // 对每个账期内的所有cp按比例排序
            List<CpSettlementMoneyVM> cpList = cpSettleStatisticsVM.getCpList();
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
                double other= Double.valueOf("0");
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
                        topSixListVM.setGrossIncome(singleCpSettleMoneyVM.getGrossIncome().subtract(grossIncome));
                        topSixListVM.setRatio(Double.toString(1.00-other));
                    }
                    other+=Double.valueOf(topSixListVM.getRatio());// i==4即第5名时，需要对第6名计算剩余ratio
                    grossIncome=grossIncome.add(topSixListVM.getGrossIncome());
                    topSixListVMS.add(topSixListVM);
                }
                cpSettleStatisticsVM.setTopSixList(topSixListVMS);
                cpSettleStatisticsVMS.add(cpSettleStatisticsVM);
            }
        });
        return ResultVOUtil.success(cpSettleStatisticsVMS);
    }

    public void listDesc(List<CpSettlementMoneyVM> list){
        //对history进行排序 升序排序
        Collections.sort(list, (o1,o2)-> {
//            Comparator<CpSettlementMoneyVM> comparing = Comparator.comparing(CpSettlementMoneyVM::getRatio);
            return o2.getRatio().compareTo(o1.getRatio());
        });
    }

//    public void listDesc(List<CpSettlementMoneyVM> list){
//
//
//        Collections.sort(list,(o1,o2)-> {
//                int flag = o1.getRatio().compareTo(o2.getRatio());
//                if(flag == -1){
//                    flag = 1;
//                }else if(flag == 1){
//                    flag = -1;
//                }
//                return flag;
//        });
//    }

    public ResultVO getCpSettleData_(CpSettlementMoneyDTO cpSettlementMoneyDTO) {

        /**1.所有账期对象的列表--默认取12个*/
        List<SingleCpSettleMoneyVM> singleCpSettleMoneyVMS = new LinkedList<>();
        // 计算每个账期的所有cp的结算总金额
        // 取最近的12个账期
        BigDecimal b = BigDecimal.ZERO;
        List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlement();
        for(AccountSettlement accountSettlement:settlementList) {
            //2.SingleCpSettleMoneyVM
            SingleCpSettleMoneyVM singleCpSettleMoneyVM = new SingleCpSettleMoneyVM();
            BeanUtils.copyProperties(accountSettlement, singleCpSettleMoneyVM);
            /** 单个账期内所有cp的总金额之和*/
            BigDecimal singleAccount = cpSettlementMoneyRepository.sumByMasterCode(accountSettlement.getCode());
            singleCpSettleMoneyVM.setGrossIncome(singleAccount==null?BigDecimal.valueOf(0):singleAccount);

            //3.List<CpSettlementMoneyVM> list
            Map<String, CpSettlementMoneyVM> cpMap = new HashMap<>();// 记录该账期下cp的总金额
            // 根据 masterCode 查询该账期内的所有cp数据(cp可能会重复，因为产品和业务不同)
            // 按条件查询
            Criteria<CpSettlementMoney> criteria = new Criteria<>();
            criteria.add(Restrictions.like("cpname",cpSettlementMoneyDTO.getCpname()))
                    .add(Restrictions.eq("masterCode",accountSettlement.getCode()))
                    .add(Restrictions.lte("createTime",cpSettlementMoneyDTO.getSetEndTime()))
                    .add(Restrictions.gte("createTime",cpSettlementMoneyDTO.getSetStartTime()));
            List<CpSettlementMoney> cps = cpSettlementMoneyRepository.findAll(criteria);
//                List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findByMasterCode(accountSettlement.getCode());
            // 根据cpcode+masterCode 查询该账期下该cp的结算总额
            //
            for (CpSettlementMoney cpSettlementMoney : cps) {
                CpSettlementMoneyVM cpSettlementMoneyVM = new CpSettlementMoneyVM();
                BeanUtils.copyProperties(cpSettlementMoney,cpSettlementMoneyVM);

//                    cpMap.put(cpSettlementMoney.getCpcode(), cpSettlementMoneyVM);//需要放2次，否则初始化没有数据
//                    // 在map中按cpcode索引到同一个c进行 settlementMoney累加
//                    if(cpMap.get(cpSettlementMoney.getCpcode()).getGrossIncome()!=null)
//                        b = b.add(cpMap.get(cpSettlementMoney.getCpcode()).getGrossIncome());
                cpSettlementMoneyVM.setGrossIncome(b);
//                    cpMap.put(cpSettlementMoney.getCpcode(), cpSettlementMoneyVM);
            }
            // List<CpSettlementMoneyVM>// 一个账期下的cp汇总
            List<CpSettlementMoneyVM> cpSettlementMoneyVMS = new ArrayList<>();
            // 将cpMap的value
//                for(Map.Entry<String,CpSettlementMoneyVM> entry:cpMap.entrySet()){
//                    cpSettlementMoneyVMS.add(entry.getValue());
//                }
            cpSettlementMoneyVMS.forEach(cp->{
                BigDecimal cpMoney = cp.getGrossIncome();
                BigDecimal divide = cpMoney.divide(singleCpSettleMoneyVM.getGrossIncome(), 2, RoundingMode.HALF_UP);
                cp.setRatio(divide.toString());
            });
            singleCpSettleMoneyVM.setCpList(cpSettlementMoneyVMS);
            singleCpSettleMoneyVMS.add(singleCpSettleMoneyVM);
        }

        return ResultVOUtil.success(singleCpSettleMoneyVMS);
    }


    /**
     *
     * @param cpSettlementMoneyDTO
     * @return
     */
    private List<CpSettlementMoney> getRawList(CpSettlementMoneyDTO cpSettlementMoneyDTO){
        ArrayList<CpSettlementMoney> cpSettlementMonies = new ArrayList<>();
        List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlement();
        settlementList.forEach(accountSettlement -> {
            Criteria<CpSettlementMoney> criteria = new Criteria<>();
            criteria
                    .add(Restrictions.like("cpname",cpSettlementMoneyDTO.getCpname()))
                    .add(Restrictions.like("businessName",cpSettlementMoneyDTO.getBusinessName()))
                    .add(Restrictions.like("productName",cpSettlementMoneyDTO.getProductName()))
                    .add(Restrictions.lte("createTime",cpSettlementMoneyDTO.getSetEndTime()))
                    .add(Restrictions.gte("createTime",cpSettlementMoneyDTO.getSetStartTime()))
                    .add(Restrictions.eq("masterCode",accountSettlement.getCode()));
            List<CpSettlementMoney> cpSettlementMoneyList = cpSettlementMoneyRepository.findAll();
            cpSettlementMoneyList.forEach(cp->{
                Collections.addAll(cpSettlementMonies,cp);
            });
        });
        return cpSettlementMonies;
    }

    @Override
    public ResultVO getCpSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        List<CpSettlementMoney> rawList = getRawList(cpSettlementMoneyDTO);
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
                cpSettlementMoneyVM.setPList(productNameList);
            List<String> businessNameList = cpSettlementMoneyRepository.findBizNameListByCpCode(cp.getMasterCode(), cp.getCpcode());
            if (businessNameList.size() > 0)
                cpSettlementMoneyVM.setBList(businessNameList);
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
        return ResultVOUtil.success(map);
    }

    @Override
    public ResultVO getBizSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        List<CpSettlementMoney> rawList = getRawList(cpSettlementMoneyDTO);
        Map<String, CpSettlementMoneyVM> map = Maps.newHashMap();
        BigDecimal sumAll = BigDecimal.ZERO;

        for(CpSettlementMoney cp:rawList) {
            CpSettlementMoneyVM cpSettlementMoneyVM = new CpSettlementMoneyVM();
            if (cp.getSettlementMoney() == null)
                cp.setSettlementMoney(BigDecimal.ZERO);
            // 统计总金额
            sumAll = sumAll.add(cp.getSettlementMoney());
            BeanUtils.copyProperties(cp, cpSettlementMoneyVM);
            List<String> productNameList = cpSettlementMoneyRepository.findProdNameListByBizCode(cp.getMasterCode(), cp.getBusinessCode());
            if (productNameList.size() > 0)
                cpSettlementMoneyVM.setPList(productNameList);
            List<String> cpNameList =  cpSettlementMoneyRepository.findCpNameListByBizCode(cp.getMasterCode(), cp.getBusinessCode());
            if (cpNameList.size() > 0)
                cpSettlementMoneyVM.setCpList(cpNameList);
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
        return ResultVOUtil.success(map);
    }

    @Override
    public ResultVO getProdSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        List<CpSettlementMoney> rawList = getRawList(cpSettlementMoneyDTO);
        Map<String, CpSettlementMoneyVM> map = Maps.newHashMap();
        BigDecimal sumAll = BigDecimal.ZERO;

        for(CpSettlementMoney cp:rawList) {
            CpSettlementMoneyVM cpSettlementMoneyVM = new CpSettlementMoneyVM();
            if (cp.getSettlementMoney() == null)
                cp.setSettlementMoney(BigDecimal.ZERO);
            // 统计总金额
            sumAll = sumAll.add(cp.getSettlementMoney());
            BeanUtils.copyProperties(cp, cpSettlementMoneyVM);
            List<String> bizNameList = cpSettlementMoneyRepository.findBizNameListByProdCode(cp.getMasterCode(), cp.getProductCode());
            if (bizNameList.size() > 0)
                cpSettlementMoneyVM.setBList(bizNameList);
            List<String> cpNameList =  cpSettlementMoneyRepository.findCpNameListByProdCode(cp.getMasterCode(), cp.getProductCode());
            if (cpNameList.size() > 0)
                cpSettlementMoneyVM.setCpList(cpNameList);
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
