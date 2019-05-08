package com.hgys.iptv.service;

import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.vo.ResultVO;

public interface BusinessService {
    ResultVO<?> save(Business business);

    ResultVO<?> update(Business business);

    ResultVO<?> logicDelete(Integer id);

    ResultVO<?> batchLogicDelete(String ids);

    ResultVO<?> findById(Integer id);

    ResultVO<?> findByCode(String code);

    ResultVO<?> findAll();
}
