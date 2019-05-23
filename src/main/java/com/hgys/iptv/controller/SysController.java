package com.hgys.iptv.controller;

import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;
import com.hgys.iptv.model.dto.SysPermissionDTO;
import com.hgys.iptv.model.dto.SysRoleDTO;
import com.hgys.iptv.model.dto.SysUserDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SysPermissionService;
import com.hgys.iptv.service.SysRoleService;
import com.hgys.iptv.service.SysUserService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    3.批量启用、停用功能
    4.密码重置
     */

    @GetMapping("/findAllUser")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "查询用户列表")
    public Page<User> findAllUser(String username, String realName,
                                  Integer status,
                                  @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")Integer pageNum,
                                  @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")Integer pageSize
                                  ) {
        return sysUserService.findAllUserOfPage(username,realName,status,pageNum,pageSize);
    }

    @GetMapping("/findByUserName")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "根据用户名查询")
    public ResultVO findByUserName(String username) {
        return sysUserService.findByUserName(username);
    }

    /**
     * 查看用户及关联的角色
     * @param id
     * @return
     */
    @GetMapping("/findUserById")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO findUserById(Integer id) {
        return sysUserService.findUserById(id);
    }


    /**
     * 新增用户
     * @param sysUserDTO
     * @return
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO addUser(@RequestBody(required = false) SysUserDTO sysUserDTO) {
        return sysUserService.addUser(sysUserDTO);
    }

    @PostMapping("/updateUser")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "更新用户")
    public ResultVO updateUser(@RequestBody SysUserDTO sysUserDTO) {
        return sysUserService.updateUser(sysUserDTO);
    }

    @DeleteMapping("/batchLogicDeleteUser")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO batchLogicDeleteUser(String ids) {
        return sysUserService.batchLogicDelete(ids);
    }

    //
    @PostMapping("/modifyPassword")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "修改密码")
    public ResultVO modifyPassword(String username,String password_old,String password_new1,String password_new2) {
        return sysUserService.modifyPassword(username,password_old,password_new1,password_new2);
    }

    @PostMapping("/resetPassword")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "重置密码")
    public ResultVO resetPassword(String username) {
        return sysUserService.resetPassword(username);
    }


    /*
    --------------------------------角色------------------------------------------
   新增角色时，先加载菜单树+权限范围列表。新增操作是向
     */
    @GetMapping("/findAllRole")
    @ApiOperation(value = "查询角色列表",notes = "@return :角色列表")
    @ResponseStatus(HttpStatus.OK)
    public Page<Role> findAllRole(String name, Integer status,
                                  @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")Integer pageNum,
                                  @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")Integer pageSize) {
        return sysRoleService.findAllRoleOfPage(name,status,pageNum,pageSize);
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

    @GetMapping("/findRoleById")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO findRoleById(Integer id) {
        return sysRoleService.findRoleById(id);
    }

    @PostMapping("/updateRole")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO updateRole(@RequestBody SysRoleDTO sysRoleDTO) {
        return sysRoleService.updateRole(sysRoleDTO);
    }

    @DeleteMapping("/batchLogicDeleteRole")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO batchLogicDeleteRole(String ids) {
        return sysRoleService.batchLogicDelete(ids);
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
