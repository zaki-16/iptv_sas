package com.hgys.iptv.service;

import com.hgys.iptv.model.dto.SysPermissionDTO;
import com.hgys.iptv.model.vo.ResultVO;

public interface SysPermissionService {
    /**
     * 系统权限管理
     * ----------------------------------
     */
    //根据权限名查权限信息--包含角色集
    ResultVO findByPermissionName(String name);
    ResultVO addPermission(SysPermissionDTO sysPermissionDTO);
    ResultVO updatePermission(SysPermissionDTO sysPermissionDTO);
    ResultVO deletePermissionById(Integer id);
    ResultVO findAllPermission();
}
