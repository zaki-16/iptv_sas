package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.CpAddVM;
import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.controller.vm.CpVM;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CpService {

    ResultVO<?> save(CpAddVM cp);

    ResultVO<?> update(CpAddVM cp);

//    ResultVO<?> logicDelete(Integer id);

    ResultVO<?> batchLogicDelete(String ids);

    ResultVO<?> findById(Integer id);

    ResultVO<?> findByCode(String code);

    ResultVO<?> findAll();

    Page<CpControllerListVM>  findByConditions(String name, Integer status, Pageable pageable);

    ResultVO<?> findcplist();

}
