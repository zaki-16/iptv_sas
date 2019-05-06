package com.hgys.iptv.service.impl;


import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.OrderQuantityRepository;
import com.hgys.iptv.service.OrderQuantityService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderQuantityServiceImpl  implements OrderQuantityService {

    @Autowired
    private OrderQuantityRepository orderquantityRepository;
    /**
     * 根据ID查询
     */
    @Override
    public OrderQuantity findById(Integer id) {
        //如果未查询到返回null
        return orderquantityRepository.findById(id).orElse(null);
    }



 /**
     * 添加
     */
    @Override
    public ResultVO<?> insterOrderQuantity(String name, String status, String note) {
        OrderQuantity oq = new OrderQuantity();
        oq.setCode(CodeUtil.getOnlyCode("SDS",5));
        oq.setInputTime(new Timestamp(System.currentTimeMillis()));
        oq.setIsdelete(0);
        oq.setName(name);
        oq.setNote(note);
        oq.setStatus(Integer.parseInt(status));
        orderquantityRepository.save(oq);
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> batchDelete(String ids) {
        try{
            List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists){
                orderquantityRepository.batchDelete(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }
}
