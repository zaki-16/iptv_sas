package com.hgys.iptv.controller;

import com.hgys.iptv.model.User;
import com.hgys.iptv.model.bean.UserSessionInfo;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.UserRepository;
import com.hgys.iptv.security.UserDetailsServiceImpl;
import com.hgys.iptv.util.ReqAndRespHolder;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;


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
                             @ApiParam(value = "登录密码") String password){
        return login(username,password);
    }
    @PostMapping(value="/login")//loginIdentity
    @ApiOperation(value = "登录请求(管理员用户密码:admin/admin)")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO login(
            @ApiParam(value = "登录名")
            @RequestParam("username")String username,
            @ApiParam(value = "登录密码")
            @RequestParam("password")String password){
        if(StringUtils.isBlank(password)){
            return ResultVOUtil.error("1","密码不能为空!");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(!passwordEncoder.matches(password.trim(),userDetails.getPassword())){
            return ResultVOUtil.error("1","用户或密码错误！");
        }

        /**
         * 登录成功后记录会话
         */
        HttpServletRequest request = ReqAndRespHolder.getRequest();
        User byUsername = userRepository.findByUsername(username);//已注册用户
        UserSessionInfo info = new UserSessionInfo();
        String ipAddr = ReqAndRespHolder.getIpAddr(request);
//        BeanUtils.copyProperties(byUsername,info);
        info.setIp(ipAddr);
        info.setId(byUsername.getId());
        info.setLoginName(byUsername.getUsername());
        info.setRealName(byUsername.getRealName());
        info.setDisplayName(byUsername.getDisplayName());
        info.setUser(byUsername);
        info.setAuthorities(userDetails.getAuthorities());
        //存 session
        request.getSession().setAttribute(CURRENT_USER,info);

//        Cookie cookie = new Cookie(username,request.getSession().getId());
//        cookie.setMaxAge(60*30);//30分钟

        return ResultVOUtil.success("登录成功");
    }


    @GetMapping("/logout")
    public ResultVO logout(HttpSession session) {
        if (session != null) {
            session.invalidate();//调用session的invalidate()方法，将保存的session删除
        }
        return ResultVOUtil.success("退出登录成功!");
    }



    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/testaddUser")
    public void addUser() {
        System.out.println("addUser................" );
    }

    @PreAuthorize(value = "hasPermission('cpMenu', 'view') or hasRole('ROLE_ADMIN')")
    @GetMapping("/testadd")
    public void testadd() {
        System.out.println("testadd................" );
    }


}
