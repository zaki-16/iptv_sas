package com.hgys.iptv.controller;

import com.hgys.iptv.aop.SystemControllerLog;
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
import org.springframework.security.access.prepost.PreAuthorize;
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

    private static final String target_user = "用户管理";
    private static final String target_role = "角色管理";

    /*
    --------------------------------用户------------------------------------------
    3.批量启用、停用功能
     */

    @GetMapping("/findAllUser")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "查询用户列表")
    @PreAuthorize(value = "hasPermission('userManager', 'view')")
    public Page<User> findAllUser(String username, String realName,
                                  @ApiParam(value = "用户类型-传平台或cp的id") String cpId,
                                  Integer status,
                                  @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")Integer pageNum,
                                  @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")Integer pageSize
                                  ) {
        return sysUserService.findAllUserOfPage(username,realName,cpId,status,pageNum,pageSize);
    }

    @GetMapping("/findByUserName")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "根据用户名查询")
    @PreAuthorize(value = "hasPermission('userManager', 'view')")
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
    @PreAuthorize(value = "hasPermission('userManager', 'view')")
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
    @PreAuthorize(value = "hasPermission('userManager', 'add')")
    @SystemControllerLog(target = target_user,methodName = "SysController.addUser",type = "新增")
    public ResultVO addUser(@RequestBody(required = false) SysUserDTO sysUserDTO) {
        return sysUserService.addUser(sysUserDTO);
    }

    @PostMapping("/updateUser")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "更新用户")
    @PreAuthorize(value = "hasPermission('userManager', 'update')")
    @SystemControllerLog(target = target_user,methodName = "SysController.updateUser",type = "修改")
    public ResultVO updateUser(@RequestBody SysUserDTO sysUserDTO) {
        return sysUserService.updateUser(sysUserDTO);
    }

    @PostMapping("/personalUpdate")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "用户自己修改个性资料--不提供用户名、密码和id")
    @SystemControllerLog(target = target_user,methodName = "SysController.personalUpdate",type = "修改")
    public ResultVO personalUpdate(@RequestBody SysUserDTO sysUserDTO) {
        return sysUserService.personalUpdate(sysUserDTO);
    }

    @GetMapping("/getPersonalData")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取个性资料")
    public ResultVO getPersonalData() {
        return sysUserService.getPersonalData();
    }

    @DeleteMapping("/batchLogicDeleteUser")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('userManager', 'remove')")
    @SystemControllerLog(target = target_user,methodName = "SysController.batchLogicDeleteUser",type = "删除")
    public ResultVO batchLogicDeleteUser(String ids) {
        return sysUserService.batchLogicDelete(ids);
    }

    //
    @PostMapping("/modifyPassword")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "用户自己修改密码")
//    @PreAuthorize(value = "hasPermission('userManager', 'update')")
    public ResultVO modifyPassword(
            @RequestParam("password_old")String password_old,
            @RequestParam("password_new")String password_new) {
        return sysUserService.modifyPassword(password_old,password_new);
    }

    @PostMapping("/resetPassword")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "管理员修改密码，重置密码为 123456")
    @PreAuthorize(value = "hasPermission('userManager', 'update')")
    @SystemControllerLog(target = target_user,methodName = "SysController.resetPassword",type = "重置密码")
    public ResultVO resetPassword(String username) {
        return sysUserService.resetPassword(username);
    }

    @PostMapping("/batchOnUser")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('userManager', 'batchOn')")
    @SystemControllerLog(target = target_user,methodName = "SysController.batchOnUser",type = "批量启用")
    public ResultVO batchOnUser(String ids){
        return sysUserService.batchOnUser(ids);
    }

    @PostMapping("/batchOffUser")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('userManager', 'batchOff')")
    @SystemControllerLog(target = target_user,methodName = "SysController.batchOffUser",type = "批量停用")
    public ResultVO batchOffUser(String ids){
        return sysUserService.batchOffUser(ids);
    }

    /*
    --------------------------------角色------------------------------------------
     */
//    @GetMapping("/findAllRole")
//    @ApiOperation(value = "查询角色列表",notes = "@return :角色列表")
//    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize(value = "hasPermission('roleManager', 'view')")
//    public ResultVO findAllRole() {
//            return sysRoleService.findAllRole();
//    }

    @GetMapping("/findAllRole")
    @ApiOperation(value = "查询角色列表",notes = "@return :角色列表")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('roleManager', 'view')")
    public Page<Role> findAllRoleOfPage(String name, Integer status,
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
    @PreAuthorize(value = "hasPermission('roleManager', 'add')")
    @SystemControllerLog(target = target_role,methodName = "SysController.addRole",type = "新增")
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
    @PreAuthorize(value = "hasPermission('roleManager', 'update')")
    @SystemControllerLog(target = target_role,methodName = "SysController.updateRole",type = "修改")
    public ResultVO updateRole(@RequestBody SysRoleDTO sysRoleDTO) {
        return sysRoleService.updateRole(sysRoleDTO);
    }

    @DeleteMapping("/batchLogicDeleteRole")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('roleManager', 'remove')")
    @SystemControllerLog(target = target_role,methodName = "SysController.batchLogicDeleteRole",type = "删除")
    public ResultVO batchLogicDeleteRole(String ids) {
        return sysRoleService.batchLogicDelete(ids);
    }


    @PostMapping("/batchOnRole")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('roleManager', 'batchOn')")
    @SystemControllerLog(target = target_role,methodName = "SysController.batchOnRole",type = "批量启用")
    public ResultVO batchOnRole(String ids){
        return sysRoleService.batchOnRole(ids);
    }

    @PostMapping("/batchOffRole")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(value = "hasPermission('roleManager', 'batchOff')")
    @SystemControllerLog(target = target_role,methodName = "SysController.batchOffRole",type = "批量停用")
    public ResultVO batchOffRole(String ids){
        return sysRoleService.batchOffRole(ids);
    }
    /*
    --------------------------------虚权限------------------------------------------
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
