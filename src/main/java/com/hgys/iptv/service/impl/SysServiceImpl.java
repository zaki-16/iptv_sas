package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Permission;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.repository.*;
import com.hgys.iptv.service.SysService;
import com.hgys.iptv.util.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

/**
 * @ClassName SysServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/16 11:33
 * @Description: TODO
 */
public abstract class SysServiceImpl extends AbstractBaseServiceImpl implements SysService {
    @Autowired
    protected BCryptPasswordEncoder passwordEncoder;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected RoleRepository roleRepository;
    @Autowired
    protected PermissionRepository permissionRepository;
    @Autowired
    protected SysRolePermissionRepository sysRolePermissionRepository;//权限-角色中间表
    @Autowired
    protected SysUserRoleRepository sysUserRoleRepository;//用户-角色中间表


}
