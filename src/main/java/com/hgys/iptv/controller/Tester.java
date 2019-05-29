//package com.hgys.iptv.controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hgys.iptv.IptvSasApplication;
//import com.hgys.iptv.model.Authority;
//import com.hgys.iptv.repository.AuthorityRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @ClassName Tester
// * @Auther: wangz
// * @Date: 2019/5/28 11:39
// * @Description: TODO
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = IptvSasApplication.class)
//public class Tester {
//    @Autowired
//    private AuthorityRepository authorityRepository;
//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;
//    @Test
//    public void test(){
//        List<Authority> all = authorityRepository.findAll();
//        all.stream().filter(authority -> "cpManager:view".equals(authority.getName()))
//                .collect(Collectors.toList());
//        System.out.println(all);
//    }
//
//    @Test
//    public void testRedis()throws Exception{
//        //1.从redis读数据--json
//        String authListJson = redisTemplate.boundValueOps("authList").get();
//        //2.若redis没有，则读库
//        if(null==authListJson) {
//            List<Authority> list = authorityRepository.findAll();
//            //3.缓存--先将 list 转为json字符串 --使用jackson进行对象和json的转化
//            ObjectMapper objectMapper = new ObjectMapper();
//            authListJson = objectMapper.writeValueAsString(list);
//            redisTemplate.boundValueOps("authList").set(authListJson);
//            System.out.println("=========从数据库中获取auth数据=============");
//        }else {
//            System.out.println("=========从redis中获取auth数据=============");
//        }
//        System.out.println(authListJson);
//    }
//}
