package com.hgys.iptv.service;

import com.hgys.iptv.model.Authority;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.dto.SysRoleDTO;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SysRoleService {
    /**
     * 系统角色管理
     * ----------------------------------
     */
    //根据角色名查角色信息--包含用户+权限集
    ResultVO findByRoleName(String name);
    ResultVO addRole(SysRoleDTO sysRoleDTO);
    ResultVO updateRole(SysRoleDTO sysRoleDTO);
    ResultVO deleteRoleById(Integer id);
    ResultVO batchLogicDelete(String ids);
    ResultVO batchOnRole(String ids);
    ResultVO batchOffRole(String ids);
    ResultVO findAllRole();
    Page<Role> findAllRoleOfPage(String name, Integer status, Integer pageNum, Integer pageSize);
    ResultVO findRoleById(Integer id);
    List<Authority> findAllAuthorityByRoleId(Integer roleId);
}
