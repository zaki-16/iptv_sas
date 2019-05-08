package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.BusinessControllerListVM;
import com.hgys.iptv.controller.vm.ProductControllerListVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProductControllerAssember
 * @Auther: wangz
 * @Date: 2019/5/8 16:10
 * @Description: TODO
 */
@Component
public class ProductControllerAssemlber {
    public ProductControllerListVM getListVM(Product product) {
        ProductControllerListVM vm = new ProductControllerListVM();
        BeanUtils.copyProperties(product, vm);
        return vm;
    }
}
