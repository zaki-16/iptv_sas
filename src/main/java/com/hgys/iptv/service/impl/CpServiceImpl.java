package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.CpRepository;
import com.hgys.iptv.service.CpService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: wangz
 * @Date: 2019/5/6 14:44
 * @Description:
 */
@Service
public class CpServiceImpl implements CpService {
    @Autowired
    private CpRepository cpRepository;
//    @Autowired
//    AbstractBaseRepositoryImpl<Cp> repository;

    //必填字段
//    private final String[] cols = {"name","status"};

    /**
     * cp 新增
     * @param cp
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> save(Cp cp){
        //校验cp名称是否已经存在
        Cp byName = cpRepository.findByName(cp.getName());
        if (null != byName){
            return ResultVOUtil.error("1",byName + "名称已经存在");
        }
        String[] cols = {cp.getName(),cp.getStatus().toString()};
        if(!Validator.validEmptyPass(cols))//必填字段不为空则插入
            return ResultVOUtil.error("1","有必填字段未填写！");
        cp.setRegisTime(new Timestamp(System.currentTimeMillis()));//注册时间
        cp.setModifyTime(new Timestamp(System.currentTimeMillis()));//最后修改时间
        cp.setCode(CodeUtil.getOnlyCode("SDS",5));//cp编码
        cp.setIsdelete(0);//删除状态
        Cp cp_add = cpRepository.save(cp);
        if(cp_add !=null)
            return ResultVOUtil.success(Boolean.TRUE);
        return ResultVOUtil.error("1","新增失败！");
    }

    /**
     * cp 修改
     * @param cp
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> update(Cp cp) {
        Cp cp_up = cpRepository.save(cp);
        if(cp_up !=null)
            return ResultVOUtil.success(Boolean.TRUE);
        return ResultVOUtil.error("1","新增失败！");//jpa会调用isNew()方法判定对象是否已存在
    }

    /**
     * cp删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> logicDelete(Integer id){
        try {
            cpRepository.logicDelete(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * cp批量逻辑删除
     * @param ids
     */
    @Override
    public ResultVO<?> batchLogicDelete(String ids){
        List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
        for (String s : idLists)
            cpRepository.logicDelete(Integer.parseInt(s));
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * cp单查询--根据id
     * @param id
     * @return
     */
    @Override
    public ResultVO<?> findById(Integer id) {
        Cp cp = cpRepository.findById(id).get();
        if(cp!=null)
            return ResultVOUtil.success(cp);
        return ResultVOUtil.error("1","所查询的cp不存在!");
    }

    /**
     * cp单查询--根据code
     * @param code
     * @return
     */
    @Override
    public ResultVO<?> findByCode(String code) {
        Cp cp = cpRepository.findByCode(code);
        if(cp!=null)
            return ResultVOUtil.success(cp);
        return ResultVOUtil.error("1","所查询的cp不存在!");
    }

    /**
     * cp列表查询
     * @return
     */
    @Override
    public ResultVO<?> findAll() {
        List<Cp> cps = cpRepository.findAll();
        if(cps!=null&&cps.size()>0)
            return ResultVOUtil.success(cps);
        return ResultVOUtil.error("1","所查询的cp列表不存在!");
    }




}
