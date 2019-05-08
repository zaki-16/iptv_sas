package com.hgys.iptv.service;

import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.vo.ResultVO;

public interface ProductService {

    ResultVO<?> save(Product prod);

    ResultVO<?> update(Product prod);

    ResultVO<?> logicDelete(Integer id);

    ResultVO<?> batchLogicDelete(String ids);

    ResultVO<?> findById(Integer id);

    ResultVO<?> findByCode(String code);

    ResultVO<?> findAll();

}
