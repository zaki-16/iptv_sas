package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.OrderBusinessComparisonControllerAssemlber;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddLIstVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonAddVM;
import com.hgys.iptv.controller.vm.OrderBusinessComparisonQueryVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.CpOrderBusinessComparison;
import com.hgys.iptv.model.OrderBusinessComparison;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.BusinessRepository;
import com.hgys.iptv.repository.CpOrderBusinessComparisonRepository;
import com.hgys.iptv.repository.OrderBusinessComparisonRepository;
import com.hgys.iptv.service.OrderBusinessComparisonService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
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

    /**
     * 新增
     * @param vm
     * @return
     */
    @Override
    public ResultVO<?> addOrderBusinessComparison(OrderBusinessComparisonAddVM vm) {
        try{
            //信息校验
            if (StringUtils.isBlank(vm.getName())){
                return ResultVOUtil.error("1","名称不能为空");
            }else if (StringUtils.isBlank(vm.getBusinessCode())){
                return ResultVOUtil.error("1","业务编码不能为空");
            }else if (StringUtils.isBlank(vm.getBusinessName())){
                return ResultVOUtil.error("1","业务名称不能为空");
            }else if (vm.getList().isEmpty()){
                return ResultVOUtil.error("1","业务定比例CP信息选择不能为空");
            }else if (null == vm.getMode()){
                return ResultVOUtil.error("1","业务定比例结算方式不能为空");
            }else if (null == vm.getStatus()){
                return ResultVOUtil.error("1","业务定比例状态不能为空");
            }

            List<OrderBusinessComparisonAddLIstVM> list =  vm.getList();

            for (OrderBusinessComparisonAddLIstVM v : list){
                if (StringUtils.isBlank(v.getCp_name())){
                    return ResultVOUtil.error("1","CP名称不能为空");
                }else if (StringUtils.isBlank(v.getCp_code())){
                    return ResultVOUtil.error("1","CP编码不能为空");
                }

                if (1 == vm.getMode()){
                    if (null == v.getProportion()){
                        return ResultVOUtil.error("1","CP所在比例不能为空");
                    }else if (null == v.getMoney()){
                        return ResultVOUtil.error("1","CP分配金额不能为空");
                    }
                }
            }

            //验证所有CP分批比例是否大于100%
            Integer he = 0;
            for (OrderBusinessComparisonAddLIstVM v : list){
                he = he + v.getProportion();
                if (he > 100){
                    return ResultVOUtil.error("1","所有CP所在比例不能大于100%");
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

            //新增从表信息
            for (OrderBusinessComparisonAddLIstVM v : list){
                CpOrderBusinessComparison cp = new CpOrderBusinessComparison();

                BeanUtils.copyProperties(v,cp);
                cp.setMasterCode(code);
                cp.setCreate_time(new Timestamp(System.currentTimeMillis()));

                cpOrderBusinessComparisonRepository.save(cp);
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

        List<CpOrderBusinessComparison> byMasterCode = cpOrderBusinessComparisonRepository.findByMasterCode(comparison.getCode().trim());

        List<OrderBusinessComparisonAddLIstVM> list = new ArrayList<>();
        for (CpOrderBusinessComparison f : byMasterCode){
            OrderBusinessComparisonAddLIstVM o = new OrderBusinessComparisonAddLIstVM();
            BeanUtils.copyProperties(f,o);
            list.add(o);
            vm.setList(list);
        }
        return vm;
    }

    @Override
    public Page<OrderBusinessComparisonQueryVM> findByConditions(String name, String code, String businessCode, String businessName, String status, String mode,Pageable pageable) {
        Page<OrderBusinessComparisonQueryVM> map = orderBusinessComparisonRepository.findAll(((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)) {
                Predicate condition = builder.like(root.get("name"), name);
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(code)) {
                Predicate condition = builder.like(root.get("code"), code);
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(status)) {
                Predicate condition = builder.equal(root.get("status"), Integer.parseInt(status));
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(businessCode)) {
                Predicate condition = builder.like(root.get("businessCode"), businessCode);
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(businessName)) {
                Predicate condition = builder.like(root.get("businessName"), businessName);
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(status)) {
                Predicate condition = builder.equal(root.get("mode"), Integer.parseInt(mode));
                predicates.add(condition);
            }

            Predicate condition = builder.equal(root.get("isdelete"), 0);
            predicates.add(condition);
            return builder.conjunction();
        }), pageable).map(orderBusinessComparisonControllerAssemlber::getListVM);
        return map;
    }

    @Override
    public ResultVO<?> updateOrderBusinessComparison(OrderBusinessComparisonAddVM vo) {
        if (null == vo.getId()){
            ResultVOUtil.error("1","主键不能为空");
        }else if (StringUtils.isBlank(vo.getName())){
            ResultVOUtil.error("1","名称不能为空");
        }else if (null == vo.getStatus()){
            ResultVOUtil.error("1","状态不能为空");
        }else if (null == vo.getMode()){
            ResultVOUtil.error("1","结算方式不能为空");
        }else if (StringUtils.isBlank(vo.getBusinessCode())){
            ResultVOUtil.error("1","业务编码不能为空");
        }else if (StringUtils.isBlank(vo.getBusinessName())){
            ResultVOUtil.error("1","业务名称不能为空");
        }
        try{
            //验证名称是否已经存在
            Optional<OrderBusinessComparison> byName = orderBusinessComparisonRepository.findByName(vo.getName());
            if (byName.isPresent()){
                if (!vo.getId().equals(byName.get().getId())){
                    return ResultVOUtil.error("1","名称已经存在");
                }
            }

            OrderBusinessComparison comparison = orderBusinessComparisonRepository.findById(vo.getId()).orElseThrow(() -> new IllegalArgumentException("为查询到id为："+vo.getId()+"业务定比例信息"));
            OrderBusinessComparison o = new OrderBusinessComparison();

            BeanUtils.copyProperties(vo,o);
            o.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(comparison,o);
            orderBusinessComparisonRepository.saveAndFlush(o);

            if (!vo.getList().isEmpty()) {
                List<OrderBusinessComparisonAddLIstVM> list = vo.getList();
                //先将之前的删除
                cpOrderBusinessComparisonRepository.deleteByMasterCode(comparison.getCode().trim());

                for (OrderBusinessComparisonAddLIstVM v : list){
                    CpOrderBusinessComparison cp = new CpOrderBusinessComparison();
                    BeanUtils.copyProperties(v,cp);
                    cp.setMasterCode(comparison.getCode());
                    cp.setCreate_time(new Timestamp(System.currentTimeMillis()));

                    cpOrderBusinessComparisonRepository.saveAndFlush(cp);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return null;
    }

    @Override
    public ResultVO<?> getBusinessList() {
        List<Business> byStatusAndIsdelete = businessRepository.findByStatusAndIsdelete(0, 0);
        return ResultVOUtil.success(byStatusAndIsdelete);
    }
}
