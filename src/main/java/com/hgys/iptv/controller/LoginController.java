package com.hgys.iptv.controller;

import com.alibaba.fastjson.JSON;
import com.hgys.iptv.security.UserDetailsServiceImpl;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;


/**
 * @author: wangzhen
 * @date:2019/4/19 17:35
 */
@Controller
@RequestMapping(value={"/iptv","/"})
@Api(value = "LoginController",tags = "登录管理Api接口")
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/index")
    public String index(){
        return "main";
    }

    @GetMapping("/login")
    public Object login4get( @ApiParam(value = "登录名") String username,
                             @ApiParam(value = "登录密码") String password){
        return login(username,password);
    }
    @PostMapping(value="/login")//loginIdentity
    @ApiOperation(value = "登录请求(管理员用户密码:admin/admin)")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object login(
            @ApiParam(value = "登录名")
            @RequestParam("username")String username,
            @ApiParam(value = "登录密码")
            @RequestParam("password")String password){
        if(StringUtils.isBlank(password)){
            return JSON.toJSON(ResultVOUtil.error("1","密码不能为空!"));
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println(userDetails.getUsername()+"具有的权限 ： "+userDetails.getAuthorities());

        if(!passwordEncoder.matches(password.trim(),userDetails.getPassword())){
            return JSON.toJSON(ResultVOUtil.error("1","用户或密码错误！"));
        }

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
        HttpSession session= attr.getRequest().getSession(true); // true == allow create
        System.out.println(session.getAttributeNames());
        test();
        return JSON.toJSON(ResultVOUtil.success("登录成功"));
    }


    @PostMapping("/session")
    public void test(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getPrincipal());
    }
}
