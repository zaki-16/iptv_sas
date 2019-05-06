package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.QueryResult;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.SettlementDimensionRepository;
import com.hgys.iptv.service.SettlementDimensionService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Service
public class SettlementDimensionServiceImpl implements SettlementDimensionService {
    @Autowired
    private SettlementDimensionRepository settlementDimensionRepository;

    @Override
    public ResultVO<?> insterSettlementDimension(String name, String status, String remarks) {

        //校验名称是否已经存在
        SettlementDimension byName = settlementDimensionRepository.findByName(name.trim());
        if (null != byName){
            return ResultVOUtil.error("1",name + "名称已经存在");
        }
        SettlementDimension vo = new SettlementDimension();
        vo.setCode(CodeUtil.getOnlyCode("SDS",5));
        vo.setInputTime(new Timestamp(System.currentTimeMillis()));
        vo.setIsdelete(0);
        vo.setName(name);
        vo.setRemarks(remarks);
        vo.setStatus(Integer.parseInt(status));
        settlementDimensionRepository.save(vo);

        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public SettlementDimension findByCode(String code) {
        return settlementDimensionRepository.findByCode(code);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> batchLogicDelete(String ids) {
        try{
            List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists){
                settlementDimensionRepository.batchLogicDelete(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public ResultVO<?> updateSettlementDimension(SettlementDimension vo) {
        return null;
    }

    @Override
    public ResultVO<?> findByConditions(String name, String code, String status, String pageNum, String pageSize) {

        return ResultVOUtil.success(null);
    }



}
