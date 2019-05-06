package com.hgys.iptv.service;


import com.hgys.iptv.model.OrderProduct;
import com.hgys.iptv.model.vo.ResultVO;

public interface OrderProductService {

    /** 通过Id查询*/
    OrderProduct findById(Integer id);

    /**添加*/
    void insterOrderProduct(OrderProduct op);

    /*** 通过Id,批量逻辑删除*/
    ResultVO<?> batchDeleteop(String ids);
}
