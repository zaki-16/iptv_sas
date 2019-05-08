package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.OrderProductControllerAssemlber;
import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.*;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.OrderProductRepository;
import com.hgys.iptv.repository.OrderProductWithSCDRepository;
import com.hgys.iptv.service.OrderProductService;
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
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private OrderProductRepository orderproductRepository;

    @Autowired
    private OrderProductWithSCDRepository orderProductWithSCDRepository;

    @Autowired
    private OrderProductControllerAssemlber orderProductControllerAssemlber;



    @Override
    public OrderProduct findById(Integer id) {
        //如果未查询到返回null
        return orderproductRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> batchDeleteop(String ids) {
        try{
            List<String> idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists){
                orderproductRepository.batchDeleteop(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 新增
     * @param vm
     * @return
     */
    @Override
    public ResultVO<?> addOrderBusinessComparison(OrderProductWithSettlementAddVM vm) {
        try{
            //信息校验
            if (StringUtils.isBlank(vm.getName())){
                return ResultVOUtil.error("1","名称不能为空");
            }else if (StringUtils.isBlank(vm.getProductcode())){
                return ResultVOUtil.error("1","产品编码不能为空");
            }else if (StringUtils.isBlank(vm.getProductname())){
                return ResultVOUtil.error("1","产品名称不能为空");
            }else if (vm.getList().isEmpty()){
                return ResultVOUtil.error("1","业务定比例CP信息选择不能为空");
            }else if (null == vm.getMode()){
                return ResultVOUtil.error("1","业务定比例结算方式不能为空");
            }else if (null == vm.getStatus()){
                return ResultVOUtil.error("1","业务定比例状态不能为空");
            }

            List<OrderProductWithSCDAddLIstVM> list =  vm.getList();

            for (OrderProductWithSCDAddLIstVM v : list) {

                if (1 == vm.getMode()) {
                    if (StringUtils.isBlank(v.getSdname())) {
                        return ResultVOUtil.error("1", "单维度名称不能为空");
                    } else if (StringUtils.isBlank(v.getSdcode())) {
                        return ResultVOUtil.error("1", "单维度Code不能为空");
                    }
                } else {
                    if (StringUtils.isBlank(v.getScdname())) {
                        return ResultVOUtil.error("1", "多维度名称不能为空");
                    } else if (StringUtils.isBlank(v.getScdcode())){
                        return ResultVOUtil.error("1", "多维度Code不能为空");
                    }
                }
            }
            //新增主表信息
            OrderProduct comparison = new OrderProduct();
            String code = CodeUtil.getOnlyCode("OBP",5);
            BeanUtils.copyProperties(vm,comparison);
            comparison.setInputTime(new Timestamp(System.currentTimeMillis()));
            comparison.setIsdelete(0);
            comparison.setBname(vm.getProductname());
            comparison.setCode(code);
            orderproductRepository.save(comparison);

            //新增从表信息
            for (OrderProductWithSCDAddLIstVM v : list){
                OrderProductWithSCD cp = new OrderProductWithSCD();
                BeanUtils.copyProperties(v,cp);
                cp.setOpcode(code);
                cp.setCreatetime(new Timestamp(System.currentTimeMillis()));
                orderProductWithSCDRepository.save(cp);
            }
        }catch (Exception e){
            e.printStackTrace();
            ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public Page<OrderProductWithSettlementAddVM> findByConditions(String name, String code, String productcode, String productname, String status, String mode, Pageable pageable) {
        Page<OrderProductWithSettlementAddVM> map = orderproductRepository.findAll(((root, query, builder) -> {
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

            if (StringUtils.isNotBlank(productcode)) {
                Predicate condition = builder.like(root.get("productcode"), productcode);
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(productname)) {
                Predicate condition = builder.like(root.get("productname"), productname);
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(status)) {
                Predicate condition = builder.equal(root.get("mode"), Integer.parseInt(mode));
                predicates.add(condition);
            }

            Predicate condition = builder.equal(root.get("isdelete"), 0);
            predicates.add(condition);
            return builder.conjunction();
        }), pageable).map(orderProductControllerAssemlber::getListVM);
        return map;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> updateOrderproduct(OrderProductWithSettlementAddVM vo) {
        if (null == vo.getId()){
            ResultVOUtil.error("1","主键不能为空");
        }

        try{
            //验证名称是否已经存在
            if (StringUtils.isNotBlank(vo.getName())){
                Optional<OrderProduct> byName = orderproductRepository.findByName(vo.getName());
                if (byName.isPresent()){
                    if (!vo.getId().equals(byName.get().getId())){
                        return ResultVOUtil.error("1","名称已经存在");
                    }
                }
            }

            OrderProduct comparison = orderproductRepository.findById(vo.getId()).orElseThrow(() -> new IllegalArgumentException("为查询到id为："+vo.getId()+"业务定比例信息"));
            OrderProduct o = new OrderProduct();

            BeanUtils.copyProperties(vo,o);
            o.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(comparison,o);
            orderproductRepository.saveAndFlush(o);

            if (!vo.getList().isEmpty()) {
                List<OrderProductWithSCDAddLIstVM> list = vo.getList();
                //先将之前的删除
                orderProductWithSCDRepository.deleteByMasterCode(comparison.getCode().trim());

                for (OrderProductWithSCDAddLIstVM v : list){
                    OrderProductWithSCD cp = new OrderProductWithSCD();
                    BeanUtils.copyProperties(v,cp);
                    cp.setOpcode(comparison.getCode());
                    cp.setCreatetime(new Timestamp(System.currentTimeMillis()));

                    orderProductWithSCDRepository.saveAndFlush(cp);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return null;
    }


}
