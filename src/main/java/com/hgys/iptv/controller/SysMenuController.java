package com.hgys.iptv.controller;

import com.hgys.iptv.model.dto.SysMenuDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysMenuController
 * @Auther: wangz
 * @Date: 2019/5/19 14:44
 * @Description: TODO 用来为菜单结点关联权限选择范围，不是真的授权
 */
@RestController
@RequestMapping("/menu")
@Api(value = "SysMenuController",tags = "系统菜单管理Api接口")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 增删改
     */
    @GetMapping("/loadMenuTree")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO loadMenuTree(){
        return sysMenuService.loadMenuTree();
    }

    @GetMapping("/loadPermByMenuId")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO loadPermByMenuId(Integer id){
        return sysMenuService.loadPermByMenuId(id);
    }

    @PostMapping("/createMenuNode")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO createMenuNode(SysMenuDTO sysMenuDTO){
        return sysMenuService.createMenuNode(sysMenuDTO);
    }

    @PostMapping("/updateMenuNode")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO updateMenuNode(SysMenuDTO sysMenuDTO){
        return sysMenuService.updateMenuNode(sysMenuDTO);
    }

    @PostMapping("/deleteMenuNodeById")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO deleteMenuNodeById(Integer id){
        return sysMenuService.deleteMenuNodeById(id);
    }
}
