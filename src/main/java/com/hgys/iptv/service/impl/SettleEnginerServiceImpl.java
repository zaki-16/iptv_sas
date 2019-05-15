package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.vm.OrderCPWithCPListVM;
import com.hgys.iptv.model.dto.SettleDTO;
import com.hgys.iptv.model.dto.SettleMetaResource;
import com.hgys.iptv.service.OrderCpService;
import com.hgys.iptv.service.SettleEnginerService;
import com.hgys.iptv.util.AbstractBaseServiceImpl;
import com.hgys.iptv.util.SettleUtil;
import com.hgys.iptv.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 分账结算
 *
 * @ClassName SettleEnginerServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/14 09:20
 * @Description: TODO
 */
@Service
public class SettleEnginerServiceImpl extends AbstractBaseServiceImpl implements SettleEnginerService {

    @Autowired
    private OrderCpService orderCpService;


    /**
     * 业务级结算
     * 结算规则：列出所有已有业务，多选。
     * 每选择一项业务，录入权重（百分比值），继而选择CP并录入结算百分比值。
     * 提交时校验业务权重之和必须100%，CP在该业务结算百分比值之和必须100%。
     */
    @Override
    public SettleMetaResource settleByBusiness(SettleDTO settleDTO) {
        //统一返回对象类型
        SettleMetaResource resource = new SettleMetaResource();
        //总收入
        String grossIncome = settleDTO.getGrossIncome();
        return null;
    }
    /**
     * 业务级结算--按金额
     * 7.5.3结算规则：
     * 选择按比例结算，继而选择业务（列出所有已有业务，多选），继而选择CP并填入结算百分比值。
     *          提交时校验该业务下所有CP结算比例值须和为100%；
     * 选择按金额结算，继而选择业务，继而选择CP并填入结算金额。
     */
    @Override
    public SettleMetaResource settleByBusinessWithAmount(SettleDTO settleDTO) {
        return null;

    }
    /**
     * 业务级结算--按比例
     */
    @Override
    public SettleMetaResource settleByBusinessWithRatio(SettleDTO settleDTO) {
        return null;

    }
    /**
     * cp.settleType = 1:定比例结算, 2：定金额结算
     *
     * 7.4.3结算规则：
     * 选择按比例结算，继而选择CP并填入结算百分比值。
     *      提交时校验所有CP结算百分比值须和为100%；
     * 选择按金额结算，继而选择CP并填入结算金额。
     */
    @Override
    public SettleMetaResource settleByCp(SettleDTO settleDTO) {
        //统一返回对象类型
        SettleMetaResource resource = new SettleMetaResource();
        //总收入
        String grossIncome = settleDTO.getGrossIncome();
        //cp结算编码
        String settleCode = settleDTO.getSettleRuleCode();
        //根据cp结算编码查询对应cp结算预设列表---------改
        OrderCPWithCPListVM orderCPWithCPListVM = orderCpService.findById(settleCode);
        //获取结算类型
        Integer settleType = orderCPWithCPListVM.getSettleaccounts();
        //获取参与分账的cp列表
        List<OrderCPWithCPListVM.OrderCpWithCp> withCps = orderCPWithCPListVM.getList();

        //1.先校验权重是否合法+校验权重数据格式是否合法,如果合法并转换
        //2.校验
        //1.定比例结算
        if(settleType==1){
            List<String> validWeightList = new ArrayList<>();
            withCps.forEach(cp->{
                //---------改weight的类型
                String weight = cp.getWeight().toString();
                //对权重进行格式校验并换算
//                double weightFormat = Validator.validWeightFormat(weight.toString());
                validWeightList.add(weight);

                resource.setCpCode(cp.getCode());
                resource.setCpName(cp.getCpname());

                //先算好每个cp的账目，校验通过时才返回
                String cpAccount = SettleUtil.multi(grossIncome, weight);
                resource.setSettleAccount(cpAccount);
                resource.setMsg("分账完成!");
            });
            //校验权重是否==100，等于返回true，其他返回false
            boolean isValid = Validator.validWeightForString(validWeightList);
            if(!isValid){
                //权重之和不等于100，返回
                resource.setMsg("分账权重不合法，请核对！");
                return resource;
            }
            //2.定金额结算
        }else if(settleType==2){
            List<String> validMoneyList = new ArrayList<>();
            withCps.forEach(cp->{
                String money = cp.getMoney().toString();
                validMoneyList.add(money);

                resource.setCpCode(cp.getCode());
                resource.setCpName(cp.getCpname());

                //先算好每个cp的账目，校验通过时才返回---要改
                resource.setSettleAccount(money);
                resource.setMsg("分账完成!");
            });
        }
        return resource;
    }

    /**
     * 产品级结算--要有各维度权重
     * 7.3.3结算规则：单维度结算、多维度结算，二选一。
     * 选择单维度结算，通过下拉列表从结算单维度选择结算维度。
     * 选择多维度结算，通过下拉列表从结算组合维度选择组合维度，单选。
     * 用户可以自定义组合（跳转至结算组合维度新增页面）。
     */
    @Override
    public SettleMetaResource settleByProduct(SettleDTO settleDTO) {
        return null;

    }

    /**
     * 订购量结算
     * CP的订购量占所有CP总订购量的比值，该比值乘以总收入，得到CP结算金额。
     */
    @Override
    public SettleMetaResource settleByQuantity(SettleDTO settleDTO) {
        return null;

    }



}
