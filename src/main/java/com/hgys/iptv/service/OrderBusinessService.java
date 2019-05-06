package com.hgys.iptv.service;


import com.hgys.iptv.model.OrderBusiness;
import com.hgys.iptv.model.vo.ResultVO;

public interface OrderBusinessService {

    /** 通过Id查询*/
    OrderBusiness findById(Integer id);

    /*** 添加*/
    ResultVO<?> insterOrderBusiness(String name, String status, String note);

    /**
     * 通过Id逻辑删除
     * @param ids
     * @return
     */
    ResultVO<?> batchDeleteob(String ids);

}
