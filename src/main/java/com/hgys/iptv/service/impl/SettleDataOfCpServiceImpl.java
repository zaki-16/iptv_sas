package com.hgys.iptv.service.impl;

import com.hgys.iptv.common.Criteria;
import com.hgys.iptv.common.Restrictions;
import com.hgys.iptv.controller.vm.CpSettlementMoneyVM;
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
import java.util.HashMap;
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
     */

    @Override
    public ResultVO getCpSettleData(CpSettlementMoneyDTO cpSettlementMoneyDTO) {
        List<CpSettlementMoneyVM> cpSettlementMoneyVMS = new ArrayList<>();
        Criteria<CpSettlementMoney> criteria = new Criteria<>();
        criteria.add(Restrictions.like("cpname",cpSettlementMoneyDTO.getCpname()))
        .add(Restrictions.lte("createTime",cpSettlementMoneyDTO.getSetEndTime()))
        .add(Restrictions.gte("createTime",cpSettlementMoneyDTO.getSetStartTime()));
        List<CpSettlementMoney> cps = cpSettlementMoneyRepository.findAll(criteria);

        //对list进行中相同cp的进行金额汇总和计算占比
        //1.获取每个账期(月)的总金额
        BigDecimal allSettlementMoney = cpSettlementMoneyRepository.sumAllSettlementMoney();
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
        return ResultVOUtil.success(cpSettlementMoneyVMS);
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
        BigDecimal allSettlementMoney = cpSettlementMoneyRepository.sumAllSettlementMoney();
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
        BigDecimal allSettlementMoney = cpSettlementMoneyRepository.sumAllSettlementMoney();
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
