package com.hgys.iptv.controller;

import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysMenuController
 * @Auther: wangz
 * @Date: 2019/5/19 14:44
 * @Description: TODO
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/loadMenuTree")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO loadMenuTree(){
        return sysMenuService.loadMenuTree();
    }
}
