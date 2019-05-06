package com.hgys.iptv.service;


import com.hgys.iptv.model.OrderCp;
import com.hgys.iptv.model.vo.ResultVO;

public interface OrderCpService {

    /*** 通过Id查询*/
    OrderCp findById(Integer id);

    /*** 添加*/
    ResultVO<?> insterOrderCp(String name, String status, String note);

    /*** 通过Id,批量逻辑删除*/
    ResultVO<?> batchDeleteoc(String ids);


}
