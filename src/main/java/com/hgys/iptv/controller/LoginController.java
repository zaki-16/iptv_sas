package com.hgys.iptv.controller;

import com.alibaba.fastjson.JSON;
import com.hgys.iptv.model.User;
import com.hgys.iptv.model.bean.UserSessionInfo;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.UserRepository;
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
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author: wangzhen
 * @date:2019/4/19 17:35
 */
@RestController
@RequestMapping(value={"/iptv","/"})
@Api(value = "LoginController",tags = "登录管理Api接口")
public class LoginController {

    private final String CURRENT_USER = "CURRENT_USER";
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String index(){
        return "main";
    }

    @GetMapping("/login")
    public ResultVO login4get( @ApiParam(value = "登录名") String username,
                             @ApiParam(value = "登录密码") String password,
                             HttpServletRequest request){
        return login(username,password,request);
    }
    @PostMapping(value="/login")//loginIdentity
    @ApiOperation(value = "登录请求(管理员用户密码:admin/admin)")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO login(
            @ApiParam(value = "登录名")
            @RequestParam("username")String username,
            @ApiParam(value = "登录密码")
            @RequestParam("password")String password,
            HttpServletRequest request){
        if(StringUtils.isBlank(password)){
            return ResultVOUtil.error("1","密码不能为空!");
//            return JSON.toJSON(ResultVOUtil.error("1","密码不能为空!"));
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println(userDetails.getUsername()+"具有的权限 ： "+userDetails.getAuthorities());

        if(!passwordEncoder.matches(password.trim(),userDetails.getPassword())){
            return ResultVOUtil.error("1","密码不能为空!");
//            return JSON.toJSON(ResultVOUtil.error("1","用户或密码错误！"));
        }
        System.out.println(request.getParameter("username"));
        /**
         * 登录成功后记录会话
         */
        User byUsername = userRepository.findByUsername(username);//已注册用户
        UserSessionInfo info = new UserSessionInfo();
        //获取用户 ip
        String ipAddr = getIpAddr(request);
        info.setIp(ipAddr);
        info.setUser(byUsername);
        //以下信息是为了方便获取
        info.setLoginName(byUsername.getUsername());
        info.setRealName(byUsername.getRealName());
        info.setDisplayName(byUsername.getDisplayName());
        //存 session
        request.getSession().setAttribute(CURRENT_USER,info);
        // 将session存进cookie
        Cookie cookie = new Cookie(username,request.getSession().getId());
        cookie.setMaxAge(60*30);//30分钟
        return ResultVOUtil.success("登录成功");
    }


    @GetMapping("/logout")
    public ResultVO logout(HttpSession session) {
        if (session != null) {
            session.invalidate();//调用session的invalidate()方法，将保存的session删除
        }
        return ResultVOUtil.success("退出登录成功!");
    }
        /**
         * 获取登录用户IP地址
         *
         * @param request
         * @return
         */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "localhost";
        }
        return ip;
    }

    @GetMapping("/testSession")
    public UserSessionInfo getUserSessionInfo(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
        UserSessionInfo info = (UserSessionInfo) session.getAttribute(CURRENT_USER);
        return info;
    }

}
