package com.hgys.iptv.service;

import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.dto.SysUserDTO;
import com.hgys.iptv.model.vo.ResultVO;

import java.util.List;

public interface SysUserService {
    /**
     * 系统用户管理
     * ----------------------------------
     */
    //根据用户名查用户信息--包含角色集
    ResultVO findByUserName(String username);
    ResultVO addUser(SysUserDTO userDTO);
    ResultVO updateUser(SysUserDTO userDTO);
    ResultVO deleteUserById(Integer id);
    ResultVO findAllUser();
    ResultVO modifyPassword(String username,String password_old,String password_new1,String password_new2);
    List<Role> findAllRoleByUserId(Integer userId);
}
