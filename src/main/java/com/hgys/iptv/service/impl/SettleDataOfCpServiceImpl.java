package com.hgys.iptv.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.hgys.iptv.common.Criteria;
import com.hgys.iptv.common.Restrictions;
import com.hgys.iptv.controller.vm.CpSettleStatisticsVM;
import com.hgys.iptv.controller.vm.CpSettlementMoneyVM;
import com.hgys.iptv.controller.vm.SingleCpSettleMoneyVM;
import com.hgys.iptv.model.CpSettlementMoney;
import com.hgys.iptv.model.dto.CpSettlementMoneyDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import com.hgys.iptv.service.SettleDataOfCpService;
import com.hgys.iptv.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName SettleDataOfCpServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/28 16:11
 * @Description: TODO
 */
@Service
public class SettleDataOfCpServiceImpl implements SettleDataOfCpService {

    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;

    /**
     * CP名称、关联产品、关联业务、结算金额（各账期结算金额之和）、占比
     * 账期（日期区间）、CP名称（文本输入）。
     *
     * 以账期为root
     *      child1：cpList：包含每个账期的金额+占比
     *      child2：汇总：取占比前5cp+剩余其他
     */

    /**
     *
     * @param cpSettlementMoneyDTO
     * @return
     */
    public ResultVO getCpSettleDataForPie(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        // 饼状图所需的最近12个账期的各个cp的数据列表：包含金额、占比 和 前5+1排行数据
        // 一个账期的数据
        CpSettleStatisticsVM cpSettleStatisticsVM = new CpSettleStatisticsVM();
        //1.获取每个账期(月)的总金额
        BigDecimal allSettlementMoney = cpSettlementMoneyRepository.sumAllSettlementMoney(12);
        // 保存排行数据的list
        LinkedList<CpSettleStatisticsVM.TopSixListVM> topSixListVMS = CollUtil.newLinkedList();
        ResultVO cpSettleData = getCpSettleData(cpSettlementMoneyDTO);
        // 最近12个账期的各个cp的数据列表：包含金额、占比
        List<CpSettlementMoneyVM> cpSettlementMoneyVMS = (List<CpSettlementMoneyVM>) cpSettleData.getData();
        int size = cpSettlementMoneyVMS.size();
        if(size==0)
            return ResultVOUtil.error("1","账期内无数据！");
        // child1
        cpSettleStatisticsVM.setCpList(cpSettlementMoneyVMS);
        // 对 CpSettlementMoneyVM中的占比排行
        listDesc(cpSettlementMoneyVMS);
        // 取前5+1 ==要考虑 cpSettlementMoneyVMS的size是否不足5个
        /**
         * 0<size<=5 只能取 size的个数，做完排行后
         * 5<size 直接复制
         */
        if(size<=5){

        }else {//>5 时取前5 +1
            double other= Double.valueOf("0");
            BigDecimal grossIncome = BigDecimal.ZERO;

            for(int i=0;i<6;i++){
                // 将已排好序的 cpSettlementMoneyVM 复制到 topSixListVM
                CpSettleStatisticsVM.TopSixListVM topSixListVM = new CpSettleStatisticsVM.TopSixListVM();
                BeanUtils.copyProperties(cpSettlementMoneyVMS.get(i),topSixListVM);
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
                    topSixListVM.setGrossIncome(allSettlementMoney.subtract(grossIncome));
                    topSixListVM.setRatio(Double.toString(1.00-other));
                }
                other+=Double.valueOf(topSixListVM.getRatio());// i==4即第5名时，需要对第6名计算剩余ratio
                grossIncome=grossIncome.add(topSixListVM.getGrossIncome());
                topSixListVMS.add(topSixListVM);
            }

        }

        //child2
        cpSettleStatisticsVM.setTopSixList(topSixListVMS);
        return ResultVOUtil.success(cpSettleStatisticsVM);
    }

    public void listAsc(List<CpSettlementMoneyVM> list){
        //对history进行排序 升序排序
        Collections.sort(list, (o1,o2)-> {
//            Comparator<CpSettlementMoneyVM> comparing = Comparator.comparing(CpSettlementMoneyVM::getRatio);
            return o1.getRatio().compareTo(o2.getRatio());
        });
    }

    public void listDesc(List<CpSettlementMoneyVM> list){
        Collections.sort(list,(o1,o2)-> {
                int flag = o1.getRatio().compareTo(o2.getRatio());
                if(flag == -1){
                    flag = 1;
                }else if(flag == 1){
                    flag = -1;
                }
                return flag;
        });
    }

        @Override
    public ResultVO getCpSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
            /**所有账期对象的列表--默认取12个*/
        List<SingleCpSettleMoneyVM> singleCpSettleMoneyVMS = new LinkedList<>();
        // 计算每个账期的所有cp的结算总金额
            for(int i=12;i>0;i--){
                SingleCpSettleMoneyVM singleCpSettleMoneyVM = new SingleCpSettleMoneyVM();
                if(i==1){
                    singleCpSettleMoneyVM.setGrossIncome(cpSettlementMoneyRepository.sumAllSettlementMoney(1));
                }else{
                    //最近12个月
                    BigDecimal b1 = cpSettlementMoneyRepository.sumAllSettlementMoney(i);
                    //最近11个月
                    BigDecimal b2 = cpSettlementMoneyRepository.sumAllSettlementMoney(i-1);
                    // 最后一个是 最近2个月的-最近1个月的==倒数第二个账期的总金额
                    BigDecimal subtract = b1.subtract(b2);
                    singleCpSettleMoneyVM.setGrossIncome(subtract);
                    /**
                     * 在每个账期内计算每个cp的详情
                     */
                }
                singleCpSettleMoneyVMS.add(singleCpSettleMoneyVM);
            }

            //     ==List<CpSettlementMoneyVM> list;
        List<CpSettlementMoneyVM> cpSettlementMoneyVMS = new ArrayList<>();
        Criteria<CpSettlementMoney> criteria = new Criteria<>();
        criteria.add(Restrictions.like("cpname",cpSettlementMoneyDTO.getCpname()))
        .add(Restrictions.lte("createTime",cpSettlementMoneyDTO.getSetEndTime()))
        .add(Restrictions.gte("createTime",cpSettlementMoneyDTO.getSetStartTime()));
        List<CpSettlementMoney> cps = cpSettlementMoneyRepository.findAll(criteria);

        //对list进行中相同cp的进行金额汇总和计算占比
        //1.获取每个账期(月)的总金额
        BigDecimal allSettlementMoney = cpSettlementMoneyRepository.sumAllSettlementMoney(12);

        //2.获取每个账期(月)的cp总金额
        // 对相同的cpcode只生成一个 CpSettlementMoneyVM
