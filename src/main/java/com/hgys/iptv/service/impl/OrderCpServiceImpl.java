package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.OrderBusiness;
import com.hgys.iptv.model.OrderCp;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.OrderCpRepository;
import com.hgys.iptv.service.OrderCpService;
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
public class OrderCpServiceImpl implements OrderCpService {
    @Autowired
    private OrderCpRepository ordercpRepository;

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


    /**
     * 添加
     */
    @Override
    public ResultVO<?> insterOrderCp(String name, String status, String note) {
        OrderCp od = new OrderCp();
        od.setCode(CodeUtil.getOnlyCode("SDS",5));
        od.setInputTime(new Timestamp(System.currentTimeMillis()));
        od.setIsdelete(0);
        od.setName(name);
        od.setNote(note);
        od.setStatus(Integer.parseInt(status));
        ordercpRepository.save(od);
        return ResultVOUtil.success(Boolean.TRUE);
    }

}
