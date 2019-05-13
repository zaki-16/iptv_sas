package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.ProductVM;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.impl.ProductServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProductBusinessListAssemlber
 * @Auther: wangz
 * @Date: 2019/5/10 09:31
 * @Description: TODO
 */
@Component
public class ProductBusinessListAssemlber {
    @Autowired
    private ProductServiceImpl service;

    public ProductVM getListVM(Product vo){
        ProductVM vm = new ProductVM();
        BeanUtils.copyProperties(vo, vm);
        ResultVO<?> result = service.findById(vo.getId());
        return (ProductVM) result.getData();
    }


}
