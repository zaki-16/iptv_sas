package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.vm.SysMenuListVM;
import com.hgys.iptv.model.Permission;
import com.hgys.iptv.model.SysMenu;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.PermissionRepository;
import com.hgys.iptv.repository.SysMenuPermissionRepository;
import com.hgys.iptv.repository.SysMenuRepository;
import com.hgys.iptv.service.SysMenuService;
import com.hgys.iptv.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName SysMenuServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/19 14:47
 * @Description: TODO
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuRepository sysMenuRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private SysMenuPermissionRepository sysMenuPermissionRepository;

    @Override
    public ResultVO loadMenuTree() {
        ArrayList<SysMenuListVM> sysMenuListVMs = new ArrayList<>();
        // 加载菜单
        List<SysMenu> sysMenus = sysMenuRepository.findAll();
        // 关联子菜单权限列表
        //1.遍历;按(子)菜单id 查中间表 SysMenuPermission 的所有权限 id
        //2.按权限id获取权限对象list
        sysMenus.forEach(sysMenu->{
            SysMenuListVM sysMenuListVM = new SysMenuListVM();
            BeanUtils.copyProperties(sysMenus,sysMenuListVM);
            Set<Integer> allPermId = sysMenuPermissionRepository.findAllPermId(sysMenu.getId());
            List<Permission> perms = permissionRepository.findAllById(allPermId);
            sysMenuListVM.setList(perms);
            sysMenuListVMs.add(sysMenuListVM);
        });
        return ResultVOUtil.success(sysMenuListVMs);
    }
}
