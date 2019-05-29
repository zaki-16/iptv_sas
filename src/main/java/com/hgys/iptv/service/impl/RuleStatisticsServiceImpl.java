package com.hgys.iptv.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.RuleStatisticsService;
import com.hgys.iptv.service.SettlementStatisticsService;
import com.hgys.iptv.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class RuleStatisticsServiceImpl implements RuleStatisticsService {

    @Autowired
    private RuleStatisticsRepository ruleStatisticsRepository;
    @Autowired
    private OrderQuantityRepository orderQuantityRepository;
    @Autowired
    private OrderBusinessRepository orderBusinessRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderCpRepository orderCpRepository;

    @Autowired
    private SettlementStatisticsRepository settlementStatisticsRepository;

    @Autowired
    private OrderBusinessComparisonRepository orderBusinessComparisonRepository;


    @Autowired
    private AccountSettlementRepository accountSettlementRepository;

    @Autowired
    private SettlementOrderRepository settlementOrderRepository;

    @Autowired
    private SettlementMoneyRepository settlementMoneyRepository;


    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;

    @Override
    public SettlementStatisticsListVM findsettlement(AccountSettlement a) {
        List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlement();
        SettlementStatisticsListVM vms = new SettlementStatisticsListVM();
        BeanUtils.copyProperties( a,vms);
        for (AccountSettlement vm : settlementList) {
            if (1 == vm.getSet_type()) {
                List<OrderQuantity> or = orderQuantityRepository.finddetail(vm.getSet_ruleCode());
                List<OrderQuantityAddVM> list = new ArrayList<>();
                for (OrderQuantity f : or) {
                    OrderQuantityAddVM s = new OrderQuantityAddVM();
                    BeanUtils.copyProperties(f, s);
                    list.add(s);
                    vms.setName(vm.getName());
                    vms.setCode(vm.getCode());
                    vms.setSet_ruleCode(vm.getSet_ruleCode());
                    vms.setSet_ruleName(vm.getSet_ruleName());
                    vms.setSet_type(vm.getSet_type());
                    vms.setOrderQuantityAddVM(list);
                }
            } else if (2 == vm.getSet_type()) {
                List<OrderBusiness> or = orderBusinessRepository.finddetail(vm.getSet_ruleCode());
                List<OrderBusinessWithCPAddVM> list = new ArrayList<>();
                for (OrderBusiness f : or) {
                    OrderBusinessWithCPAddVM s = new OrderBusinessWithCPAddVM();
                    BeanUtils.copyProperties(f, s);
                    list.add(s);
                    vms.setName(vm.getName());
                    vms.setCode(vm.getCode());
                    vms.setSet_ruleCode(vm.getSet_ruleCode());
                    vms.setSet_ruleName(vm.getSet_ruleName());
                    vms.setSet_type(vm.getSet_type());
                    vms.setOrderBusinessWithCPAddVM(list);
                }
            } else if (3 == vm.getSet_type()) {
                List<OrderProduct> or = orderProductRepository.finddetail(vm.getSet_ruleCode());
                List<OrderProductWithSettlementfindVM> list = new ArrayList<>();
                for (OrderProduct f : or) {
                    OrderProductWithSettlementfindVM s = new OrderProductWithSettlementfindVM();
                    BeanUtils.copyProperties(f, s);
                    list.add(s);
                    vms.setName(vm.getName());
                    vms.setCode(vm.getCode());
                    vms.setSet_ruleCode(vm.getSet_ruleCode());
                    vms.setSet_ruleName(vm.getSet_ruleName());
                    vms.setSet_type(vm.getSet_type());
                    vms.setOrderProductWithSettlementfindVM(list);
                }
            } else if (4 == vm.getSet_type()) {
                List<OrderCp> or = orderCpRepository.finddetail(vm.getSet_ruleCode());
                List<OrderCPWithCPListVM> list = new ArrayList<>();
                for (OrderCp f : or) {
                    OrderCPWithCPListVM s = new OrderCPWithCPListVM();
                    BeanUtils.copyProperties(f, s);
                    list.add(s);
                    vms.setName(vm.getName());
                    vms.setCode(vm.getCode());
                    vms.setSet_ruleCode(vm.getSet_ruleCode());
                    vms.setSet_ruleName(vm.getSet_ruleName());
                    vms.setSet_type(vm.getSet_type());
                    vms.setOrderCPWithCPListVM(list);
                }
            } else if (5 == vm.getSet_type()) {
                List<OrderBusinessComparison> or = orderBusinessComparisonRepository.finddetail(vm.getSet_ruleCode());
                List<OrderBusinessComparisonQueryVM> list = new ArrayList<>();
                for (OrderBusinessComparison f : or) {
                    OrderBusinessComparisonQueryVM s = new OrderBusinessComparisonQueryVM();
                    BeanUtils.copyProperties(f, s);
                    list.add(s);
                    vms.setName(vm.getName());
                    vms.setCode(vm.getCode());
                    vms.setSet_ruleCode(vm.getSet_ruleCode());
                    vms.setSet_ruleName(vm.getSet_ruleName());
                    vms.setSet_type(vm.getSet_type());
                    vms.setOrderBusinessComparisonQueryVM(list);
                }
            }
        }
            return vms;
    }


    @Override
    public ResultVO findsettlementList(String name, String startTime, String endTime,String set_type,String set_ruleName) {
        if (startTime == null & endTime == null) {
            if (name == null || name == "") {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlement();
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        if(money==null){
                            vm.setTotal_sum(BigDecimal.ZERO);
                        }else {
                            vm.setTotal_sum(money);
                        }
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        if(money==null){
                            vm.setTotal_sum(BigDecimal.ZERO);
                        }else {
                            vm.setTotal_sum(money);
                        }
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        if(money==null){
                            vm.setTotal_sum(BigDecimal.ZERO);
                        }else {
                            vm.setTotal_sum(money);
                        }
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        if(money==null){
                            vm.setTotal_sum(BigDecimal.ZERO);
                        }else {
                            vm.setTotal_sum(money);
                        }
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        if(money==null){
                            vm.setTotal_sum(BigDecimal.ZERO);
                        }else {
                            vm.setTotal_sum(money);
                        }
                    }
                }
                Collections.sort(settlementList, new Comparator<AccountSettlement>() {
                    public int compare(AccountSettlement o1, AccountSettlement o2) {
                        int flag = o1.getTotal_sum().compareTo(o2.getTotal_sum());
                        if (flag==1) {
                            return -1;
                        }
                        if (flag == 0) {
                            return 0;
                        }
                        return 1;
                    }
                });
                List<AccountSettlement> settlementLists =new ArrayList<>();
                for (int i=0;i<settlementList.size();i++){
                    BigDecimal money = null;
                    if(i<6){
                    settlementLists.add(settlementList.get(i));
                }else{
                        settlementLists.add(settlementList.get(i));
                        settlementLists.get(i).setName("其他");
                        money=settlementLists.get(i).getTotal_sum();
                        BigDecimal  money1=settlementList.get(i-1).getTotal_sum();
                        settlementLists.get(i).setTotal_sum(money.add(money1));


                    }

                }
                settlementList.addAll(settlementLists);
                return ResultVOUtil.success(settlementList);
            } else if (name != null && set_type == null && set_ruleName == null) {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlementnames(name);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return ResultVOUtil.success(settlementList);
            } else if (name != null && set_type != null && set_ruleName == null) {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlementnamess(name, set_type);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return ResultVOUtil.success(settlementList);
            } else if (name != null && set_type != null && set_ruleName != null) {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlementnamesss(name, set_type, set_ruleName);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return ResultVOUtil.success(settlementList);
            } else if (name != null && set_type == null && set_ruleName != null) {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlementnamessss(name, set_ruleName);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return ResultVOUtil.success(settlementList);
            }
        } else {
            if (name == null && set_type == null && set_ruleName == null) {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlement(startTime, endTime);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return ResultVOUtil.success(settlementList);
            } else if ((name != null && set_type == null && set_ruleName == null)) {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlementname(startTime, endTime, name);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return ResultVOUtil.success(settlementList);
            } else if ((name != null && set_type != null && set_ruleName == null)) {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlementnames(startTime, endTime, name, set_type);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return ResultVOUtil.success(settlementList);
            } else if ((name != null && set_type != null && set_ruleName == null)) {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlementnames(startTime, endTime, name, set_type);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return ResultVOUtil.success(settlementList);
            } else if ((name != null && set_type != null && set_ruleName != null)) {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlementnamess(startTime, endTime, name, set_type, set_ruleName);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return ResultVOUtil.success(settlementList);
            } else if ((name != null && set_type == null && set_ruleName != null)) {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlementnamesss(startTime, endTime, name, set_ruleName);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type() == 1) {          //订购量结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 2) {   //2:业务级结算;
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 3) {   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 4) {   //4:CP定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    } else if (vm.getSet_type() == 5) {   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }

                return ResultVOUtil.success(settlementList);
            }

        }
        return ResultVOUtil.success();
    }
}




