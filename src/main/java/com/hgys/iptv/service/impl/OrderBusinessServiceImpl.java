package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.OrderBusiness;
import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.OrderBusinessRepository;
import com.hgys.iptv.service.OrderBusinessService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderBusinessRepository orderbusinessRepository;

    @Override
    public OrderBusiness findById(Integer id) {
        //如果未查询到返回null
        return orderbusinessRepository.findById(id).orElse(null);
    }

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

    /**
     * 添加
     */
    @Override
    public ResultVO<?> insterOrderBusiness(String name, String status, String note) {
        OrderBusiness ob = new OrderBusiness();
        ob.setCode(CodeUtil.getOnlyCode("SDS",5));
        ob.setInputTime(new Timestamp(System.currentTimeMillis()));
        ob.setIsdelete(0);
        ob.setName(name);
        ob.setNote(note);
        ob.setStatus(Integer.parseInt(status));
        orderbusinessRepository.save(ob);
        return ResultVOUtil.success(Boolean.TRUE);
    }

}
