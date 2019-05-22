package com.hgys.iptv.controller;

import com.hgys.iptv.model.User;
import com.hgys.iptv.model.bean.UserSessionInfo;
import com.hgys.iptv.model.enums.LogResultEnum;
import com.hgys.iptv.model.enums.LogTypeEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.security.UserDetailsServiceImpl;
import com.hgys.iptv.service.SysUserService;
import com.hgys.iptv.util.Logger;
import com.hgys.iptv.util.ReqAndRespHolder;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private Logger logger;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private SysUserService sysUserService;

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
        //

        /**
         * 登录成功后记录会话
         */
        HttpServletRequest request = ReqAndRespHolder.getRequest();
        String ipAddr = ReqAndRespHolder.getIpAddr(request);

        UserSessionInfo info = new UserSessionInfo();
        info.setIp(ipAddr);
        info.setLoginName(userDetails.getUsername());
        //获取 user ，记录真实姓名
        User byUserName = (User)sysUserService.findByUserName(userDetails.getUsername()).getData();
        info.setRealName(byUserName.getRealName());
        //用户信息存 session
        request.getSession().setAttribute(userDetails.getUsername(),info);

        SecurityContext context = SecurityContextHolder.getContext();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        context.setAuthentication(token);
//        Cookie cookie = new Cookie(username,request.getSession().getId());
//        cookie.setMaxAge(60*30);//30分钟
        logger.log(LogTypeEnum.LOGIN.getType(), LogResultEnum.SUCCESS.getResult());
        return ResultVOUtil.success("登录成功");
    }


    @GetMapping("/logout")
    public ResultVO logout() {
        logger.log(LogTypeEnum.LOGOUT.getType(), LogResultEnum.SUCCESS.getResult());
        HttpSession session = ReqAndRespHolder.getSession();
        if (session != null) {
            session.invalidate();//将保存的session删除
        }
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if(authentication!=null)
            context.setAuthentication(null);
        return ResultVOUtil.success("退出登录成功!");
    }



//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping("/test1")
//    public ResultVO addUser() {
//        String username = ReqAndRespHolder.getRequest().getParameter("username");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("test hasRole success................" );
//        return ResultVOUtil.success("test hasRole success");
//    }
//
//    @PreAuthorize(value = "hasPermission('cpMenu', 'view')")
//    @PostMapping("/test2")
//    public ResultVO testadd() {
//        System.out.println("test hasPermission success................" );
//        return ResultVOUtil.success("test hasPermission success");
//    }


}
