package com.hgys.iptv.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.hgys.iptv.controller.assemlber.SettlementDocumentControllerAssemlber;
import com.hgys.iptv.controller.vm.SettlementDocumentCPListExcelVM;
import com.hgys.iptv.controller.vm.SettlementDocumentCPListVM;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.QAccountSettlement;
import com.hgys.iptv.model.QCpSettlementMoney;
import com.hgys.iptv.model.vo.CpSettlementInfoExcelDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AccountSettlementRepository;
import com.hgys.iptv.repository.CpSettlementMoneyRepository;
import com.hgys.iptv.repository.SettlementDocumentRepository;
import com.hgys.iptv.service.SettlementDocumentService;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.excel.ExcelForWebUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SettlementDocumentServiceImpl implements SettlementDocumentService {

    @Autowired
    private SettlementDocumentRepository settlementDocumentRepository;

    @Autowired
    private SettlementDocumentControllerAssemlber settlementDocumentControllerAssemlber;

    @Autowired
    private CpSettlementMoneyRepository cpSettlementMoneyRepository;

    @Autowired
    private AccountSettlementRepository accountSettlementRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    /**
     * 查询结算文档列表信息
     * @param name
     * @param code
     * @param pageable
     * @return
     */
    @Override
    public Page<SettlementDocumentQueryListVM> findByConditions(String name, String code, Pageable pageable) {
       return settlementDocumentRepository.findAll(((root, query, builder) -> {
           List<Predicate> predicates = new ArrayList<>();
           if (StringUtils.isNotBlank(name)){
               Predicate condition = builder.like(root.get("name"), "%"+name+"%");
               predicates.add(condition);
           }
           if (StringUtils.isNotBlank(code)){
               Predicate condition = builder.like(root.get("code"), "%"+code+"%");
               predicates.add(condition);
           }

           /** 1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回*/
           Predicate condition = builder.notEqual(root.get("status"), 1);
           predicates.add(condition);

           if (!predicates.isEmpty()){
               return builder.and(predicates.toArray(new Predicate[0]));
           }
           return builder.conjunction();
        }),pageable).map(settlementDocumentControllerAssemlber :: getListVM);
    }

    @Override
    public Page<SettlementDocumentCPListExcelVM> documentQueryHistoryCpMySelfList(Integer masterId,String cpCode,String pageNum, String pageSize) {
        QAccountSettlement qAccountSettlement = QAccountSettlement.accountSettlement;
        QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;
        //查询分账结算信息
        AccountSettlement accountSettlement = jpaQueryFactory.selectFrom(qAccountSettlement).where(qAccountSettlement.id.eq(masterId)).fetchOne();
        QueryResults<SettlementDocumentCPListExcelVM> fetch = jpaQueryFactory.select(Projections.bean(
                SettlementDocumentCPListExcelVM.class,
                qAccountSettlement.id.as("masterId"),
                qAccountSettlement.setStartTime,
                qAccountSettlement.setEndTime,
                qAccountSettlement.status,
                qCpSettlementMoney.id,
                qCpSettlementMoney.masterCode,
                qCpSettlementMoney.masterName,
                qCpSettlementMoney.cpcode,
                qCpSettlementMoney.cpname,
                qCpSettlementMoney.productCode,
                qCpSettlementMoney.productName,
                qCpSettlementMoney.businessCode,
                qCpSettlementMoney.businessName,
                qCpSettlementMoney.settlementMoney,
                qCpSettlementMoney.createTime
        )).from(qCpSettlementMoney)
                .innerJoin(qAccountSettlement).on(qCpSettlementMoney.masterCode.eq(qAccountSettlement.code))
                .where(qCpSettlementMoney.cpcode.eq(cpCode))
                .where(qAccountSettlement.setStartTime.lt(accountSettlement.getSetStartTime()))
                .orderBy(qAccountSettlement.setStartTime.desc())
                .offset(Integer.parseInt(pageNum) - 1).limit(Integer.parseInt(pageSize)).fetchResults();

        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) - 1 ,Integer.parseInt(pageSize));
        Page<SettlementDocumentCPListExcelVM> pageImpianto = new PageImpl<>(fetch.getResults(), pageable, fetch.getTotal());
        return pageImpianto;
    }

    @Override
    public Page<SettlementDocumentQueryListVM> documentHistoryQueryCpList(Integer masterId, String pageNum, String pageSize) {
        Optional<AccountSettlement> byId = accountSettlementRepository.findById(masterId);
        AccountSettlement accountSettlement = byId.get();
        //查询当前账期以前账期的数据
        QAccountSettlement qAccountSettlement = QAccountSettlement.accountSettlement;

        QueryResults<AccountSettlement> fetch = jpaQueryFactory.selectFrom(qAccountSettlement)
                .where(qAccountSettlement.setStartTime.lt(accountSettlement.getSetStartTime()))
                .orderBy(qAccountSettlement.setStartTime.desc())
                .offset(Integer.parseInt(pageNum) - 1).limit(Integer.parseInt(pageSize)).fetchResults();

        List<AccountSettlement> list =fetch.getResults();
        List<SettlementDocumentQueryListVM> vms = new ArrayList<>();
        for (AccountSettlement a : list){
            SettlementDocumentQueryListVM s = new SettlementDocumentQueryListVM();
            BeanUtils.copyProperties(accountSettlement,s);
            //查询该分账结算下所有的cp信息
            List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findByMasterCode(a.getCode());
            List<SettlementDocumentCPListVM> listVMS = new ArrayList<>();
            for (CpSettlementMoney cp : byMasterCode){
                SettlementDocumentCPListVM vm = new SettlementDocumentCPListVM();
                BeanUtils.copyProperties(cp,vm);
                listVMS.add(vm);
            }
            s.setCpList(listVMS);
            vms.add(s);
        }
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) - 1 ,Integer.parseInt(pageSize));
        Page<SettlementDocumentQueryListVM> pageImpianto = new PageImpl<>(vms, pageable, fetch.getTotal());
        return pageImpianto;
    }


    @Override
    public ResultVO<?> findByIdQueryCpList(Integer id) {
        //查询分账结算信息
        Optional<AccountSettlement> byId = accountSettlementRepository.findById(id);
        AccountSettlement accountSettlement = byId.get();
        SettlementDocumentQueryListVM s = new SettlementDocumentQueryListVM();
        BeanUtils.copyProperties(accountSettlement,s);
        //查询该分账结算下所有的cp信息
        List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findByMasterCode(accountSettlement.getCode());
        List<SettlementDocumentCPListVM> vms = new ArrayList<>();
        for (CpSettlementMoney cp : byMasterCode){
            SettlementDocumentCPListVM vm = new SettlementDocumentCPListVM();
            BeanUtils.copyProperties(cp,vm);
            vms.add(vm);
        }
        s.setCpList(vms);

        return ResultVOUtil.success(s);
    }

    @Override
    public ResultVO<?> settlementDocumentQueryCpMySelfList(Integer id) {
        QAccountSettlement qAccountSettlement = QAccountSettlement.accountSettlement;
        QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;
        SettlementDocumentCPListExcelVM vm = jpaQueryFactory.select(Projections.bean(
                SettlementDocumentCPListExcelVM.class,
                qAccountSettlement.id.as("masterId"),
                qAccountSettlement.setStartTime,
                qAccountSettlement.setEndTime,
                qAccountSettlement.status,
                qCpSettlementMoney.id,
                qCpSettlementMoney.masterCode,
                qCpSettlementMoney.masterName,
                qCpSettlementMoney.cpcode,
                qCpSettlementMoney.cpname,
                qCpSettlementMoney.productCode,
                qCpSettlementMoney.productName,
                qCpSettlementMoney.businessCode,
                qCpSettlementMoney.businessName,
                qCpSettlementMoney.settlementMoney,
                qCpSettlementMoney.createTime
        )).from(qCpSettlementMoney)
                .innerJoin(qAccountSettlement).on(qCpSettlementMoney.masterCode.eq(qAccountSettlement.code))
                .where(qCpSettlementMoney.id.eq(id)).fetchOne();
        return ResultVOUtil.success(vm);
    }

    @Override
    public SettlementDocumentCPListExcelVM settlementCpExcel(Integer id) {
        QAccountSettlement qAccountSettlement = QAccountSettlement.accountSettlement;
        QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;

        SettlementDocumentCPListExcelVM vm = jpaQueryFactory.select(Projections.bean(
                SettlementDocumentCPListExcelVM.class,
                qAccountSettlement.id.as("masterId"),
                qAccountSettlement.setStartTime,
                qAccountSettlement.setEndTime,
                qAccountSettlement.status,
                qAccountSettlement.set_type.as("type"),
                qCpSettlementMoney.id,
                qCpSettlementMoney.masterCode,
                qCpSettlementMoney.masterName,
                qCpSettlementMoney.cpcode,
                qCpSettlementMoney.cpname,
                qCpSettlementMoney.productCode,
                qCpSettlementMoney.productName,
                qCpSettlementMoney.businessCode,
                qCpSettlementMoney.businessName,
                qCpSettlementMoney.settlementMoney,
                qCpSettlementMoney.createTime
        )).from(qCpSettlementMoney)
                .innerJoin(qAccountSettlement).on(qCpSettlementMoney.masterCode.eq(qAccountSettlement.code))
                .where(qCpSettlementMoney.id.eq(id)).fetchOne();
        return vm;
    }


    @Override
    public void excelSettlementInfo(Integer masterId, HttpServletResponse response) {
        //查询分账结算信息
        Optional<AccountSettlement> byId = accountSettlementRepository.findById(masterId);
        if (!byId.isPresent()){
            throw new IllegalArgumentException("未查询到该结算账单信息");
        }
        AccountSettlement accountSettlement = byId.get();
        /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定 */
        if (3 == accountSettlement.getSet_type()){
            try{
                List<ExcelExportEntity> colList = new ArrayList<>();
                ExcelExportEntity colEntity = new ExcelExportEntity("账期", "time");
                colEntity.setNeedMerge(true);
                colList.add(colEntity);

                colEntity = new ExcelExportEntity("CP", "cp");
                colEntity.setNeedMerge(true);
                colList.add(colEntity);

                ExcelExportEntity deliColGroup = new ExcelExportEntity("增值业务包", "zz");
                List<ExcelExportEntity> deliColList = new ArrayList<>();
                //查询该分账结算下所有的产品，然后动态设置表头
                QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;
                List<CpSettlementMoney> fetch = jpaQueryFactory.select(qCpSettlementMoney).from(qCpSettlementMoney)
                        .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                        .groupBy(qCpSettlementMoney.productCode).fetch();
                for (CpSettlementMoney c : fetch){
                    deliColList.add(new ExcelExportEntity(c.getProductName(), c.getProductCode()));
                    deliColGroup.setList(deliColList);
                }
                colList.add(deliColGroup);

                List<Map<String, Object>> list = new ArrayList<>();
                List<CpSettlementMoney> vms = jpaQueryFactory.selectFrom(qCpSettlementMoney)
                        .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                        .groupBy(qCpSettlementMoney.cpcode).fetch();
                //CP编码加产品编码作为键
                Map<String, CpSettlementMoney> collect = vms.stream().collect(Collectors.toMap(s -> s.getCpcode() + s.getProductCode(), settlementDocumentCPListExcelVM -> settlementDocumentCPListExcelVM));

                for(CpSettlementMoney excelVM : vms){
                    Map<String, Object> valMap = new HashMap<>();
                    //结算账期
                    String startTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetStartTime());
                    String endTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetEndTime());
                    valMap.put("time",startTime + "-" + endTime);
                    valMap.put("cp", excelVM.getCpname());

                    //通过masterCode和cp编码查询出当前Cp在该账单所有的产品
                    List<CpSettlementMoney> moneys = jpaQueryFactory.selectFrom(qCpSettlementMoney)
                            .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                            .where(qCpSettlementMoney.cpcode.eq(excelVM.getCpcode())).fetch();

                    List<Map<String, Object>> deliDetailList = new ArrayList<>();
                    Map<String, Object> deliValMap = new HashMap<>();
                    for(CpSettlementMoney cp : moneys){
                        deliValMap.put(cp.getProductCode(),cp.getSettlementMoney());
                    }
                    deliDetailList.add(deliValMap);
                    valMap.put("zz", deliDetailList);

                    list.add(valMap);
                }
                //结算合计金额
                BigDecimal decimal = cpSettlementMoneyRepository.jsAllmoney(accountSettlement.getCode());
                if (null == decimal){
                    decimal = new BigDecimal(0);
                }
                ExportParams exportParams = new ExportParams("甘肃广电IPTV业务结算报表", "产品级结算");
                exportParams.setSecondTitle("合计金额:" + decimal.setScale(2).toString());
                Workbook workbook = cn.afterturn.easypoi.excel.ExcelExportUtil.exportExcel(exportParams, colList,
                        list);
                ExcelForWebUtil.workBookExportExcel(response,workbook,"产品级账单结算报表");

            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (1 == accountSettlement.getSet_type() || 4 == accountSettlement.getSet_type()){
            String startTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetStartTime());
            String endTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetEndTime());
            String timeHead = "结算账期" + startTime + "至" + endTime;
            //查询数据
            List<CpSettlementMoney> byMasterCode = cpSettlementMoneyRepository.findByMasterCode(accountSettlement.getCode());
            List<CpSettlementInfoExcelDTO> dtos = new ArrayList<>();

            //分成合计金额
            BigDecimal decimal = new BigDecimal(0);
            for (CpSettlementMoney m : byMasterCode){
                CpSettlementInfoExcelDTO dto = new CpSettlementInfoExcelDTO();
                dto.setCp(m.getCpname());
                dto.setMoney(m.getSettlementMoney().toString());
                dtos.add(dto);
                //计算分成合计金额
                decimal = decimal.add(m.getSettlementMoney());
            }

            CpSettlementInfoExcelDTO l = new CpSettlementInfoExcelDTO();
            l.setCp("分成合计(元)");
            l.setMoney(decimal.setScale(2).toString());
            //最后一列插入分成合计金额
            dtos.add(l);

            Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams(timeHead,"订购量结算&CP定比例结算"), CpSettlementInfoExcelDTO.class, dtos);
            ExcelForWebUtil.workBookExportExcel(response,sheets,"Cp结算账单结算信息表");
        }else if (2 == accountSettlement.getSet_type() || 5 == accountSettlement.getSet_type()){
            try{
                List<ExcelExportEntity> colList = new ArrayList<>();
                ExcelExportEntity colEntity = new ExcelExportEntity("账期", "time");
                colEntity.setNeedMerge(true);
                colList.add(colEntity);

                colEntity = new ExcelExportEntity("CP", "cp");
                colEntity.setNeedMerge(true);
                colList.add(colEntity);

                ExcelExportEntity deliColGroup = new ExcelExportEntity("业务", "yw");
                List<ExcelExportEntity> deliColList = new ArrayList<>();
                //查询该分账结算下所有的业务，然后动态设置表头
                QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;
                List<CpSettlementMoney> fetch = jpaQueryFactory.select(qCpSettlementMoney).from(qCpSettlementMoney)
                        .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                        .groupBy(qCpSettlementMoney.businessCode).fetch();
                for (CpSettlementMoney c : fetch){
                    deliColList.add(new ExcelExportEntity(c.getBusinessName(), c.getBusinessCode()));
                    deliColGroup.setList(deliColList);
                }
                colList.add(deliColGroup);

                List<Map<String, Object>> list = new ArrayList<>();
                List<CpSettlementMoney> vms = jpaQueryFactory.selectFrom(qCpSettlementMoney)
                        .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                        .groupBy(qCpSettlementMoney.cpcode).fetch();
                //CP编码加产品编码作为键
                Map<String, CpSettlementMoney> collect = vms.stream().collect(Collectors.toMap(s -> s.getCpcode() + s.getProductCode(), settlementDocumentCPListExcelVM -> settlementDocumentCPListExcelVM));

                for(CpSettlementMoney excelVM : vms){
                    Map<String, Object> valMap = new HashMap<>();
                    //结算账期
                    String startTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetStartTime());
                    String endTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetEndTime());
                    valMap.put("time",startTime + "-" + endTime);
                    valMap.put("cp", excelVM.getCpname());

                    //通过masterCode和cp编码查询出当前Cp在该账单所有的业务
                    List<CpSettlementMoney> moneys = jpaQueryFactory.selectFrom(qCpSettlementMoney)
                            .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                            .where(qCpSettlementMoney.cpcode.eq(excelVM.getCpcode())).fetch();

                    List<Map<String, Object>> deliDetailList = new ArrayList<>();
                    Map<String, Object> deliValMap = new HashMap<>();
                    for(CpSettlementMoney cp : moneys){
                        deliValMap.put(cp.getBusinessCode(),cp.getSettlementMoney());
                    }
                    deliDetailList.add(deliValMap);
                    valMap.put("yw", deliDetailList);

                    list.add(valMap);
                }
                //结算合计金额
                BigDecimal decimal = cpSettlementMoneyRepository.jsAllmoney(accountSettlement.getCode());
                if (null == decimal){
                    decimal = new BigDecimal(0);
                }
                ExportParams exportParams = new ExportParams("甘肃广电IPTV业务结算报表（移动侧）", "业务级结算&业务定比列结算");
                exportParams.setSecondTitle("合计金额:" + decimal.setScale(2).toString());
                Workbook workbook = cn.afterturn.easypoi.excel.ExcelExportUtil.exportExcel(exportParams, colList,
                        list);
                ExcelForWebUtil.workBookExportExcel(response,workbook,"业务级级账单结算报表");

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void excelSettlementInfo1(Integer masterId, HttpServletResponse response) {
        //查询分账结算信息
        Optional<AccountSettlement> byId = accountSettlementRepository.findById(masterId);
        if (!byId.isPresent()){
            throw new IllegalArgumentException("未查询到该结算账单信息");
        }
        AccountSettlement accountSettlement = byId.get();
        /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定 */
        if (3 == accountSettlement.getSet_type()){
            try{
                //查询该结算账单详细信息
                List<CpSettlementMoney> list = cpSettlementMoneyRepository.findByMasterCode(accountSettlement.getCode());

                String startTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetStartTime());
                String endTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetEndTime());
                String timeHead = startTime + "至" + endTime;
                QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;
                //查询该结算订单所有的cp信息
                List<CpSettlementMoney> moneyList =  jpaQueryFactory.selectFrom(qCpSettlementMoney)
                        .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                        .groupBy(qCpSettlementMoney.cpcode).fetch();

                Map<String, Object> beanParams = new HashMap<>();
                List<Map> l = new ArrayList<>();
                for (CpSettlementMoney c : moneyList){
                    //查询该结算订单Cp结算的所有产品
                    List<CpSettlementMoney> fetch = jpaQueryFactory.selectFrom(qCpSettlementMoney)
                            .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                            .where(qCpSettlementMoney.cpcode.eq(c.getCpcode())).fetch();

                    Map<String,Object> a = new HashMap<>();
                    a.put("cpName",c.getCpname());
                    for (CpSettlementMoney money : fetch){
                        //设置各产品金额
                        if ("4K花园".equals(money.getProductName().trim())){
                            a.put("product1",money.getSettlementMoney());
                        }else if ("电竞世界".equals(money.getProductName().trim())){
                            a.put("product2",money.getSettlementMoney());
                        }else if ("动漫星空".equals(money.getProductName().trim())){
                            a.put("product3",money.getSettlementMoney());
                        }else if ("欢乐歌房".equals(money.getProductName().trim())){
                            a.put("product4",money.getSettlementMoney());
                        }else if ("黄冈学霸".equals(money.getProductName().trim())){
                            a.put("product5",money.getSettlementMoney());
                        }else if ("健身达人".equals(money.getProductName().trim())){
                            a.put("product6",money.getSettlementMoney());
                        }else if ("亲子乐园".equals(money.getProductName().trim())){
                            a.put("product7",money.getSettlementMoney());
                        }else if ("游戏王国".equals(money.getProductName().trim())){
                            a.put("product8",money.getSettlementMoney());
                        }else if ("院线大片".equals(money.getProductName().trim())){
                            a.put("product9",money.getSettlementMoney());
                        }else if ("欢乐屋".equals(money.getProductName().trim())){
                            a.put("product10",money.getSettlementMoney());
                        }else if ("热播剧场".equals(money.getProductName().trim())){
                            a.put("product11",money.getSettlementMoney());
                        }else if ("综艺娱乐".equals(money.getProductName().trim())){
                            a.put("product12",money.getSettlementMoney());
                        }

                    }
                    BigDecimal decimal = cpSettlementMoneyRepository.jsAllmoneyByMasterCodeAndCpcode(accountSettlement.getCode(), c.getCpcode());
                    a.put("productAllMoney",decimal);
                    l.add(a);

                }

                beanParams.put("pList",l);
                beanParams.put("timeHead",timeHead);
                //计算合计金额
                BigDecimal allMoney = new BigDecimal(0);

                for (CpSettlementMoney money : list){
                    allMoney = allMoney.add(money.getSettlementMoney());
                }
                beanParams.put("allMoney",allMoney);
                //当前时间（制表时间）
                String nowTime = new SimpleDateFormat("yyyy年MM月dd日").format(new Timestamp(System.currentTimeMillis()));
                beanParams.put("nowTime",nowTime);
                //计算产品结算总金额
                List<CpSettlementMoney> fetch = jpaQueryFactory.selectFrom(qCpSettlementMoney)
                        .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                        .groupBy(qCpSettlementMoney.productCode).fetch();
                for (CpSettlementMoney money : fetch){
                    BigDecimal decimal = cpSettlementMoneyRepository.jsAllmoneyByMasterCodeAndProductCode(accountSettlement.getCode(), money.getProductCode());
                    //设置各产品金额
                    if ("4K花园".equals(money.getProductName().trim())){
                        beanParams.put("product1",decimal);
                    }else if ("电竞世界".equals(money.getProductName().trim())){
                        beanParams.put("product2",decimal);
                    }else if ("动漫星空".equals(money.getProductName().trim())){
                        beanParams.put("product3",decimal);
                    }else if ("欢乐歌房".equals(money.getProductName().trim())){
                        beanParams.put("product4",decimal);
                    }else if ("黄冈学霸".equals(money.getProductName().trim())){
                        beanParams.put("product5",decimal);
                    }else if ("健身达人".equals(money.getProductName().trim())){
                        beanParams.put("product6",decimal);
                    }else if ("亲子乐园".equals(money.getProductName().trim())){
                        beanParams.put("product7",decimal);
                    }else if ("游戏王国".equals(money.getProductName().trim())){
                        beanParams.put("product8",decimal);
                    }else if ("院线大片".equals(money.getProductName().trim())){
                        beanParams.put("product9",decimal);
                    }else if ("欢乐屋".equals(money.getProductName().trim())){
                        beanParams.put("product10",decimal);
                    }else if ("热播剧场".equals(money.getProductName().trim())){
                        beanParams.put("product11",decimal);
                    }else if ("综艺娱乐".equals(money.getProductName().trim())){
                        beanParams.put("product12",decimal);
                    }
                }

                ExcelForWebUtil.exportExcelLiunx(response,beanParams,"ProductLevel.xlsx","产品级结算");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (1 == accountSettlement.getSet_type() || 4 == accountSettlement.getSet_type()){
            String startTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetStartTime());
            String endTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetEndTime());
            String timeHead = startTime + "至" + endTime;
            //查询数据
            List<CpSettlementMoney> monies = cpSettlementMoneyRepository.findByMasterCode(accountSettlement.getCode());

            Map<String, Object> beanParams = new HashMap<>();
            List<Map> l = new ArrayList<>();
            for (CpSettlementMoney money : monies){
                Map<String,String> a = new HashMap<>();
                a.put("cpName",money.getCpname());
                a.put("money",money.getSettlementMoney().toString());
                l.add(a);
            }
            beanParams.put("pList",l);
            beanParams.put("timeHead",timeHead);
            //计算合计金额
            BigDecimal allMoney = new BigDecimal(0);
            for (CpSettlementMoney money : monies){
                allMoney = allMoney.add(money.getSettlementMoney());
            }
            beanParams.put("allMoney",allMoney);
            //当前时间（制表时间）
            String nowTime = new SimpleDateFormat("yyyy年MM月dd日").format(new Timestamp(System.currentTimeMillis()));
            beanParams.put("nowTime",nowTime);
            ExcelForWebUtil.exportExcelLiunx(response,beanParams,"OrderAndProportion.xlsx","订购量结算&CP定比例结算");
        }else if (2 == accountSettlement.getSet_type() || 5 == accountSettlement.getSet_type()){
            try{
                //查询该结算账单详细信息
                List<CpSettlementMoney> list = cpSettlementMoneyRepository.findByMasterCode(accountSettlement.getCode());

                String startTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetStartTime());
                String endTime = new SimpleDateFormat("yyyy-MM-dd").format(accountSettlement.getSetEndTime());
                String timeHead = startTime + "至" + endTime;
                QCpSettlementMoney qCpSettlementMoney = QCpSettlementMoney.cpSettlementMoney;
                //查询该结算订单所有的cp信息
                List<CpSettlementMoney> moneyList =  jpaQueryFactory.selectFrom(qCpSettlementMoney)
                        .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                        .groupBy(qCpSettlementMoney.cpcode).fetch();

                Map<String, Object> beanParams = new HashMap<>();
                List<Map> l = new ArrayList<>();
                for (CpSettlementMoney c : moneyList){
                    //查询该结算订单Cp结算的所有业务
                    List<CpSettlementMoney> fetch = jpaQueryFactory.selectFrom(qCpSettlementMoney)
                            .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                            .where(qCpSettlementMoney.cpcode.eq(c.getCpcode())).fetch();

                    Map<String,Object> a = new HashMap<>();
                    a.put("cpName",c.getCpname());
                    for (CpSettlementMoney money : fetch){
                        //设置各业务金额
                        if ("教育".equals(money.getBusinessName().trim())){
                            a.put("business1",money.getSettlementMoney());
                        }else if ("少儿".equals(money.getBusinessName().trim())){
                            a.put("business2",money.getSettlementMoney());
                        }else if ("影视".equals(money.getBusinessName().trim())){
                            a.put("business3",money.getSettlementMoney());
                        }else if ("生活".equals(money.getBusinessName().trim())){
                            a.put("business4",money.getSettlementMoney());
                        }else if ("电竞".equals(money.getBusinessName().trim())){
                            a.put("business5",money.getSettlementMoney());
                        }else if ("娱乐".equals(money.getBusinessName().trim())){
                            a.put("business6",money.getSettlementMoney());
                        }else if ("体育".equals(money.getBusinessName().trim())){
                            a.put("business7",money.getSettlementMoney());
                        }

                    }
                    BigDecimal decimal = cpSettlementMoneyRepository.jsAllmoneyByMasterCodeAndCpcode(accountSettlement.getCode(), c.getCpcode());
                    a.put("businessAllMoney",decimal);
                    l.add(a);

                }

                beanParams.put("pList",l);
                beanParams.put("timeHead",timeHead);
                //计算合计金额
                BigDecimal allMoney = new BigDecimal(0);

                for (CpSettlementMoney money : list){
                    allMoney = allMoney.add(money.getSettlementMoney());
                }
                beanParams.put("allMoney",allMoney);
                //当前时间（制表时间）
                String nowTime = new SimpleDateFormat("yyyy年MM月dd日").format(new Timestamp(System.currentTimeMillis()));
                beanParams.put("nowTime",nowTime);
                //计算业务结算总金额
                List<CpSettlementMoney> fetch = jpaQueryFactory.selectFrom(qCpSettlementMoney)
                        .where(qCpSettlementMoney.masterCode.eq(accountSettlement.getCode()))
                        .groupBy(qCpSettlementMoney.businessCode).fetch();
                for (CpSettlementMoney money : fetch){
                    BigDecimal decimal = cpSettlementMoneyRepository.jsAllmoneyByMasterCodeAndBusinessCode(accountSettlement.getCode(), money.getBusinessCode());
                    //设置各业务结算总金额
                    if ("教育".equals(money.getBusinessName().trim())){
                        beanParams.put("business1",decimal);
                    }else if ("少儿".equals(money.getBusinessName().trim())){
                        beanParams.put("business2",decimal);
                    }else if ("影视".equals(money.getBusinessName().trim())){
                        beanParams.put("business3",decimal);
                    }else if ("生活".equals(money.getBusinessName().trim())){
                        beanParams.put("business4",decimal);
                    }else if ("电竞".equals(money.getBusinessName().trim())){
                        beanParams.put("business5",decimal);
                    }else if ("娱乐".equals(money.getBusinessName().trim())){
                        beanParams.put("business6",decimal);
                    }else if ("体育".equals(money.getBusinessName().trim())){
                        beanParams.put("business7",decimal);
                    }
                }

                ExcelForWebUtil.exportExcelLiunx(response,beanParams,"BusinessLevel.xlsx","业务级结算&业务定比列");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
