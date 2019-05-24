package com.hgys.iptv.service;

import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;
import com.hgys.iptv.model.dto.SysUserDTO;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SysUserService {
    /**
     * 系统用户管理
     * ----------------------------------
     */
    //根据用户名查用户信息--包含角色集
    ResultVO findByUserName(String username);
    ResultVO findUserById(Integer id);
    ResultVO addUser(SysUserDTO userDTO);
    ResultVO updateUser(SysUserDTO userDTO);
    ResultVO personalUpdate(SysUserDTO userDTO);
    ResultVO deleteUserById(Integer id);
    ResultVO batchLogicDelete(String ids);
    //    ResultVO findAllUser();
    Page<User> findAllUserOfPage(String username,String realName,String cpAbbr,Integer status,Integer pageNum, Integer pageSize);
    ResultVO modifyPassword(String password_old,String password_new);
    ResultVO resetPassword(String username);
    List<Role> findAllRoleByUserId(Integer userId);
}
