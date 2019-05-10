package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.controller.vm.ProductControllerListVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.repository.BusinessRepository;
import com.hgys.iptv.repository.CpProductRepository;
import com.hgys.iptv.repository.ProductBusinessRepository;
import com.hgys.iptv.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName CpProductListAssemlber
 * @Auther: wangz
 * @Date: 2019/5/10 11:26
 * @Description: TODO
 */
@Component
public class CpProductListAssemlber {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CpProductRepository cpProductRepository;

    public CpControllerListVM getListVM(Cp vo){
        CpControllerListVM vm = new CpControllerListVM();
        BeanUtils.copyProperties(vo,vm);
        Set<Integer> pidSet= cpProductRepository.findAllPid(vo.getId());
//        找出cpid下的所有产品
        List<Product> productList = productRepository.findAllById(pidSet);
        List<CpControllerListVM.Product> list = new ArrayList<>();
        for (Product p : productList){
            CpControllerListVM.Product cpp_ = new CpControllerListVM.Product();
            BeanUtils.copyProperties(p,cpp_);
            list.add(cpp_);
        }
        vm.setList(list);
        return vm;
    }

}
