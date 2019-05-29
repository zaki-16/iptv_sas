package com.hgys.iptv.service.impl;


import com.hgys.iptv.model.AccountSettlement;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.SettlementStatisticsService;
import com.hgys.iptv.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SettlementStatisticsServiceImpl implements SettlementStatisticsService {

    @Autowired
    private SettlementStatisticsRepository settlementStatisticsRepository;

    @Autowired
    private AccountSettlementRepository accountSettlementRepository;

    @Autowired
    private SettlementOrderRepository settlementOrderRepository;

    @Autowired
    private SettlementMoneyRepository settlementMoneyRepository;


    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;



    @Override
    public ResultVO findsettlement(String name, String startTime, String endTime) {
        if (startTime == null & endTime == null ) {
            if(name==null||name=="") {
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlement();
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type()==1){          //订购量结算
                        BigDecimal money = settlementOrderRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==2){   //2:业务级结算;
                        BigDecimal money = settlementMoneyRepository.findByMastermoney(vm.getCode());
                         vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==3){   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==4){   //4:CP定比例结算
                        BigDecimal money = settlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==5){   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return  ResultVOUtil.success(settlementList);
            }else{
                List<AccountSettlement> settlementList = settlementStatisticsRepository.findsettlementname(name);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type()==1){          //订购量结算
                        BigDecimal money = settlementOrderRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==2){   //2:业务级结算;
                        BigDecimal money = settlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==3){   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==4){   //4:CP定比例结算
                        BigDecimal money = settlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==5){   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return  ResultVOUtil.success(settlementList);
            }
        } else {
            if(name==null||name=="") {
            List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlement(startTime,endTime);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type()==1){          //订购量结算
                        BigDecimal money = settlementOrderRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==2){   //2:业务级结算;
                        BigDecimal money = settlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==3){   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==4){   //4:CP定比例结算
                        BigDecimal money = settlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==5){   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return  ResultVOUtil.success(settlementList);
        }else{
                List<AccountSettlement> settlementList = settlementStatisticsRepository.finddatesettlementname(startTime,endTime,name);
                for (AccountSettlement vm : settlementList) {
                    if (vm.getSet_type()==1){          //订购量结算
                        BigDecimal money = settlementOrderRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==2){   //2:业务级结算;
                        BigDecimal money = settlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==3){   //3:产品级结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==4){   //4:CP定比例结算
                        BigDecimal money = settlementMoneyRepository.findByMastermoney(vm.getCode()); //通过code查询
                        vm.setTotal_sum(money);
                    }else if (vm.getSet_type()==5){   // 5:业务定比例结算
                        BigDecimal money = cpSettlementMoneyRepository.findByMastermoney(vm.getCode());
                        vm.setTotal_sum(money);
                    }
                }
                return  ResultVOUtil.success(settlementList);
            }
        }

    }
}