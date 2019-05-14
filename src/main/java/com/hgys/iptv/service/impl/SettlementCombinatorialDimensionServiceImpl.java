package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.SettlementCombinatorialDimensionControllerAssemlber;
import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionAddVM;
import com.hgys.iptv.controller.vm.SettlementCombinatorialDimensionControllerListVM;
import com.hgys.iptv.controller.vm.SettlementDimensionVM;
import com.hgys.iptv.model.SettlementCombinatorialDimensionFrom;
import com.hgys.iptv.model.SettlementCombinatorialDimensionMaster;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionFromRepository;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionMasterRepository;
import com.hgys.iptv.service.SettlementCombinatorialDimensionService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import io.swagger.models.auth.In;
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
public class SettlementCombinatorialDimensionServiceImpl implements SettlementCombinatorialDimensionService {

    @Autowired
    private SettlementCombinatorialDimensionMasterRepository settlementCombinatorialDimensionMasterRepository;

    @Autowired
    private SettlementCombinatorialDimensionFromRepository settlementCombinatorialDimensionFromRepository;

    @Autowired
    private SettlementCombinatorialDimensionControllerAssemlber settlementCombinatorialDimensionControllerAssemlber;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> addSettlementCombinatorialDimension(SettlementCombinatorialDimensionAddVM vo) {
        if (StringUtils.isBlank(vo.getName())){
            return ResultVOUtil.error("1","结算组合维度名称不能为空");
        }else if (vo.getList().isEmpty()){
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

            List<SettlementDimensionVM> vos = vo.getList();

            //验证权重是否超过100%
            Integer he = 0;
            for (SettlementDimensionVM s : vos){
                he = he + s.getWeight();
                if (he > 100){
                    new IllegalArgumentException("权重不能超过100%");
                }
            }
            //处理附表数据
            for (SettlementDimensionVM s : vos){
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> batchLogicDelete(String ids) {
        try{
            List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists){
                settlementCombinatorialDimensionMasterRepository.batchLogicDelete(Integer.parseInt(s));

                SettlementCombinatorialDimensionMaster byId = settlementCombinatorialDimensionMasterRepository.findById(Integer.parseInt(s)).orElseThrow(
                        () -> new IllegalArgumentException("未查询到数据")
                );

                settlementCombinatorialDimensionFromRepository.batchLogicDeleteByCode(byId.getCode().trim());
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public SettlementCombinatorialDimensionControllerListVM getSettlementCombinatorialDimension(String code) {

        SettlementCombinatorialDimensionMaster byCode = settlementCombinatorialDimensionMasterRepository.findByCode(code).orElseThrow(
                () -> new IllegalArgumentException("未查询到结算组合信息")
        );

        SettlementCombinatorialDimensionControllerListVM vm = new SettlementCombinatorialDimensionControllerListVM();
        BeanUtils.copyProperties(byCode,vm);

        List<SettlementCombinatorialDimensionFrom> byMaster_code = settlementCombinatorialDimensionFromRepository.findByMasterCode(byCode.getCode().trim());

        List<SettlementCombinatorialDimensionControllerListVM.SettlementDimension> list = new ArrayList<>();
        for (SettlementCombinatorialDimensionFrom f : byMaster_code){
            SettlementCombinatorialDimensionControllerListVM.SettlementDimension s = new SettlementCombinatorialDimensionControllerListVM.SettlementDimension();
            BeanUtils.copyProperties(f,s);
            list.add(s);
            vm.setList(list);
        }
        return vm;
    }

    @Override
    public SettlementCombinatorialDimensionControllerListVM findById(String id) {
        SettlementCombinatorialDimensionMaster byId = settlementCombinatorialDimensionMasterRepository.findById(Integer.parseInt(id)).orElseThrow(
                () -> new IllegalArgumentException("未查询到结算组合信息")
        );

        SettlementCombinatorialDimensionControllerListVM vm = new SettlementCombinatorialDimensionControllerListVM();
        BeanUtils.copyProperties(byId,vm);

        List<SettlementCombinatorialDimensionFrom> byMaster_code = settlementCombinatorialDimensionFromRepository.findByMasterCode(byId.getCode().trim());

        List<SettlementCombinatorialDimensionControllerListVM.SettlementDimension> list = new ArrayList<>();
        for (SettlementCombinatorialDimensionFrom f : byMaster_code){
            SettlementCombinatorialDimensionControllerListVM.SettlementDimension s = new SettlementCombinatorialDimensionControllerListVM.SettlementDimension();
            BeanUtils.copyProperties(f,s);
            list.add(s);
            vm.setList(list);
        }
        return vm;
    }

    @Override
    public Page<SettlementCombinatorialDimensionControllerListVM> findByConditions(String name, String code, String status, Pageable pageable) {

        Page<SettlementCombinatorialDimensionControllerListVM> map = settlementCombinatorialDimensionMasterRepository.findAll(((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)) {
                Predicate condition = builder.like(root.get("name"), "%"+name+"%");
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(code)) {
                Predicate condition = builder.like(root.get("code"), "%"+code+"%");
                predicates.add(condition);
            }

            if (StringUtils.isNotBlank(status)) {
                Predicate condition = builder.equal(root.get("status"), Integer.parseInt(status));
                predicates.add(condition);
            }

            Predicate condition = builder.equal(root.get("isdelete"), 0);
            predicates.add(condition);

            if (!predicates.isEmpty()) {
                return builder.and(predicates.toArray(new Predicate[0]));
            }
            return builder.conjunction();
        }), pageable).map(settlementCombinatorialDimensionControllerAssemlber::getListVM);

        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> updateCombinatorialDimension(SettlementCombinatorialDimensionAddVM vo) {
        if (null == vo.getId()){
            ResultVOUtil.error("1","结算组合维度主键不能为空");
        }

        try{
            //验证名称是否已经存在
            if (StringUtils.isNotBlank(vo.getName())){
                Optional<SettlementCombinatorialDimensionMaster> byName = settlementCombinatorialDimensionMasterRepository.findByName(vo.getName().trim());
                if (byName.isPresent()){
                    if (!vo.getId().equals(byName.get().getId())){
                        return ResultVOUtil.error("1","结算维度组合名称已经存在");
                    }
                }
            }
            SettlementCombinatorialDimensionMaster master = settlementCombinatorialDimensionMasterRepository.findById(vo.getId()).orElseThrow(() -> new IllegalArgumentException("为查询到ID为:" + vo.getId() + "结算维度信息"));

            SettlementCombinatorialDimensionMaster m = new SettlementCombinatorialDimensionMaster();
            BeanUtils.copyProperties(vo,m);
            m.setModifyTime(new Timestamp(System.currentTimeMillis()));
            UpdateTool.copyNullProperties(master,m);
            settlementCombinatorialDimensionMasterRepository.saveAndFlush(m);

            if (!vo.getList().isEmpty()){
                List<SettlementDimensionVM> list = vo.getList();
                //先将之前的删除
                settlementCombinatorialDimensionFromRepository.deleteByMasterCode(master.getCode().trim());
                for (SettlementDimensionVM v : list){
                    SettlementCombinatorialDimensionFrom from = new SettlementCombinatorialDimensionFrom();
                    BeanUtils.copyProperties(v,from);
                    from.setMaster_code(master.getCode());
                    from.setCreate_time(new Timestamp(System.currentTimeMillis()));
                    from.setIsdelete(0);

                    settlementCombinatorialDimensionFromRepository.saveAndFlush(from);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO<?> getSettlementDimensionList(String code) {
        List<SettlementCombinatorialDimensionFrom> byMasterCode = settlementCombinatorialDimensionFromRepository.findByMasterCode(code);
        return ResultVOUtil.success(byMasterCode);
    }
    @Override
    public ResultVO<?> findcdslist() {
        List<SettlementCombinatorialDimensionMaster> cps =settlementCombinatorialDimensionMasterRepository.findcdslist();
        if(cps!=null)
            return ResultVOUtil.success(cps);
        return ResultVOUtil.error("1","所查询的不存在!");
    }

}
