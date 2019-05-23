package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.AccountSettlementAssemlber;
import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.QAccountSettlement;
import com.hgys.iptv.model.QCp;
import com.hgys.iptv.model.QCpProduct;
import com.hgys.iptv.model.QOrderBusinessComparison;
import com.hgys.iptv.model.QOrderCp;
import com.hgys.iptv.model.QOrderProductWithSCD;
import com.hgys.iptv.model.QProduct;
import com.hgys.iptv.model.bean.CpOrderCpExcelDTO;
import com.hgys.iptv.model.bean.OrderProductDimensionDTO;
import com.hgys.iptv.model.bean.OrderProductDimensionListDTO;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.AccountSettlementService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountSettlementServiceImpl implements AccountSettlementService {

    @Autowired
    private OrderQuantityWithCpRepository quantityWithCpRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderProductWithSCDRepository scdRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private SettlementCombinatorialDimensionFromRepository settlementCombinatorialDimensionFromRepository;

    @Autowired
    private CpRepository cpRepository;
    @Autowired
    private AccountSettlementAssemlber accountSettlementAssemlber;

    @Autowired
    private SettlementDimensionRepository settlementDimensionRepository;

    @Autowired
    private AccountSettlementRepository accountSettlementRepository;

    @Autowired
    private SettlementOrderRepository settlementOrderRepository;

    @Autowired
    private SettlementMoneyRepository settlementMoneyRepository;

    @Autowired
    private SettlementProductSingleRepository settlementProductSingleRepository;

    @Autowired
    private SettlementProductManyRepository settlementProductManyRepository;

    @Autowired
    private SettlementBusinessRepository settlementBusinessRepository;

    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;

    @Autowired
    private OrderBusinessRepository orderBusinessRepository;


    @Autowired
    private OrderCpRepository orderCpRepository;

    @Autowired
    private OrderBusinessComparisonRepository orderBusinessComparisonRepository;

    @Autowired
    private OrderQuantityRepository orderQuantityRepository;






    /**
     * 新增分配结算
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> addAccountSettlement(AccountSettlementAddVM vm) {
        /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算 */
        try{
            /**
             * 新增分账结算源数据
             */
            //1、先新增分配结算信息
            AccountSettlement account = new AccountSettlement();
            String code = CodeUtil.getOnlyCode("FZ",5); //分账结算编码
            account.setCode(code);
            account.setName(vm.getName());
            account.setInputTime(new Timestamp(System.currentTimeMillis()));
            account.setIsdelete(0);
            account.setSet_ruleCode(vm.getSet_ruleCode());
            account.setSet_type(vm.getSet_type());
            account.setRemakes(StringUtils.trimToEmpty(vm.getRemakes()));
            account.setStatus(1);
            account.setSet_type(vm.getSet_type());
            account.setSetStartTime(Timestamp.valueOf(vm.getStartTime()));
            account.setSetEndTime(Timestamp.valueOf(vm.getEndTime()));
            AccountSettlement save = accountSettlementRepository.save(account);

            //1、新增订购量结算源数据
            if (1 == vm.getSet_type()){
                List<CpOrderCpAddVM> cpAddVMS = vm.getCpAddVMS();
                for (CpOrderCpAddVM addVM : cpAddVMS){
                    SettlementOrder order = new SettlementOrder();
                    BeanUtils.copyProperties(addVM,order);
                    order.setMasterCode(code);
                    order.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    order.setOrderMoney(vm.getOrderMoney());
                    settlementOrderRepository.save(order);
                }
            }else if (2 == vm.getSet_type()){
                //2、新增业务级结算源数据
                SettlementMoney money = new SettlementMoney();
                money.setMasterCode(code);
                money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                money.setType(0);
                money.setMoney(vm.getBusinessMoney());
                settlementMoneyRepository.save(money);
            }else if (3 == vm.getSet_type()){
                //3、新增产品级结算结算源数据
                //单维度
                if (null != vm.getDimensionAddVM() && !vm.getDimensionAddVM().isEmpty()){
                    List<OrderProductDimensionAddVM> dimensionAddVM = vm.getDimensionAddVM();
                    for (OrderProductDimensionAddVM addVM : dimensionAddVM){
                        SettlementProductSingle single = new SettlementProductSingle();
                        BeanUtils.copyProperties(addVM,single);
                        single.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        single.setMasterCode(code);
                        settlementProductSingleRepository.save(single);
                    }
                }else if (null != vm.getDimensionListAddVMS() && !vm.getDimensionListAddVMS().isEmpty()){
                    List<OrderProductDimensionListAddVM> listAddVMS = vm.getDimensionListAddVMS();
                    for (OrderProductDimensionListAddVM listAddVM : listAddVMS){
                        SettlementProductMany many = new SettlementProductMany();
                        BeanUtils.copyProperties(listAddVM,many);
                        many.setMasterCode(code);
                        many.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        settlementProductManyRepository.save(many);
                    }
                }
            }else if (4 == vm.getSet_type()){
                //查询是按金额结算还是比列结算
                QOrderCp qOrderCp = QOrderCp.orderCp;
                OrderCp orderCp = queryFactory.selectFrom(qOrderCp)
                        .where(qOrderCp.code.eq(vm.getSet_ruleCode().trim())).fetchFirst();

                if (0 == orderCp.getSettleaccounts()){
                    SettlementMoney money = new SettlementMoney();
                    money.setMasterCode(code);
                    money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    money.setType(1);
                    money.setMoney(vm.getCpAllMoney());
                    settlementMoneyRepository.save(money);
                }else if (1 == orderCp.getSettleaccounts()){
                    SettlementMoney money = new SettlementMoney();
                    money.setMasterCode(code);
                    money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    money.setType(2);
                    money.setMoney(vm.getCpAllMoney());
                    settlementMoneyRepository.save(money);
                }
            }else if (5 == vm.getSet_type()){
                //判断是按金额结算还是比列结算
                QOrderBusinessComparison qOrderBusinessComparison = QOrderBusinessComparison.orderBusinessComparison;
                OrderBusinessComparison comparison = queryFactory.selectFrom(qOrderBusinessComparison)
                        .where(qOrderBusinessComparison.code.eq(vm.getSet_ruleCode().trim())).fetchFirst();
                //1:比例结算；2:金额结算
                if (1 == comparison.getMode()){
                    List<BusinessBelielAddVM> belielAddVMS = vm.getBelielAddVMS();
                    for (BusinessBelielAddVM addVM : belielAddVMS){
                        SettlementBusiness business = new SettlementBusiness();
                        BeanUtils.copyProperties(addVM,business);
                        business.setType(1);
                        business.setBusinessMoney(addVM.getMoney());
                        business.setMasterCode(code);
                        business.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        settlementBusinessRepository.save(business);
                    }
                }else if (2 == comparison.getMode()){
                    List<BusinessBelielAddVM> belielAddVMS = vm.getBelielAddVMS();
                    for (BusinessBelielAddVM addVM : belielAddVMS){
                        SettlementBusiness business = new SettlementBusiness();
                        BeanUtils.copyProperties(addVM,business);
                        business.setType(2);
                        business.setMasterCode(code);
                        business.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        business.setBusinessMoney(addVM.getMoney());
                        settlementBusinessRepository.save(business);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","系统内部错误");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public List<?> excelExport(Integer type, String code) {
        //1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算
        if (type == 1){
            List<OrderQuantityWithCp> cpList = quantityWithCpRepository.findByMasterCode(code.trim());
            List<CpOrderCpExcelDTO> dtos = new ArrayList<>();
            for (OrderQuantityWithCp cp : cpList){
                CpOrderCpExcelDTO dto = new CpOrderCpExcelDTO();
                BeanUtils.copyProperties(cp,dto);
                dtos.add(dto);
            }
            return dtos;
        }else if (type == 3){

            OrderProduct byCode = orderProductRepository.findByCode(code);
            if (null != byCode){
                //查看是单维度还是多维度
                Integer mode = byCode.getMode();
                //查询所有的产品
                List<OrderProductWithSCD> byMasterCode = scdRepository.findByMasterCode(code.trim());
                //查询产品下所有的cp
                if (mode == 1){
                    QCpProduct qCpProduct = QCpProduct.cpProduct; //产品和cp关系表
                    QOrderProductWithSCD qScd = QOrderProductWithSCD.orderProductWithSCD;
                    QCp qCp = QCp.cp; //cp表
                    QProduct qProduct = QProduct.product; //产品表
                    List<OrderProductDimensionDTO> fetch = queryFactory.select(Projections.bean(
                            OrderProductDimensionDTO.class,
                            qCp.code.as("cpcode"),
                            qCp.name.as("cpname"),
                            qProduct.code.as("pcode"),
                            qProduct.name.as("pname")
                    )).from(qScd).innerJoin(qProduct).on(qScd.pcode.eq(qProduct.code))
                            .innerJoin(qCpProduct).on(qProduct.id.eq(qCpProduct.pid))
                            .innerJoin(qCp).on(qCpProduct.cpid.eq(qCp.id))
                            .where(qScd.opcode.eq(code)).fetch();

                    List<OrderProductDimensionDTO> result = new ArrayList<>();
                    for (OrderProductDimensionDTO dto : fetch){
                        dto.setDimensionCode(byCode.getSdcode());
                        result.add(dto);
                    }
                    return result;
                }else {
                    QCpProduct qCpProduct = QCpProduct.cpProduct; //产品和cp关系表
                    QOrderProductWithSCD qScd = QOrderProductWithSCD.orderProductWithSCD;
                    QCp qCp = QCp.cp; //cp表
                    QProduct qProduct = QProduct.product; //产品表
                    List<OrderProductDimensionListDTO> fetch = queryFactory.select(Projections.bean(
                            OrderProductDimensionListDTO.class,
                            qCp.code.as("cpcode"),
                            qCp.name.as("cpname"),
                            qProduct.code.as("pcode"),
                            qProduct.name.as("pname")
                    )).from(qScd).innerJoin(qProduct).on(qScd.pcode.eq(qProduct.code))
                            .innerJoin(qCpProduct).on(qProduct.id.eq(qCpProduct.pid))
                            .innerJoin(qCp).on(qCpProduct.cpid.eq(qCp.id))
                            .where(qScd.opcode.eq(code)).fetch();

                    List<OrderProductDimensionListDTO> result = new ArrayList<>();
                    //查询多维度下单维度
                    List<SettlementCombinatorialDimensionFrom> froms = settlementCombinatorialDimensionFromRepository.findByMasterCode(byCode.getScdcode().trim());
                    for (OrderProductDimensionListDTO dto : fetch){
                        dto.setDimensionACode(froms.get(0).getDim_code());
                        dto.setDimensionBCode(froms.get(1).getDim_code());
                        dto.setDimensionCCode(froms.get(2).getDim_code());
                        result.add(dto);
                    }
                    return result;
                }
            }else {
                return null;
            }
        }
        return null;
    }

    /**
     * 检查CP是否存在
     * @param dtos
     * @return
     */
    @Override
    public ResultVO<?> checkCp(List<CpOrderCpExcelDTO> dtos) {
        int i = 1;
        for (CpOrderCpExcelDTO dto : dtos){
            i += i;
            Cp cp = cpRepository.findByCode(dto.getCpcode().trim());
            if (null == cp){
                return ResultVOUtil.error("1","第" + i + "条数据，CP不存在!");
            }

            if (StringUtils.isBlank(dto.getQuantity())){
                return ResultVOUtil.error("1","第" + i + "条数据，订购量不能为空!");
            }
        }
        return ResultVOUtil.success();
    }

    /**
     * 检查CP和单维度是否存在
     * @param dtos
     * @return
     */
    @Override
    public ResultVO<?> checkCpAndDimension(List<OrderProductDimensionDTO> dtos) {
        int i = 1;
        for (OrderProductDimensionDTO dto : dtos){
            i += i;
            Cp cp = cpRepository.findByCode(dto.getCpcode().trim());
            if (null == cp){
                return ResultVOUtil.error("1","第" + i + "条数据，CP不存在!");
            }
            Optional<SettlementDimension> byCode = settlementDimensionRepository.findByCode(dto.getDimensionCode().trim());
            if (!byCode.isPresent()){
                return ResultVOUtil.error("1","第" + i + "条数据，维度不存在!");
            }

            if (StringUtils.isBlank(dto.getNumber())){
                return ResultVOUtil.error("1","第" + i + "条数据，数量不能为空!");
            }

            if (StringUtils.isBlank(dto.getMoney())){
                return ResultVOUtil.error("1","第" + i + "条数据，结算金额不能为空!");
            }
        }
        return ResultVOUtil.success();
    }

    /**
     * 检查CP和多维度是否存在
     * @param dtos
     * @return
     */
    @Override
    public ResultVO<?> checkCpAndDimensionList(List<OrderProductDimensionListDTO> dtos) {
        int i = 1;
        for (OrderProductDimensionListDTO dto : dtos){
            i += i;
            Cp cp = cpRepository.findByCode(dto.getCpcode().trim());
            if (null == cp){
                return ResultVOUtil.error("1","第" + i + "条数据，CP不存在!");
            }
            Optional<SettlementDimension> byCode = settlementDimensionRepository.findByCode(dto.getDimensionACode().trim());
            if (!byCode.isPresent()){
                return ResultVOUtil.error("1","第" + i + "条数据，维度A不存在!");
            }

            Optional<SettlementDimension> byCode1 = settlementDimensionRepository.findByCode(dto.getDimensionBCode().trim());
            if (!byCode1.isPresent()){
                return ResultVOUtil.error("1","第" + i + "条数据，维度B不存在!");
            }

            Optional<SettlementDimension> byCode2 = settlementDimensionRepository.findByCode(dto.getDimensionCCode().trim());
            if (!byCode2.isPresent()){
                return ResultVOUtil.error("1","第" + i + "条数据，维度C不存在!");
            }

            if (StringUtils.isBlank(dto.getNumberA())){
                return ResultVOUtil.error("1","第" + i + "条数据，维度A数量不能为空!");
            }

            if (StringUtils.isBlank(dto.getNumberB())){
                return ResultVOUtil.error("1","第" + i + "条数据，维度B数量不能为空!");
            }

            if (StringUtils.isBlank(dto.getNumberC())){
                return ResultVOUtil.error("1","第" + i + "条数据，维度C数量不能为空!");
            }

            if (StringUtils.isBlank(dto.getMoney())){
                return ResultVOUtil.error("1","第" + i + "条数据，结算金额不能为空!");
            }
        }
        return ResultVOUtil.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> batchLogicDelete(String ids) {
        try{
            List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists){
                accountSettlementRepository.batchLogicDelete(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @Override
    public AccountSettlementAddVM findById(String id) {
        AccountSettlement comparison = accountSettlementRepository.findById(Integer.parseInt(id)).orElseThrow(
                () -> new IllegalArgumentException("未查询到信息")
        );
        AccountSettlementAddVM vm = new AccountSettlementAddVM();
        BeanUtils.copyProperties(comparison, vm);
        Timestamp startTime=  comparison.getSetStartTime();
        Timestamp endTime=  comparison.getSetEndTime();
        String strn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
        String strns = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
        vm.setStartTime(strn);
        vm.setEndTime(strns);
        if (1 == vm.getSet_type()) {   //1:订购量结算源数据
            List<SettlementOrder> settlementOrders = settlementOrderRepository.findByMasterCode(comparison.getCode());
            List<CpOrderCpAddVM> list = new ArrayList<>();
            String rulename = orderQuantityRepository.findByMastername(vm.getSet_ruleCode());
            Integer ruleid = orderQuantityRepository.findByMastid(vm.getSet_ruleCode());
            vm.getSet_ruleId(ruleid);
            vm.setSet_ruleName(rulename);
            for (SettlementOrder f : settlementOrders) {
                CpOrderCpAddVM s = new CpOrderCpAddVM();
                BeanUtils.copyProperties(f, s);
                String cpname = cpRepository.findByMasterCodes(f.getCpcode());
                BigDecimal ordermoney = settlementOrderRepository.findByMastermoney(vm.getCode());
                vm.setOrderMoney(ordermoney);
                s.setCpname(cpname);
                list.add(s);
                vm.setCpAddVMS(list);
            }
        } else if (2 == vm.getSet_type()) {   //2:业务级结算
            BigDecimal money = settlementMoneyRepository.findByMastermoney(comparison.getCode()); //通过code查询
            String rulename = orderBusinessRepository.findByMasterCodes(vm.getSet_ruleCode());
            Integer ruleid = orderBusinessRepository.findByMastid(vm.getSet_ruleCode());
            vm.setSet_ruleName(rulename);
            vm.setBusinessMoney(money);
            vm.getSet_ruleId(ruleid);

        } else if (3 == vm.getSet_type()) {   //3:产品级结算
            int mode = orderProductRepository.findBymode(comparison.getSet_ruleCode()); //通过规则code查询属于单维度还是多维度
            Integer ruleid = orderProductRepository.findByMastid(vm.getSet_ruleCode());
            vm.getSet_ruleId(ruleid);
            if (mode == 1) {//1:按单维度结算
                List<SettlementProductSingle> settlementProductSingles = settlementProductSingleRepository.findByMasterCode(comparison.getCode()); //通过code查询
                List<OrderProductDimensionAddVM> list = new ArrayList<>();
                for (SettlementProductSingle f : settlementProductSingles) {
                    OrderProductDimensionAddVM s = new OrderProductDimensionAddVM();
                    String cpname = cpRepository.findByMasterCodes(f.getCpcode());
                    s.setCpname(cpname);
                    BeanUtils.copyProperties(f, s);
                    list.add(s);
                    vm.setDimensionAddVM(list);
                }
            } else if (mode == 2) {//;2:多维度结算
                List<SettlementProductMany> settlementProductManies = settlementProductManyRepository.findByMasterCode(comparison.getCode()); //通过code查询
                List<OrderProductDimensionListAddVM> list = new ArrayList<>();
                for (SettlementProductMany f : settlementProductManies) {
                    OrderProductDimensionListAddVM s = new OrderProductDimensionListAddVM();
                    BeanUtils.copyProperties(f, s);
                    list.add(s);
                    vm.setDimensionListAddVMS(list);
                }
            }
        } else if (4 == vm.getSet_type()) {//4:CP定比例结算
            BigDecimal money = settlementMoneyRepository.findByMastermoney(comparison.getCode()); //通过code查询
                vm.setCpAllMoney(money);
        } else if (5 == vm.getSet_type()) { //5:业务定比例结算
            List<SettlementBusiness> settlementMonies = settlementBusinessRepository.findByMasterCode(comparison.getCode()); //通过code查询
            List<BusinessBelielAddVM> list = new ArrayList<>();
            String rulename = orderBusinessComparisonRepository.findByMasterCodes(vm.getSet_ruleCode());
            Integer ruleid = orderBusinessComparisonRepository.findByMastid(vm.getSet_ruleCode());
            vm.setSet_ruleName(rulename);
            vm.getSet_ruleId(ruleid);
            for (SettlementBusiness f : settlementMonies) {
                BusinessBelielAddVM s = new BusinessBelielAddVM();
                BeanUtils.copyProperties(f, s);
                String businessCode=vm.getSet_ruleCode();
                String businessname = orderBusinessComparisonRepository.findByMasterCodes(businessCode);
                s.setMoney(f.getBusinessMoney());
                s.setBusinessName(businessname);
                list.add(s);
                vm.setBelielAddVMS(list);
            }
        }
        return vm;
    }

    @Override
    public Page<AccountSettlementAddVM> findByConditions(String name, String code, String status, Pageable pageable) {
        Page<AccountSettlementAddVM> map = accountSettlementRepository.findAll(((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)) {
                Predicate condition = builder.like(root.get("name"), "%"+name+"%");
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(code)) {
                Predicate condition = builder.like(root.get("code"), "%"+code+"%");
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(status)) {
                Predicate condition = builder.equal(root.get("status"), Integer.parseInt(status));
                predicates.add(condition);
            }

            Predicate condition = builder.equal(root.get("isdelete"), 0);
            predicates.add(condition);

            if (!predicates.isEmpty()){
                return builder.and(predicates.toArray(new Predicate[0]));
            }
            return builder.conjunction();
        }), pageable).map(accountSettlementAssemlber::getListVM);
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> updateAccountSet(AccountSettlementAddVM vm) {
        /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算 */
        try{
            //查询分账结算信息
            QAccountSettlement qAccountSettlement = QAccountSettlement.accountSettlement;
            AccountSettlement accountSettlement = queryFactory.selectFrom(qAccountSettlement)
                    .where(qAccountSettlement.id.eq(vm.getId())).fetchOne();

            UpdateTool.copyNullProperties(vm,accountSettlement);
            accountSettlement.setModifyTime(new Timestamp(System.currentTimeMillis()));
            if (StringUtils.isNotBlank(vm.getStartTime())){
                accountSettlement.setSetStartTime(Timestamp.valueOf(vm.getStartTime()));
            }
            if (StringUtils.isNotBlank(vm.getEndTime())){
                accountSettlement.setSetEndTime(Timestamp.valueOf(vm.getEndTime()));
            }
            accountSettlement.setStatus(1);
            AccountSettlement ac = accountSettlementRepository.saveAndFlush(accountSettlement);
            String code = ac.getCode();
            //1、先删除之前的订购量结算源数据再新增订购量结算源数据
            if (1 == vm.getSet_type()){
                settlementOrderRepository.deleteByMasterCode(code.trim());
                List<CpOrderCpAddVM> cpAddVMS = vm.getCpAddVMS();
                for (CpOrderCpAddVM addVM : cpAddVMS){
                    SettlementOrder order = new SettlementOrder();
                    BeanUtils.copyProperties(addVM,order);
                    order.setMasterCode(code);
                    order.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    order.setOrderMoney(vm.getOrderMoney());
                    settlementOrderRepository.save(order);
                }
            }else if (2 == vm.getSet_type()){
                //2、先删除业务级结算源数据再新增业务级结算源数据
                settlementMoneyRepository.deleteByMasterCode(code);
                SettlementMoney money = new SettlementMoney();
                money.setMasterCode(code);
                money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                money.setType(0);
                money.setMoney(vm.getBusinessMoney());
                settlementMoneyRepository.save(money);
            }else if (3 == vm.getSet_type()){
                //3、先删除再新增产品级结算结算源数据
                //单维度
                if (null != vm.getDimensionAddVM() && !vm.getDimensionAddVM().isEmpty()){
                    settlementProductSingleRepository.deleteByMasterCode(code);

                    List<OrderProductDimensionAddVM> dimensionAddVM = vm.getDimensionAddVM();
                    for (OrderProductDimensionAddVM addVM : dimensionAddVM){
                        SettlementProductSingle single = new SettlementProductSingle();
                        BeanUtils.copyProperties(addVM,single);
                        single.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        single.setMasterCode(code);
                        settlementProductSingleRepository.save(single);
                    }
                }else if (null != vm.getDimensionListAddVMS() && !vm.getDimensionListAddVMS().isEmpty()){
                    //先删除之前数据
                    settlementProductManyRepository.deleteByMasterCode(code);

                    List<OrderProductDimensionListAddVM> listAddVMS = vm.getDimensionListAddVMS();
                    for (OrderProductDimensionListAddVM listAddVM : listAddVMS){
                        SettlementProductMany many = new SettlementProductMany();
                        BeanUtils.copyProperties(listAddVM,many);
                        many.setMasterCode(code);
                        many.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        settlementProductManyRepository.save(many);
                    }
                }
            }else if (4 == vm.getSet_type()){
                //查询是按金额结算还是比列结算
                QOrderCp qOrderCp = QOrderCp.orderCp;
                OrderCp orderCp = queryFactory.selectFrom(qOrderCp)
                        .where(qOrderCp.code.eq(vm.getSet_ruleCode().trim())).fetchFirst();

                //先删除之前的源数据在新增
                settlementMoneyRepository.deleteByMasterCode(code);
                if (0 == orderCp.getSettleaccounts()){
                    SettlementMoney money = new SettlementMoney();
                    money.setMasterCode(code);
                    money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    money.setType(1);
                    money.setMoney(vm.getCpAllMoney());
                    settlementMoneyRepository.save(money);
                }else if (1 == orderCp.getSettleaccounts()){
                    SettlementMoney money = new SettlementMoney();
                    money.setMasterCode(code);
                    money.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    money.setType(2);
                    money.setMoney(vm.getCpAllMoney());
                    settlementMoneyRepository.save(money);
                }
            }else if (5 == vm.getSet_type()){
                //判断是按金额结算还是比列结算
                QOrderBusinessComparison qOrderBusinessComparison = QOrderBusinessComparison.orderBusinessComparison;
                OrderBusinessComparison comparison = queryFactory.selectFrom(qOrderBusinessComparison)
                        .where(qOrderBusinessComparison.code.eq(vm.getSet_ruleCode().trim())).fetchFirst();

                //先删除再新增
                settlementBusinessRepository.deleteByMasterCode(code);
                //1:比例结算；2:金额结算
                if (1 == comparison.getMode()){
                    List<BusinessBelielAddVM> belielAddVMS = vm.getBelielAddVMS();
                    for (BusinessBelielAddVM addVM : belielAddVMS){
                        SettlementBusiness business = new SettlementBusiness();
                        BeanUtils.copyProperties(addVM,business);
                        business.setType(1);
                        business.setMasterCode(code);
                        business.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        business.setBusinessMoney(addVM.getMoney());
                        settlementBusinessRepository.save(business);
                    }
                }else if (2 == comparison.getMode()){
                    List<BusinessBelielAddVM> belielAddVMS = vm.getBelielAddVMS();
                    for (BusinessBelielAddVM addVM : belielAddVMS){
                        SettlementBusiness business = new SettlementBusiness();
                        BeanUtils.copyProperties(addVM,business);
                        business.setType(2);
                        business.setMasterCode(code);
                        business.setBusinessMoney(addVM.getMoney());
                        business.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        settlementBusinessRepository.save(business);
                    }
                }
            }

            //最后删除已经结算好的结算数据，重新结算后才能生成结算数据
            cpSettlementMoneyRepository.deleteByMasterCode(code);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","系统内部错误");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

}
