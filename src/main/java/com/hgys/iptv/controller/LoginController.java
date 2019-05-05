package com.hgys.iptv.controller;

import com.hgys.iptv.security.UserDetailsServiceImpl;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;
import com.hgys.iptv.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


/**
 * @author: wangzhen
 * @date:2019/4/19 17:35
 */
@Controller
@RequestMapping("/iptv")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @RequestMapping("/login")
    public String login(String name){
        userDetailsService.loadUserByUsername(name);
        return "pages/success";//thymeleaf会将路径映射成.html
    }

    //添加用户
    @PostMapping("/addUser")
    public User addUser(Map<String, Object> map) {
        return loginService.addUser(map);
    }
    //添加角色
    @PostMapping("/addRole")
    public Role addRole(Map<String, Object> map) {
        return loginService.addRole(map);
    }

}
