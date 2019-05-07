package com.hgys.iptv.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: wangzhen
 * @date:2019/4/21 10:16
 */
@Controller
@RequestMapping("/page2")
@Api(value = "PageToController",tags = "页面跳转管理Api接口")
public class PageToController {
    private final String PREFIX = "pages/";

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/index1")
    public String index1() {
        return "user";
    }

    @GetMapping("/loginHtml")
    public String indexHtml() {
        return PREFIX+"login";
    }

    @GetMapping(value = "/logout")
    public String logout(){
        return "logout";
    }

    //未授权
    @GetMapping(value = "/login403")
    public String login403(){
        return PREFIX+"unauth";
    }

    @GetMapping(value = "/loginFailHtml")
    public String error(){
        return PREFIX+"finance";
    }

    @GetMapping("/loginSuccessHtml")
    public String success(){
        return PREFIX+"success";
    }

    // 属于user角色@RequiresRoles("user")
    // 必须同时属于user和admin角@RequiresRoles({ "user", "admin" })
    // 属于user或者admin之一;修改logical为OR 即可@RequiresRoles(value = { "user", "admin"},
    // logical = Logical.OR)
    @GetMapping("/showUserHtml")
    public String userHtml() {
        return PREFIX+"user";
    }


    @GetMapping("/showAdminHtml")
    public String adminHtml() {
        System.out.println("showAdminHtml has in !");
        return PREFIX+"admin";
    }

}
