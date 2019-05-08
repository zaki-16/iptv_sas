//package com.hgys.iptv.service.impl;
//
//import com.hgys.iptv.repository.OperatorRepository;
//import com.hgys.iptv.model.Operator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class OperatorServiceImpl {
//    @Autowired
//    private OperatorRepository operatorRepository;
//
//    public Operator findByOpNm(String name){
//        return operatorRepository.findByOpNm(name);
//    }
//
//    public void save(Operator op){
//        Operator op1 =operatorRepository.save(op);
//        System.out.println(op1.getOpNm());
//    }
//
//    public void delete(Operator op){
//        operatorRepository.delete(op);
//    }
//
//    public List<Operator> findAll(){
//        return operatorRepository.findAll();
//    }
//}
