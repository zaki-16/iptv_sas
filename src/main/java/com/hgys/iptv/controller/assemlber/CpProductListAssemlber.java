package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.repository.BusinessRepository;
import com.hgys.iptv.repository.CpBusinessRepository;
import com.hgys.iptv.repository.CpProductRepository;
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
    ProductRepository productRepository;
    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    CpProductRepository cpProductRepository;
    @Autowired
    CpBusinessRepository cpBusinessRepository;

    public CpControllerListVM getListVM(Cp vo){
        CpControllerListVM vm = new CpControllerListVM();
        BeanUtils.copyProperties(vo,vm);

        Set<Integer> pidSet= cpProductRepository.findAllPid(vo.getId());
//        找出cpid下的所有产品
        List<Product> productList = productRepository.findAllById(pidSet);
        List<CpControllerListVM.Product> pList = new ArrayList<>();
        for (Product p : productList){
            CpControllerListVM.Product cpp_ = new CpControllerListVM.Product();
            BeanUtils.copyProperties(p,cpp_);
            pList.add(cpp_);
        }
        vm.setpList(pList);

        Set<Integer> bidSet = cpBusinessRepository.findAllBid(vo.getId());
        List<Business> businessList = businessRepository.findAllById(bidSet);
        List<CpControllerListVM.Business> bList = new ArrayList<>();
        businessList.forEach(b->{
            CpControllerListVM.Business cpb_ = new CpControllerListVM.Business();
            BeanUtils.copyProperties(b,cpb_);
            bList.add(cpb_);
        });
        vm.setbList(bList);
        return vm;
    }

}
