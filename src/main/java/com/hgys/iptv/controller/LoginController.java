package com.hgys.iptv.controller;

import com.alibaba.fastjson.JSON;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.security.UserDetailsServiceImpl;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;
import com.hgys.iptv.service.LoginService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author: wangzhen
 * @date:2019/4/19 17:35
 */
@Controller
@RequestMapping(value={"/iptv","/"})
@Api(value = "LoginController",tags = "登录管理Api接口")
public class LoginController {
    @Autowired
    private LoginService loginService;
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
            @ApiParam(value = "登录名") String username,
            @ApiParam(value = "登录密码") String password){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(StringUtils.isBlank(password)){
            return JSON.toJSON(ResultVOUtil.error("1","密码不能为空!"));
        }else if(!password.trim().equals(userDetails.getPassword())){
            return JSON.toJSON(ResultVOUtil.error("1","用户或密码错误！"));
        }
        //userDetails.getAuthorities()
        return JSON.toJSON(ResultVOUtil.success("登录成功"));
    }

    /**
     * 用户控制
     * @param map
     * @return
     */
    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户,请求参数为表单json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User addUser(@RequestParam Map<String, Object> map) {
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
    public Role addRole(@RequestParam Map<String, Object> map) {
        return loginService.addRole(map);
    }

    @GetMapping("/findAllRoles")
    @ApiOperation(value = "查询角色列表",notes = "@return :角色列表")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<Role> findAllRole(){
        return ResultVOUtil.success(loginService.findAllRole());
    }

}
