package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.ProductAddVM;
import com.hgys.iptv.controller.vm.ProductControllerListVM;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ResultVO<?> save(ProductAddVM prod);

    ResultVO<?> update(Product prod);

//    ResultVO<?> logicDelete(Integer id);

    ResultVO<?> batchLogicDelete(String ids);

    ResultVO<?> findById(Integer id);

    ResultVO<?> findByCode(String code);

    ResultVO<?> findAll();

    Page<ProductControllerListVM> findByConditions(String name, String code, Integer status, Pageable pageable);


}
