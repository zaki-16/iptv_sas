package com.hgys.iptv.service;

import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.vo.ResultVO;

import java.util.List;

public interface CpService {

    ResultVO<?> save(Cp cp);

    ResultVO<?> update(Cp cp);

    ResultVO<?> logicDelete(Integer id);

    ResultVO<?> batchLogicDelete(String ids);

    ResultVO<?> findById(Integer id);

    ResultVO<?> findByCode(String code);

    ResultVO<?> findAll();

}
