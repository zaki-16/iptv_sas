package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.ProductControllerListVM;
import com.hgys.iptv.controller.vm.ProductListVM;
import com.hgys.iptv.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProductListVMAssemlber
 * @Auther: wangz
 * @Date: 2019/5/9 17:52
 * @Description: TODO
 */
@Component
public class ProductListVMAssemlber {
    public ProductListVM getListVM(Product product) {
        ProductListVM vm = new ProductListVM();
        BeanUtils.copyProperties(product, vm);
        return vm;
    }
}
