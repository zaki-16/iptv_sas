package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.OrderProduct;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.OrderProductRepository;
import com.hgys.iptv.service.OrderProductService;
import com.hgys.iptv.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private OrderProductRepository orderproductRepository;

    @Override
    public OrderProduct findById(Integer id) {
        //如果未查询到返回null
        return orderproductRepository.findById(id).orElse(null);
    }

    @Override
    public void insterOrderProduct(OrderProduct oc) {
        orderproductRepository.save(oc);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
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



}
