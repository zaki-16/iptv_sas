package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.OrderCpControllerAssemlber;
import com.hgys.iptv.controller.assemlber.OrderCpWithCpControllerAssemlber;
import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.OrderCp;
import com.hgys.iptv.model.OrderCpWithCp;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.OrderCpRepository;
import com.hgys.iptv.repository.OrderCpWithCpRepository;
import com.hgys.iptv.service.OrderCpService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderCpServiceImpl implements OrderCpService {
    @Autowired
    private OrderCpRepository ordercpRepository;
    @Autowired
    private OrderCpWithCpRepository orderCpWithCpRepository;

    @Autowired
    private OrderCpControllerAssemlber orderCpControllerAssemlber;

    @Autowired
    private OrderCpWithCpControllerAssemlber orderCpWithCpControllerAssemlber;


    @Override
    public OrderCp findById(Integer id) {
        //如果未查询到返回null
        return ordercpRepository.findById(id).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> batchDeleteoc(String ids) {
        try{
            List<String> idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists){
                ordercpRepository.batchDeleteoc(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> addOrderCp(OrderCPAddVM vo) {
        if (org.apache.commons.lang3.StringUtils.isBlank(vo.getName())){
            return ResultVOUtil.error("1","名称不能为空");
        }else if (null == vo.getList()){
            return ResultVOUtil.error("1","集合不能为空");
        }

        try {
            //主表数据新增
            String code = CodeUtil.getOnlyCode("OCP",5);
            OrderCp master = new OrderCp();
            master.setCode(code);
            master.setName(vo.getName());
            master.setNote(vo.getNote());
            master.setInputTime(new Timestamp(System.currentTimeMillis()));
            master.setIsdelete(0);
            master.setStatus(vo.getStatus());
            master.setSettleaccounts(vo.getSettleaccounts());
            ordercpRepository.save(master);

            List<OrderCPAddVM.OrderCpWithCp> vos = vo.getList();
if (vo.getSettleaccounts()==0) { //按比例结算
    //验证权重是否超过100%
    Integer he = 0;
    for (OrderCPAddVM.OrderCpWithCp s : vos) {
        he += he + s.getWeight();
        if (he > 100 && he<100 ) {
            new IllegalArgumentException("权重合必须为100%");
        }
    }
}
            //处理附表数据
            for (OrderCPAddVM.OrderCpWithCp s : vos){
                OrderCpWithCp from = new OrderCpWithCp();
                from.setOccode(code);
                from.setCpcode(s.getCpcode());
                from.setOcname(s.getOcname());
                from.setWeight(s.getWeight());
                from.setCreatetime(new Timestamp(System.currentTimeMillis()));
                from.setMoney(s.getMoney());
                from.setSettleaccounts(s.getSettleaccounts());
                from.setIsdelete(0);
                orderCpWithCpRepository.save(from);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }


    @Override
    public Page<OrderCPWithCPListVM> findByConditions(String name, String code, String status, Pageable pageable) {

        Page<OrderCPWithCPListVM> map = ordercpRepository.findAll(((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (org.apache.commons.lang3.StringUtils.isNotBlank(name)) {
                Predicate condition = builder.equal(root.get("name"), name);
                predicates.add(condition);
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(code)) {
                Predicate condition = builder.equal(root.get("code"), code);
                predicates.add(condition);
            }

            if (org.apache.commons.lang3.StringUtils.isNotBlank(status)) {
                Predicate condition = builder.equal(root.get("status"), status);
                predicates.add(condition);
            }

            Predicate condition = builder.equal(root.get("isdelete"), 0);
            predicates.add(condition);

            if (!predicates.isEmpty()) {
                return builder.and(predicates.toArray(new Predicate[0]));
            }
            return builder.conjunction();
        }), pageable).map(orderCpWithCpControllerAssemlber::getListVM);

        return map;
    }

    @Override
    public OrderCPWithCPListVM getOrderCp(String code) {

        OrderCp byCode = ordercpRepository.findByCode(code).orElseThrow(
                () -> new IllegalArgumentException("未查询到结算组合信息")
        );

        OrderCPWithCPListVM vm = new OrderCPWithCPListVM();
        BeanUtils.copyProperties(byCode,vm);

        List<OrderCpWithCp> byMaster_code = orderCpWithCpRepository.findByMasterCode(byCode.getCode().trim());

        List<OrderCPWithCPListVM.OrderCpWithCp> list = new ArrayList<>();
        for (OrderCpWithCp f : byMaster_code){
            OrderCPWithCPListVM.OrderCpWithCp s = new OrderCPWithCPListVM.OrderCpWithCp();
            BeanUtils.copyProperties(f,s);
            list.add(s);
            vm.setList(list);
        }
        return vm;
    }

    @Override
    public ResultVO<?> updateOrderCp(OrderCPWithCPListVM vo) {
        if (null == vo.getId()){
            ResultVOUtil.error("1","主键不能为空");
        }else if (org.apache.commons.lang3.StringUtils.isBlank(vo.getName())){
            ResultVOUtil.error("1","名称不能为空");
        }else if (null == vo.getStatus()){
            ResultVOUtil.error("1","状态不能为空");
        }

        try{
            if (null != vo.getList() && vo.getList().size() > 0){

            }else {
                OrderCp master = ordercpRepository.findById(vo.getId()).orElseThrow(() -> new IllegalArgumentException("为查询到ID为:" + vo.getId() + "信息"));
                OrderCp m = new OrderCp();
                BeanUtils.copyProperties(vo,m);
                m.setModifyTime(new Timestamp(System.currentTimeMillis()));
                UpdateTool.copyNullProperties(master,m);
                ordercpRepository.saveAndFlush(m);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return null;
    }


}
