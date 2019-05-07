package com.hgys.iptv.controller;

import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.security.UserDetailsServiceImpl;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;
import com.hgys.iptv.service.LoginService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author: wangzhen
 * @date:2019/4/19 17:35
 */
@Controller
@RequestMapping("/iptv")
@Api(value = "LoginController",tags = "登录管理Api接口")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    @ApiOperation(value = "登录请求(管理员用户密码:admin/admin)")
    @ResponseStatus(HttpStatus.OK)
    public String login(String name){
        userDetailsService.loadUserByUsername(name);
        return "pages/success";//thymeleaf会将路径映射成.html
    }

    /**
     * 用户控制
     * @param map
     * @return
     */
    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户,请求参数为表单json")
    @ResponseStatus(HttpStatus.OK)
    public User addUser(Map<String, Object> map) {
        return loginService.addUser(map);
    }


    /**
     * 角色控制
     * @param map
     * @return
     */
    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色,请求参数为表单json")
    @ResponseStatus(HttpStatus.OK)
    public Role addRole(Map<String, Object> map) {
        return loginService.addRole(map);
    }

    @GetMapping("/findAllRoles")
    @ApiOperation(value = "查询角色列表",notes = "@return :角色列表")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<Role> findAllRole(){
        return ResultVOUtil.success(loginService.findAllRole());
    }

}
