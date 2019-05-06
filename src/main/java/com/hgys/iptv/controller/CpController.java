package com.hgys.iptv.controller;

import com.hgys.iptv.model.Cp;
import com.hgys.iptv.service.impl.CpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: wangz
 * @Date: 2019/5/5 18:17
 * @Description:
 */
@RestController()
@RequestMapping("/cp")
public class CpController {
    @Autowired
    private CpServiceImpl cpService;

//    @GetMapping("/saveCp")
//    public CP save(CP cp){
//        return cpService.save(cp);
//    }
//    @GetMapping("/deleteCp")
//    public void delete(CP cp){ cpService.delete(cp); }
////    @GetMapping("/findByCpid")

////    public CP findByBid(Integer cpid){
////        return cpService.findById(1);
////    }
//    @GetMapping("/findByCode")
//    public CP findByCode(String code){
//        return cpService.findByCode(code);
//    }
    @GetMapping("/findAll")
    public List<Cp> findAll(){
        return cpService.findAll();
    }

}
