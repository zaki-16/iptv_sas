package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionAddVM;
import com.hgys.iptv.model.SettlementCombinatorialDimensionFrom;
import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionFromRepository;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionMasterRepository;
import com.hgys.iptv.service.SettlementCombinatorialDimensionService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class SettlementCombinatorialDimensionServiceImpl implements SettlementCombinatorialDimensionService {

    @Autowired
    private SettlementCombinatorialDimensionMasterRepository settlementCombinatorialDimensionMasterRepository;

    @Autowired
    private SettlementCombinatorialDimensionFromRepository settlementCombinatorialDimensionFromRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> addSettlementCombinatorialDimension(SettlementCombinatorialDimensionAddVM vo) {
        if (StringUtils.isBlank(vo.getName())){
            return ResultVOUtil.error("1","结算组合维度名称不能为空");
        }else if (null == vo.getList()){
            return ResultVOUtil.error("1","结算维度组合集合不能为空");
        }
        //验证名字是否已经存在
        Optional<SettlementCombinatorialDimensionMaster> byName = settlementCombinatorialDimensionMasterRepository.findByName(vo.getName().trim());
        if (byName.isPresent()){
            return ResultVOUtil.error("1","结算维度组合名称已经存在");
        }

        try {
            //主表数据新增
            String code = CodeUtil.getOnlyCode("SCD",5);
            SettlementCombinatorialDimensionMaster master = new SettlementCombinatorialDimensionMaster();
            master.setCode(code);
            master.setName(vo.getName());
            master.setRemakes(vo.getRemakes());
            master.setInputTime(new Timestamp(System.currentTimeMillis()));
            master.setIsdelete(0);
            master.setStatus(vo.getStatus());

            settlementCombinatorialDimensionMasterRepository.save(master);

            List<SettlementCombinatorialDimensionAddVM.SettlementDimension> vos = vo.getList();
            //处理附表数据
            for (SettlementCombinatorialDimensionAddVM.SettlementDimension s : vos){
                SettlementCombinatorialDimensionFrom from = new SettlementCombinatorialDimensionFrom();
                from.setMaster_code(code);
                from.setDim_code(s.getDim_code());
                from.setDim_name(s.getDim_name());
                from.setWeight(s.getWeight());
                from.setCreate_time(new Timestamp(System.currentTimeMillis()));
                from.setIsdelete(0);

                settlementCombinatorialDimensionFromRepository.save(from);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }
}
