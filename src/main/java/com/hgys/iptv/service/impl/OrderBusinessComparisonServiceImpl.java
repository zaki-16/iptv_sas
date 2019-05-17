package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.OrderBusinessComparisonControllerAssemlber;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddListVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonBusinessAddVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.BusinessComparisonRelation;
import com.hgys.iptv.model.CpOrderBusinessComparison;
import com.hgys.iptv.model.OrderBusinessComparison;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.qmodel.QBusinessComparisonRelation;
import com.hgys.iptv.model.qmodel.QCpOrderBusinessComparison;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.OrderBusinessComparisonService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderBusinessComparisonServiceImpl implements OrderBusinessComparisonService {

    @Autowired
    private OrderBusinessComparisonRepository orderBusinessComparisonRepository;

    @Autowired
    private CpOrderBusinessComparisonRepository cpOrderBusinessComparisonRepository;

    @Autowired
    private OrderBusinessComparisonControllerAssemlber orderBusinessComparisonControllerAssemlber;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private BusinessComparisonRelationRepository businessComparisonRelationRepository;

    @Autowired
    private CpRepository cpRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    /**
     * 新增
     * @param vm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> addOrderBusinessComparison(OrderBusinessComparisonAddVM vm) {
        try{
            //信息校验
            if (StringUtils.isBlank(vm.getName())){
                return ResultVOUtil.error("1","业务定比例名称不能为空");
            }else if (vm.getList().isEmpty()){
                return ResultVOUtil.error("1","业务定比例业务编码不能为空");
            }else if (vm.getList().get(0).getList().isEmpty()){
                return ResultVOUtil.error("1","业务定比例CP信息选择不能为空");
            }else if (null == vm.getMode()){
                return ResultVOUtil.error("1","业务定比例结算方式不能为空");
            }else if (null == vm.getStatus()){
                return ResultVOUtil.error("1","业务定比例状态不能为空");
            }

            List<OrderBusinessComparisonBusinessAddVM> businessAddVMS = vm.getList();
            for (OrderBusinessComparisonBusinessAddVM v : businessAddVMS){
                if (StringUtils.isBlank(v.getBusinessCode())){
                    return ResultVOUtil.error("1","业务编码不能为空");
                }
                List<OrderBusinessComparisonAddListVM> list = v.getList();

                for (OrderBusinessComparisonAddListVM m : list){
                    if (1 == vm.getMode()){
                        if (null == m.getProportion()){
                            return ResultVOUtil.error("1","CP所在比例不能为空");
                        }
                    }else if (2 == vm.getMode()){
                        if (null == m.getMoney()){
                            return ResultVOUtil.error("1","CP分配金额不能为空");
                        }
                    }
                }
            }

            //验证业务下所有CP分批比例是否大于100%
            Integer he = 0;
            for (OrderBusinessComparisonBusinessAddVM v : businessAddVMS){
                List<OrderBusinessComparisonAddListVM> listVMS = v.getList();
                for (OrderBusinessComparisonAddListVM order : listVMS){
                    he = he + order.getProportion();
                    if (he > 100){
                        return ResultVOUtil.error("1","业务编码为："+v.getBusinessCode() + "下的所有CP所在比例不能大于100%");
                    }
                }

            }

            //新增主表信息
            OrderBusinessComparison comparison = new OrderBusinessComparison();
            String code = CodeUtil.getOnlyCode("OBC",5);
            BeanUtils.copyProperties(vm,comparison);
            comparison.setInputTime(new Timestamp(System.currentTimeMillis()));
            comparison.setIsdelete(0);
            comparison.setCode(code);
            orderBusinessComparisonRepository.save(comparison);

            //新增业务定比列与业务关系信息

            for (OrderBusinessComparisonBusinessAddVM v : businessAddVMS){
                BusinessComparisonRelation relation = new BusinessComparisonRelation();
                String bCode = CodeUtil.getOnlyCode("B",5);
                BeanUtils.copyProperties(v,relation);
                //查询业务
                Business busines = businessRepository.findByCode(v.getBusinessCode().trim());
                relation.setMasterCode(code);
                relation.setCode(bCode);
                relation.setMasterName(vm.getName());
                relation.setBusinessName(StringUtils.trimToEmpty(busines.getName()));
                relation.setCreate_time(new Timestamp(System.currentTimeMillis()));
                businessComparisonRelationRepository.save(relation);

                List<OrderBusinessComparisonAddListVM> list = v.getList();
                for (OrderBusinessComparisonAddListVM business : list){
                    CpOrderBusinessComparison compa = new CpOrderBusinessComparison();

                    BeanUtils.copyProperties(business,compa);
                    compa.setMasterCode(bCode);
                    compa.setCreate_time(new Timestamp(System.currentTimeMillis()));
                    compa.setCp_name(StringUtils.trimToEmpty(cpRepository.findByCode(business.getCp_code().trim()).getName()));

                    cpOrderBusinessComparisonRepository.save(compa);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> batchLogicDelete(String ids) {
        try{
            List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists){
                orderBusinessComparisonRepository.batchLogicDelete(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 通过code查询
     * @param code
     * @return
     */
    @Override
    public OrderBusinessComparisonQueryVM getOrderBusinessComparison(String code) {
        OrderBusinessComparison comparison = orderBusinessComparisonRepository.findByCode(code).orElseThrow(
                () -> new IllegalArgumentException("未查询到结算类型-业务定比例信息")
        );

        OrderBusinessComparisonQueryVM vm = new OrderBusinessComparisonQueryVM();
        BeanUtils.copyProperties(comparison,vm);

        //查询业务信息
        List<BusinessComparisonRelation> relations = businessComparisonRelationRepository.findByMasterCode(code);
        List<OrderBusinessComparisonBusinessAddVM> list = new ArrayList<>();
        for (BusinessComparisonRelation r : relations){
            OrderBusinessComparisonBusinessAddVM addVM = new OrderBusinessComparisonBusinessAddVM();
            BeanUtils.copyProperties(r,addVM);

            //查询业务下Cp
            List<CpOrderBusinessComparison> byMasterCode = cpOrderBusinessComparisonRepository.findByMasterCode(r.getCode());
            List<OrderBusinessComparisonAddListVM> vms = new ArrayList<>();
            for (CpOrderBusinessComparison f : byMasterCode){
                OrderBusinessComparisonAddListVM o = new OrderBusinessComparisonAddListVM();
                BeanUtils.copyProperties(f,o);
                vms.add(o);
                addVM.setList(vms);
            }
            list.add(addVM);
        }
        vm.setList(list);

        return vm;
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Override
    public OrderBusinessComparisonQueryVM findById(String id) {
        OrderBusinessComparison comparison = orderBusinessComparisonRepository.findById(Integer.parseInt(id)).orElseThrow(
                () -> new IllegalArgumentException("未查询到结算类型-业务定比例信息")
        );

        OrderBusinessComparisonQueryVM vm = new OrderBusinessComparisonQueryVM();
        BeanUtils.copyProperties(comparison,vm);

        //查询业务信息
        List<BusinessComparisonRelation> relations = businessComparisonRelationRepository.findByMasterCode(comparison.getCode());
        List<OrderBusinessComparisonBusinessAddVM> list = new ArrayList<>();
        for (BusinessComparisonRelation r : relations){
            OrderBusinessComparisonBusinessAddVM addVM = new OrderBusinessComparisonBusinessAddVM();
            BeanUtils.copyProperties(r,addVM);

            //查询业务下Cp
            List<CpOrderBusinessComparison> byMasterCode = cpOrderBusinessComparisonRepository.findByMasterCode(r.getCode());
            List<OrderBusinessComparisonAddListVM> vms = new ArrayList<>();
            for (CpOrderBusinessComparison f : byMasterCode){
                OrderBusinessComparisonAddListVM o = new OrderBusinessComparisonAddListVM();
                BeanUtils.copyProperties(f,o);
                vms.add(o);
                addVM.setList(vms);
            }
            list.add(addVM);
        }
        vm.setList(list);

        return vm;
    }

    @Override
    public Page<OrderBusinessComparisonQueryVM> findByConditions(String name, String code, String status, String mode,Pageable pageable) {
        Page<OrderBusinessComparisonQueryVM> map = orderBusinessComparisonRepository.findAll(((root, query, builder) -> {
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

            if (StringUtils.isNotBlank(mode)) {
                Predicate condition = builder.equal(root.get("mode"), Integer.parseInt(mode));
                predicates.add(condition);
            }

            Predicate condition = builder.equal(root.get("isdelete"), 0);
            predicates.add(condition);

            if (!predicates.isEmpty()){
                return builder.and(predicates.toArray(new Predicate[0]));
            }
            return builder.conjunction();
        }), pageable).map(orderBusinessComparisonControllerAssemlber::getListVM);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> updateOrderBusinessComparison(OrderBusinessComparisonAddVM vo) {
        if (null == vo.getId()){
            ResultVOUtil.error("1","主键不能为空");
        }

        try{
            //验证名称是否已经存在
            if (StringUtils.isNotBlank(vo.getName())){
                Optional<OrderBusinessComparison> byName = orderBusinessComparisonRepository.findByName(vo.getName());
                if (byName.isPresent()){
                    if (!vo.getId().equals(byName.get().getId()) && byName.get().getIsdelete() == 0){
                        return ResultVOUtil.error("1","名称已经存在");
                    }
                }
            }

            Optional<OrderBusinessComparison> byId = orderBusinessComparisonRepository.findById(vo.getId());
            OrderBusinessComparison comparison = null;
            if (byId.isPresent()){
                comparison = byId.get();
            }else{
                return ResultVOUtil.error("0002","未查询到id:" + vo.getId() + "结算类型-业务定比例信息");
            }
            OrderBusinessComparison o = new OrderBusinessComparison();

            BeanUtils.copyProperties(vo,o);
            o.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(comparison,o);
            orderBusinessComparisonRepository.saveAndFlush(o);

            if (!vo.getList().isEmpty()) {
                List<OrderBusinessComparisonBusinessAddVM> list = vo.getList();
                //先将之前业务删除，对现在数据新增
                long execute = queryFactory.delete(QBusinessComparisonRelation.businessComparisonRelation).where(QBusinessComparisonRelation.businessComparisonRelation.masterCode.eq(comparison.getCode())).execute();
                System.err.println(execute);
                for (OrderBusinessComparisonBusinessAddVM addVM : list){
                    BusinessComparisonRelation relation = new BusinessComparisonRelation();
                    String bcode = CodeUtil.getOnlyCode("B",5);
                    BeanUtils.copyProperties(addVM,relation);
                    relation.setCode(bcode);
                    relation.setMasterCode(comparison.getCode());
                    relation.setMasterName(comparison.getName());
                    relation.setBusinessName(StringUtils.trimToEmpty(businessRepository.findByCode(addVM.getBusinessCode().trim()).getName()));
                    relation.setCreate_time(new Timestamp(System.currentTimeMillis()));
                    businessComparisonRelationRepository.saveAndFlush(relation);

                    long execute1 = queryFactory.delete(QCpOrderBusinessComparison.cpOrderBusinessComparison).where(QCpOrderBusinessComparison.cpOrderBusinessComparison.masterCode.eq(addVM.getBusinessCode().trim())).execute();
                    System.err.println(execute1);
                    List<OrderBusinessComparisonAddListVM> listVMS = addVM.getList();
                    for (OrderBusinessComparisonAddListVM v : listVMS){
                        CpOrderBusinessComparison cp = new CpOrderBusinessComparison();
                        BeanUtils.copyProperties(v,cp);
                        cp.setMasterCode(bcode);
                        cp.setCp_name(StringUtils.trimToEmpty(cpRepository.findByCode(v.getCp_code().trim()).getName()));
                        cp.setCreate_time(new Timestamp(System.currentTimeMillis()));

                        cpOrderBusinessComparisonRepository.saveAndFlush(cp);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public ResultVO<?> getBusinessList() {
        List<Business> byStatusAndIsdelete = businessRepository.findByStatusAndIsdelete(0, 0);
        return ResultVOUtil.success(byStatusAndIsdelete);
    }
}
