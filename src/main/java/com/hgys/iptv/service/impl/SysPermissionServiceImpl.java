package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Permission;
import com.hgys.iptv.model.dto.SysPermissionDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SysPermissionService;
import com.hgys.iptv.util.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName SysPermissionServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/16 19:29
 * @Description: TODO
 */
@Service
public class SysPermissionServiceImpl extends SysServiceImpl implements SysPermissionService {
    @Override
    public ResultVO findByPermissionName(String name) {
        Permission byName = permissionRepository.findByName(name);
        if(byName==null){
            return ResultVOUtil.error("1","该权限不存在！");
        }
        return ResultVOUtil.success(byName);
    }

    /**
     * 新增权限
     * @param sysPermissionDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO addPermission(SysPermissionDTO sysPermissionDTO) {
        if(StringUtils.isBlank(sysPermissionDTO.getName())){
            return ResultVOUtil.error("1","权限名不能为空！");
        }
        //检查名称
        Permission byName = permissionRepository.findByName(sysPermissionDTO.getName());
        if(byName!=null)
            return ResultVOUtil.error("1","权限名已存在！");
        // 添加角色--关联权限
        try {
            Permission permission = new Permission();
            // 状态0:启用，1：禁用--默认新增时就启用
            if(sysPermissionDTO.getStatus()==null || sysPermissionDTO.getStatus()!=1)
                permission.setStatus(0);
            BeanUtils.copyProperties(sysPermissionDTO,permission);
            permission.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            permissionRepository.save(permission);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","新增权限异常！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO updatePermission(SysPermissionDTO sysPermissionDTO) {
        if (!(sysPermissionDTO.getId() != null && sysPermissionDTO.getId() > 0)) {
            return ResultVOUtil.error("1", "用户id不能为空！");
        }
        return this.addPermission(sysPermissionDTO);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO deletePermissionById(Integer id) {
        try {
            permissionRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","新增权限异常！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     *
     * @return
     */
    @Override
    public ResultVO findAllPermission() {
        List<Permission> all = permissionRepository.findAll();
        if(all.size()>0)
            return ResultVOUtil.success(all);
        return ResultVOUtil.error("1","所查询列表不存在!");
    }

}
