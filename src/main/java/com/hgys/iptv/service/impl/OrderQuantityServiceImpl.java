package com.hgys.iptv.service.impl;


import com.hgys.iptv.controller.assemlber.OrderQuantityControllerAssemlber;
import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.OrderQuantityRepository;
import com.hgys.iptv.repository.OrderQuantityWithCpRepository;
import com.hgys.iptv.service.OrderQuantityService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang3.StringUtils;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderQuantityServiceImpl  implements OrderQuantityService {

    @Autowired
    private OrderQuantityRepository orderquantityRepository;

    @Autowired
    private OrderQuantityControllerAssemlber orderQuantityControllerAssemlber;

    @Autowired
    private OrderQuantityWithCpRepository OrderquantityWithCpRepository;


    @Override
    public Optional<OrderQuantity> findByCode(String code) {
        return orderquantityRepository.findByCode(code);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> batchDelete(String ids) {
        try{
            List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists) {
                orderquantityRepository.batchDelete(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }


    @Override
    public Page<OrderQuantityWithCPListVM> findByConditions(String name, String code, String status, Pageable pageable) {

        return orderquantityRepository.findAll(((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)){
                Predicate condition = builder.equal(root.get("name"), name);
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(code)){
                Predicate condition = builder.equal(root.get("code"), code);
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(status)){
                Predicate condition = builder.equal(root.get("status"), status);
                predicates.add(condition);
            }
            if (!predicates.isEmpty()){
                return builder.and(predicates.toArray(new Predicate[0]));
            }
            return builder.conjunction();
        }),pageable).map(orderQuantityControllerAssemlber::getListVM);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> updateOrderQuantity(OrderQuantityAddVM oq) {
        if (null == oq.getId()){
            ResultVOUtil.error("1","ID不能为空");
        }else if (StringUtils.isBlank(oq.getName())){
            ResultVOUtil.error("1","名称不能为空");
        }else if (null == oq.getStatus()){
            ResultVOUtil.error("1","状态不能为空");
        }
        try{
            OrderQuantity master = orderquantityRepository.findById(oq.getId()).orElseThrow(()-> new IllegalArgumentException("为查询到ID为:" + oq.getId() + "信息"));
            OrderQuantity m = new OrderQuantity();
            BeanUtils.copyProperties(oq,m);
            m.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(master,m);
            orderquantityRepository.saveAndFlush(m);
            if (!oq.getList().isEmpty()){
                List<SmallOrderCpVM> list = oq.getList();
                //先将之前的删除
                orderquantityRepository.deleteByMasterCode(master.getCode().trim());
                for (SmallOrderCpVM v : list){
                    OrderQuantityWithCp from = new OrderQuantityWithCp();
                    BeanUtils.copyProperties(v,from);
                    from.setOqcode(master.getCode());
                    from.setCreatetime(new Timestamp(System.currentTimeMillis()));
                    from.setIsdelete(0);
                    OrderquantityWithCpRepository.saveAndFlush(from);
                }
            }
    }catch (Exception e){
        e.printStackTrace();
        return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
    }
        return null;
}


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> addOrderQuantity(OrderQuantityAddVM vo) {
        if (StringUtils.isBlank(vo.getName())){
            return ResultVOUtil.error("1","名称不能为空");
        }else if (null == vo.getList()){
            return ResultVOUtil.error("1","集合不能为空");
        }
        //验证名字是否已经存在
        Optional<OrderQuantity> byName = orderquantityRepository.findByName(vo.getName().trim());
        if (byName.isPresent()){
            return ResultVOUtil.error("1","结算名称已经存在");
        }
        try {
            //结算类型-订购量表
            String code = CodeUtil.getOnlyCode("OQ",5);
            OrderQuantity oq = new OrderQuantity();
            oq.setCode(code);
            oq.setName(vo.getName());
            oq.setNote(vo.getNote());
            oq.setInputTime(new Timestamp(System.currentTimeMillis()));
            oq.setIsdelete(0);
            oq.setStatus(vo.getStatus());
            orderquantityRepository.save(oq);
            List<SmallOrderCpVM> vos = vo.getList();
            //结算类型-订购量表与 CP 关系表
            for (SmallOrderCpVM s : vos){
                OrderQuantityWithCp oc = new OrderQuantityWithCp();
                oc.setCreatetime(new Timestamp(System.currentTimeMillis()));
                oc.setCpcode(s.getCpcode());
                oc.setCpname(s.getCpname());
                oc.setOqcode(s.getOqcode());
                oc.setOqname(s.getOqname());
                oc.setCode(code);
                oc.setIsdelete(0);
                OrderquantityWithCpRepository.save(oc);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public OrderQuantityWithCPListVM getOrderQuantityWithCp(String code) {

        OrderQuantity byCode = orderquantityRepository.findByCode(code).orElseThrow(
                () -> new IllegalArgumentException("未查询到信息")
        );
        OrderQuantityWithCPListVM vm = new OrderQuantityWithCPListVM();
        BeanUtils.copyProperties(byCode,vm);

        List<OrderQuantityWithCp> byMaster_code = OrderquantityWithCpRepository.findByMasterCode(byCode.getCode().trim());

        List<OrderQuantityWithCPListVM.OrderQuantityWithCp> list = new ArrayList<>();
        for (OrderQuantityWithCp f : byMaster_code){
            OrderQuantityWithCPListVM.OrderQuantityWithCp s = new OrderQuantityWithCPListVM.OrderQuantityWithCp();
            BeanUtils.copyProperties(f,s);
            list.add(s);
            vm.setList(list);
        }
        return vm;
    }


}
