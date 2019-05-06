package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Product;
import com.hgys.iptv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: wangz
 * @Date: 2019/5/5 17:15
 * @Description:/
 */
@Service
public class ProductServiceImpl{
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }
    public void delete(Product product){ productRepository.delete(product); }

    public Product findByPid(Integer pid){
        return productRepository.findByPid(pid);
    }
    public Product findByCode(String code){
        return productRepository.findByCode(code);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
