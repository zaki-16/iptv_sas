//package com.hgys.iptv.controller;
//
//import com.hgys.iptv.model.dto.SysRoleDTO;
//import com.hgys.iptv.model.dto.SysUserDTO;
//import com.hgys.iptv.model.vo.ResultVO;
//import com.hgys.iptv.service.SysService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
///**
// * 系统用户、角色、权限管理
// * @ClassName SysController
// * @Auther: wangz
// * @Date: 2019/5/16 11:29
// * @Description: TODO
// */
//@RestController
//@RequestMapping("/sys")
//@Api(value = "SysController",tags = "系统用户、角色、权限管理Api接口")
//public class SysController {
//
//    @Autowired
//    private SysService sysService;
//
//    /*
//    --------------------------------用户------------------------------------------
//    ResultVO findByUserName(String username);
//    ResultVO addUser(SysUserDTO userDTO);
//    ResultVO updateUser(SysUserDTO userDTO);
//    ResultVO deleteUser(String username);
//    ResultVO findAllUser();
//     */
//
//    @GetMapping("/findAllUser")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultVO findAllUser() {
//        return sysService.findAllUser();
//    }
//
//    @GetMapping("/findByUserName")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultVO findByUserName(String username) {
//        return sysService.findByUserName(username);
//    }
//
//    @ApiOperation(value = "添加用户,请求参数为表单json")
//    @PostMapping("/addUser")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResultVO addUser(@RequestBody SysUserDTO sysUserDTO) {
//        return sysService.addUser(sysUserDTO);
//    }
//
//    @PostMapping("/updateUser")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultVO updateUser(@RequestBody SysUserDTO sysUserDTO) {
//        return sysService.updateUser(sysUserDTO);
//    }
//
//    @DeleteMapping("/deleteUserById")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultVO deleteUserById(Long id) {
//        return sysService.deleteUserById(id);
//    }
//
//
//    /*
//    --------------------------------角色------------------------------------------
//     */
//    @GetMapping("/findAllRole")
//    @ApiOperation(value = "查询角色列表",notes = "@return :角色列表")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultVO findAllRole() {
//        return sysService.findAllRole();
//    }
//    /**
//     * 角色控制
//     * @param sysRoleDTO
//     * @return
//     */
//    @PostMapping("/addRole")
//    @ApiOperation(value = "添加角色,请求参数为表单json")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultVO addRole(@RequestBody SysRoleDTO sysRoleDTO) {
//        return sysService.addRole(sysRoleDTO);
//    }
//
//    /*
//    --------------------------------权限------------------------------------------
//     */
//    @GetMapping("/findAllPermission")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultVO findAllPermission() {
//        return sysService.findAllPermission();
//    }
//
//
//
//}