//        HashMap<String, BigDecimal> cpMap = new HashMap<>();
        cps.forEach(cp->{
            BigDecimal cpMoney = cpSettlementMoneyRepository.sumSettleMoneyByCpCode(cp.getCpcode());
            BigDecimal divide = cpMoney.divide(allSettlementMoney, 2, RoundingMode.HALF_UP);
            CpSettlementMoneyVM cpSettlementMoneyVM = new CpSettlementMoneyVM();
            BeanUtils.copyProperties(cp,cpSettlementMoneyVM);
            cpSettlementMoneyVM.setGrossIncome(cpMoney);
            cpSettlementMoneyVM.setRatio(divide.toString());
            cpSettlementMoneyVMS.add(cpSettlementMoneyVM);
        });
        return ResultVOUtil.success(singleCpSettleMoneyVMS);
    }

    @Override
    public ResultVO getBizSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        List<CpSettlementMoneyVM> cpSettlementMoneyVMS = new ArrayList<>();
        Criteria<CpSettlementMoney> criteria = new Criteria<>();
        criteria.add(Restrictions.like("businessName",cpSettlementMoneyDTO.getBusinessName()))
                .add(Restrictions.lte("createTime",cpSettlementMoneyDTO.getSetEndTime()))
                .add(Restrictions.gte("createTime",cpSettlementMoneyDTO.getSetStartTime()));
        List<CpSettlementMoney> cps = cpSettlementMoneyRepository.findAll(criteria);

        //1.获取每个账期(月)的总金额
        BigDecimal allSettlementMoney = cpSettlementMoneyRepository.sumAllSettlementMoney(12);
        //2.获取每个账期(月)的cp总金额
        cps.forEach(cp->{
            if(cp.getBusinessCode()!=null){
                BigDecimal cpMoney = cpSettlementMoneyRepository.sumSettleMoneyByBizCode(cp.getBusinessCode());
                BigDecimal divide = cpMoney.divide(allSettlementMoney, 2, RoundingMode.HALF_UP);
                CpSettlementMoneyVM cpSettlementMoneyVM = new CpSettlementMoneyVM();
                BeanUtils.copyProperties(cp,cpSettlementMoneyVM);
                cpSettlementMoneyVM.setGrossIncome(cpMoney);
                cpSettlementMoneyVM.setRatio(divide.toString());
                cpSettlementMoneyVMS.add(cpSettlementMoneyVM);
            }
        });
        return ResultVOUtil.success(cpSettlementMoneyVMS);
    }

    @Override
    public ResultVO getProdSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        List<CpSettlementMoneyVM> cpSettlementMoneyVMS = new ArrayList<>();
        Criteria<CpSettlementMoney> criteria = new Criteria<>();
        criteria.add(Restrictions.like("productName",cpSettlementMoneyDTO.getProductName()))
                .add(Restrictions.lte("createTime",cpSettlementMoneyDTO.getSetEndTime()))
                .add(Restrictions.gte("createTime",cpSettlementMoneyDTO.getSetStartTime()));
        List<CpSettlementMoney> cps = cpSettlementMoneyRepository.findAll(criteria);

        //1.获取每个账期(月)的总金额
        BigDecimal allSettlementMoney = cpSettlementMoneyRepository.sumAllSettlementMoney(12);
        //2.获取每个账期(月)的cp总金额
        cps.forEach(cp->{
            if(cp.getProductCode()!=null){
                BigDecimal cpMoney = cpSettlementMoneyRepository.sumSettleMoneyByProdCode(cp.getProductCode());
                BigDecimal divide = cpMoney.divide(allSettlementMoney, 2, RoundingMode.HALF_UP);
                CpSettlementMoneyVM cpSettlementMoneyVM = new CpSettlementMoneyVM();
                BeanUtils.copyProperties(cp,cpSettlementMoneyVM);
                cpSettlementMoneyVM.setGrossIncome(cpMoney);
                cpSettlementMoneyVM.setRatio(divide.toString());
                cpSettlementMoneyVMS.add(cpSettlementMoneyVM);
            }
        });
        return ResultVOUtil.success(cpSettlementMoneyVMS);
    }
}
