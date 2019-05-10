package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.OrderBusinessControllerAssemlber;
import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.OrderBusinessService;
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
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderBusinessRepository orderbusinessRepository;


    @Autowired
    private OrderBuinessWithCpRepository orderBuinessWithCpRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private OrderBusinessControllerAssemlber orderBusinessControllerAssemlber;

    @Autowired
    private SmallOrderCpRepository smallOrderCpRepository;

    @Autowired
    private CpRepository cpRepository;


    @Override
    public OrderBusiness findById(Integer id) {
        //如果未查询到返回null
        return orderbusinessRepository.findById(id).orElse(null);
    }


    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> batchDeleteob(String ids) {
        try{
            List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists){
                orderbusinessRepository.batchDeleteob(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> addOrderBusiness(OrderBusinessWithCPAddVM vo) {
        if (StringUtils.isBlank(vo.getName())) {
            return ResultVOUtil.error("1", "名称不能为空");
        } else if (vo.getList().isEmpty()) {
            return ResultVOUtil.error("1", "集合不能为空");
        }
        try {
            //主表数据新增
            String code = CodeUtil.getOnlyCode("SCD", 5);
            OrderBusiness master = new OrderBusiness();
            master.setCode(code);
            master.setName(vo.getName());
            master.setNote(vo.getNote());
            master.setInputTime(new Timestamp(System.currentTimeMillis()));
            master.setIsdelete(0);
            master.setStatus(vo.getStatus());
            orderbusinessRepository.save(master);
            List<SmallOrderBusinessVM> vos = vo.getList();
            //验证权重是否超过100%
            Integer he = 0;
            for (SmallOrderBusinessVM s : vos){
                he += he + s.getWeight();
                if (he > 100){
                    new IllegalArgumentException("权重不能超过100%");
                }
            }
            //处理业务表
            for (SmallOrderBusinessVM s : vos) {
                String codes = CodeUtil.getOnlyCode("SCD", 5);
                OrderBusinessWithCp from = new OrderBusinessWithCp();
                String buname = businessRepository.findByMasterCodes(s.getBucode());//根据业务ID，查询业务的名称
                from.setObcode(code);
                from.setBucode(s.getBucode());
                from.setBuname(buname);
                from.setCreatetime(new Timestamp(System.currentTimeMillis()));
                from.setIsdelete(0);
                from.setWeight(s.getWeight());
                from.setCode(codes);
                orderBuinessWithCpRepository.save(from);
                List<SmallOrderBusinessVM.SmallOrderBusinessCPVM> voss = s.getLists();
                for (SmallOrderBusinessVM.SmallOrderBusinessCPVM cps : voss) {   //处理CP列表
                    OrderBusinessCp cp = new OrderBusinessCp();
                    String cpname = cpRepository.findByMasterCodes(cps.getCpcode());//根据CPID 查询CP的名称
                    cp.setCpcode(cps.getCpcode());
                    cp.setCpname(cpname);
                    cp.setBucode(s.getBucode());
                    cp.setWeight(cps.getWeight());
                    cp.setObcode(from.getObcode());
                    smallOrderCpRepository.save(cp);
                }
            }
            }catch(Exception e){
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> updateOrderBusiness(OrderBusinessWithCPAddVM vo) {
        if (null == vo.getId()){
            ResultVOUtil.error("1","主键不能为空");
        }else if (StringUtils.isBlank(vo.getName())){
            ResultVOUtil.error("1","名称不能为空");
        }else if (null == vo.getStatus()){
            ResultVOUtil.error("1","状态不能为空");
        }else if (StringUtils.isBlank(vo.getName())){
            ResultVOUtil.error("1","业务名称不能为空");
        }
        try{
            //验证名称是否已经存在
            Optional<OrderBusiness> byName = orderbusinessRepository.findByName(vo.getName());
            if (byName.isPresent()){
                if (!vo.getId().equals(byName.get().getId())){
                    return ResultVOUtil.error("1","名称已经存在");
                }
            }
            OrderBusiness comparison = orderbusinessRepository.findById(vo.getId()).orElseThrow(() -> new IllegalArgumentException("未查询到id为："+vo.getId()+"业务定比例信息"));
            OrderBusiness o = new OrderBusiness();
            BeanUtils.copyProperties(vo,o);
            o.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(comparison,o);
            orderbusinessRepository.saveAndFlush(o);
            if (!vo.getList().isEmpty()) {
                List<SmallOrderBusinessVM> list = vo.getList();
                //先将之前的业务删除
                orderBuinessWithCpRepository.deleteByMasterCode(comparison.getCode().trim());
                for (SmallOrderBusinessVM v : list){
                    OrderBusinessWithCp cp = new OrderBusinessWithCp();
                    BeanUtils.copyProperties(v,cp);
                    cp.setObcode(comparison.getCode());
                    cp.setCreatetime(new Timestamp(System.currentTimeMillis()));
                    orderBuinessWithCpRepository.saveAndFlush(cp);
                    List<SmallOrderBusinessVM.SmallOrderBusinessCPVM> voss = v.getLists();
                    for (SmallOrderBusinessVM.SmallOrderBusinessCPVM cps : voss) {   //处理CP列表
                        //先将之前的CP删除
                        smallOrderCpRepository.deleteByMastercodes(cp.getObcode());
                    OrderBusinessCp cpcp = new OrderBusinessCp();
                        cpcp.setCpcode(cps.getCpcode());
                        cpcp.setCpname(cps.getCpname());
                        cpcp.setBucode(v.getBucode());
                        cpcp.setWeight(cps.getWeight());
                        cpcp.setObcode(cp.getObcode());
                        smallOrderCpRepository.saveAndFlush(cpcp);
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
    public Page<OrderBusinessWithCPAddVM> findByConditions(String name, String code,   String status,  Pageable pageable) {
        Page<OrderBusinessWithCPAddVM> map = orderbusinessRepository.findAll(((root, query, builder) -> {
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

            Predicate condition = builder.equal(root.get("isdelete"), 0);
            predicates.add(condition);
            return builder.conjunction();
        }), pageable).map(orderBusinessControllerAssemlber::getListVM);
        return map;
    }

    /**
     * 通过code查询
     * @param code
     * @return
     */
    @Override
    public OrderBusinessWithCPAddVM getOrderBusiness(String code) {
        OrderBusiness comparison = orderbusinessRepository.findByCode(code).orElseThrow(
                () -> new IllegalArgumentException("未查询到结算类型-业务级信息")
        );

        OrderBusinessWithCPAddVM vm = new OrderBusinessWithCPAddVM();
        BeanUtils.copyProperties(comparison,vm);

        List<OrderBusinessWithCp> byMasterCode = orderBuinessWithCpRepository.findByMasterCode(comparison.getCode().trim());

        List<SmallOrderBusinessVM> list = new ArrayList<>();
        for (OrderBusinessWithCp f : byMasterCode){
            SmallOrderBusinessVM o = new SmallOrderBusinessVM();
            BeanUtils.copyProperties(f,o);
            list.add(o);
            vm.setList(list);
        }
        return vm;
    }

    @Override
    public OrderBusinessWithCPAddVM findByIds(String id) {
        OrderBusiness byId = orderbusinessRepository.findById(Integer.parseInt(id)).orElseThrow(
                () -> new IllegalArgumentException("未查询到结算信息")
        );

        OrderBusinessWithCPAddVM vm = new OrderBusinessWithCPAddVM();
        BeanUtils.copyProperties(byId,vm);

        List<OrderBusinessWithCp> byMaster_code = orderBuinessWithCpRepository.findByMasterCode(byId.getCode().trim());


        OrderBusinessWithCp bybu = orderBuinessWithCpRepository.findById(Integer.parseInt(id)).orElseThrow(
                () -> new IllegalArgumentException("未查询到结算信息")
        );

        List<OrderBusinessCp> bybucode = smallOrderCpRepository.findByBUCode(bybu.getBucode().trim());

        List<SmallOrderBusinessVM> list = new ArrayList<>();
        List<SmallOrderBusinessVM.SmallOrderBusinessCPVM> lists = new ArrayList<>();
        for (OrderBusinessWithCp f : byMaster_code){
            SmallOrderBusinessVM s = new SmallOrderBusinessVM();
            BeanUtils.copyProperties(f,s);
            list.add(s);
            vm.setList(list);
            for (OrderBusinessCp a : bybucode){
                SmallOrderBusinessVM.SmallOrderBusinessCPVM cp = new SmallOrderBusinessVM.SmallOrderBusinessCPVM();
                BeanUtils.copyProperties(a,cp);
              /*  lists.add(a);
                vm.setList(lists);*/
            }

        }
        return vm;
    }

}
