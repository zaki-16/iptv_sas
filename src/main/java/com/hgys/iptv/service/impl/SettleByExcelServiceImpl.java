//package com.hgys.iptv.service.impl;
//
//import com.hgys.iptv.controller.vm.AccountSettlementAddVM;
//import com.hgys.iptv.controller.vm.OrderCPWithCPListVM;
//import com.hgys.iptv.model.dto.SettleMetaResource;
//import com.hgys.iptv.model.vo.ResultVO;
//import com.hgys.iptv.service.SettleByExcelService;
//import com.hgys.iptv.util.SettleUtil;
//import com.hgys.iptv.util.Validator;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName SettleByExcelServiceImpl
// * @Auther: wangz
// * @Date: 2019/5/15 11:49
// * @Description: TODO
// */
//public class SettleByExcelServiceImpl implements SettleByExcelService {
//
//    @Override
//    public ResultVO<?> settleByBusiness(AccountSettlementAddVM settleDTO) {
//        return null;
//    }
//
//    @Override
//    public ResultVO<?> settleByBusinessWithAmount(AccountSettlementAddVM settleDTO) {
//        return null;
//    }
//
//    @Override
//    public ResultVO<?> settleByBusinessWithRatio(AccountSettlementAddVM settleDTO) {
//        return null;
//    }
//
//    @Override
//    public ResultVO<?> settleByCp(AccountSettlementAddVM settleDTO) {
//        //统一返回对象类型
//        SettleMetaResource resource = new SettleMetaResource();
//        //总收入
//        BigDecimal cpAllMoney = settleDTO.getCpAllMoney();
//
//        //获取结算类型
//        Integer settleType = settleDTO.getSet_type();
//        //获取参与分账的cp列表
//        List<OrderCPWithCPListVM.OrderCpWithCp> withCps = orderCPWithCPListVM.getList();
//
//        //1.先校验权重是否合法+校验权重数据格式是否合法,如果合法并转换
//        //2.校验
//        //1.定比例结算
//        if(settleType==1){
//            List<String> validWeightList = new ArrayList<>();
//            withCps.forEach(cp->{
//                //---------改weight的类型
//                String weight = cp.getWeight().toString();
//                //对权重进行格式校验并换算
////                double weightFormat = Validator.validWeightFormat(weight.toString());
//                validWeightList.add(weight);
//
//                resource.setCpCode(cp.getCode());
//                resource.setCpName(cp.getCpname());
//
//                //先算好每个cp的账目，校验通过时才返回
//                String cpAccount = SettleUtil.multi(grossIncome, weight);
//                resource.setSettleAccount(cpAccount);
//                resource.setMsg("分账完成!");
//            });
//            //校验权重是否==100，等于返回true，其他返回false
//            boolean isValid = Validator.validWeightForString(validWeightList);
//            if(!isValid){
//                //权重之和不等于100，返回
//                resource.setMsg("分账权重不合法，请核对！");
//                return resource;
//            }
//            //2.定金额结算
//        }else if(settleType==2){
//            List<String> validMoneyList = new ArrayList<>();
//            withCps.forEach(cp->{
//                String money = cp.getMoney().toString();
//                validMoneyList.add(money);
//
//                resource.setCpCode(cp.getCode());
//                resource.setCpName(cp.getCpname());
//
//                //先算好每个cp的账目，校验通过时才返回---要改
//                resource.setSettleAccount(money);
//                resource.setMsg("分账完成!");
//            });
//        }
//        return resource;
//    }
//
//    @Override
//    public ResultVO<?> settleByProdWithSingleDime(AccountSettlementAddVM settleDTO) {
//        return null;
//    }
//
//    @Override
//    public ResultVO<?> settleByProdWithCombDime(AccountSettlementAddVM settleDTO) {
//        return null;
//    }
//
//    @Override
//    public ResultVO<?> settleByQuantity(AccountSettlementAddVM settleDTO) {
//        return null;
//    }
//}
