package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Cp;
import com.hgys.iptv.repository.CpRepository;
import com.hgys.iptv.service.CpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    /**
     * cp的新增和修改
     */
    @Override
    @Transactional
    public Cp saveOrUpdate(Cp cp){
        return cpRepository.save(cp);
    }

    /**
     * cp删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @Override
    public void delete(Cp cp){
        cp.setIsdelete(1);
        saveOrUpdate(cp);
    }
    /**
     * cp批量删除
     */
    @Override
    public void batchDelete(List<Cp> cps){
        if(cps!=null && cps.size()>0)
            for(Cp cp:cps){
                delete(cp);
            }
    }

    /**
     * cp查询--根据id
     */
    @Override
    public Cp findById(Integer id) {
        return cpRepository.findById(id).get();
    }

    /**
     * cp查询所有
     */
    @Override
    public List<Cp> findAll() {
        return cpRepository.findAll();
    }




}
