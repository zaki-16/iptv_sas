package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.ProductControllerListVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.repository.BusinessRepository;
import com.hgys.iptv.repository.ProductBusinessRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName ProductBusinessListAssemlber
 * @Auther: wangz
 * @Date: 2019/5/10 09:31
 * @Description: TODO
 */
@Component
public class ProductBusinessListAssemlber {
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private ProductBusinessRepository productBusinessRepository;

    public ProductControllerListVM getListVM(Product vo){
        ProductControllerListVM vm = new ProductControllerListVM();
        BeanUtils.copyProperties(vo,vm);
        Set<Integer> bidSet= productBusinessRepository.findAllBid(vo.getId());
        List<Business> businessList = businessRepository.findAllById(bidSet);
        List<ProductControllerListVM.Business> list = new ArrayList<>();
        for (Business b : businessList){
            ProductControllerListVM.Business pb_ = new ProductControllerListVM.Business();
            BeanUtils.copyProperties(b,pb_);
            list.add(pb_);
        }
        vm.setList(list);
        return vm;
    }

}
