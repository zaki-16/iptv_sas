package com.hgys.iptv.service;

import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.vo.ResultVO;

import java.util.List;


public interface OrderQuantityService {
    /**通过Id查询*/
    OrderQuantity findById(Integer id);
    /*** 添加*/
    ResultVO<?> insterOrderQuantity(String name, String status, String note);


    /*** 通过Id,批量逻辑删除*/
    ResultVO<?> batchDelete(String ids);

}
