package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Business;
import com.hgys.iptv.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: wangz
 * @Date: 2019/5/5 18:05
 * @Description:
 */
@Service
public class BusinessServiceImpl {
    @Autowired
    private BusinessRepository businessRepository;

    public Business save(Business business){
        return businessRepository.save(business);
    }
    public void delete(Business business){ businessRepository.delete(business); }
    public Business findByBid(Integer bid){
        return businessRepository.findByBid(bid);
    }
    public Business findByCode(String code){
        return businessRepository.findByCode(code);
    }
    public List<Business> findAll(){
        return businessRepository.findAll();
    }
}
