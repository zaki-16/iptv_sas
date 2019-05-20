package com.hgys.iptv.controller;

import com.hgys.iptv.model.dto.SysPermissionDTO;
import com.hgys.iptv.model.dto.SysRoleDTO;
import com.hgys.iptv.model.dto.SysUserDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SysPermissionService;
import com.hgys.iptv.service.SysRoleService;
import com.hgys.iptv.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户、角色、权限管理
 * @ClassName SysController
 * @Auther: wangz
 * @Date: 2019/5/16 11:29
 * @Description: TODO
 */
@RestController
@RequestMapping("/sys")
@Api(value = "SysController",tags = "系统用户、角色、权限管理Api接口")
public class SysController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

    /*
    --------------------------------用户------------------------------------------
    缺：
    1.按条件：cp、登录名、真实姓名、状态 分页查询用户
    2.查看用户时，需要查关联的角色
    3.批量启用、停用功能
    4.密码重置
     */

    @GetMapping("/findAllUser")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "查询用户列表")
    public ResultVO findAllUser() {
        return sysUserService.findAllUser();
    }

    @GetMapping("/findByUserName")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "根据用户名查询")
    public ResultVO findByUserName(String username) {
        return sysUserService.findByUserName(username);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    //    @PreAuthorize("hasPermission('SAVE', 'UPDATE') or hasRole('ROLE_ADMIN')")
    public ResultVO addUser(@RequestBody(required = false) SysUserDTO sysUserDTO) {
        return sysUserService.addUser(sysUserDTO);
    }

    @PostMapping("/updateUser")
    @ResponseStatus(HttpStatus.OK)


    @ApiOperation(value = "更新用户")
    public ResultVO updateUser(@RequestBody SysUserDTO sysUserDTO) {
        return sysUserService.updateUser(sysUserDTO);
    }

    @DeleteMapping("/deleteUserById")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO deleteUserById(Integer id) {
        return sysUserService.deleteUserById(id);
    }

    //
    @PostMapping("/modifyPassword")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "修改密码")
    public ResultVO modifyPassword(String username,String password_old,String password_new1,String password_new2) {
        return sysUserService.modifyPassword(username,password_old,password_new1,password_new2);
    }


    /*
    --------------------------------角色------------------------------------------
    缺：
    1.按条件：角色名称、类型、状态 分页查询
    2.新增角色时，列出菜单树==一级单单：cp管理--二级菜单：cpXX管理--三级菜单：权限类型
    获取菜单树及最小菜单关联的权限范围。e.g 权限名==菜单 id1：id2：add
    角色跟
     */
    @GetMapping("/findAllRole")
    @ApiOperation(value = "查询角色列表",notes = "@return :角色列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO findAllRole() {
        return sysRoleService.findAllRole();
    }
    /**
     * 角色控制
     * @param sysRoleDTO
     * @return
     */
    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO addRole(@RequestBody SysRoleDTO sysRoleDTO) {
        return sysRoleService.addRole(sysRoleDTO);
    }

    @GetMapping("/findByRoleName")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO findByRoleName(String name) {
        return sysRoleService.findByRoleName(name);
    }

    @PostMapping("/updateRole")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO updateRole(@RequestBody SysRoleDTO sysRoleDTO) {
        return sysRoleService.updateRole(sysRoleDTO);
    }

    @DeleteMapping("/deleteRoleById")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO deleteRoleById(Integer id) {
        return sysRoleService.deleteRoleById(id);
    }

    /*
    --------------------------------权限------------------------------------------
     */
    @GetMapping("/findAllPermission")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO findAllPermission() {
        return sysPermissionService.findAllPermission();
    }

    @PostMapping("/addPermission")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO addPermission(@RequestBody SysPermissionDTO sysPermissionDTO) {
        return sysPermissionService.addPermission(sysPermissionDTO);
    }

    @GetMapping("/findByPermissionName")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO findByPermissionName(String name) {
        return sysPermissionService.findByPermissionName(name);
    }

    @PostMapping("/updatePermission")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO updatePermission(@RequestBody SysPermissionDTO sysPermissionDTO) {
        return sysPermissionService.updatePermission(sysPermissionDTO);
    }

    @DeleteMapping("/deletePermissionById")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO deletePermissionById(Integer id) {
        return sysPermissionService.deletePermissionById(id);
    }

}
