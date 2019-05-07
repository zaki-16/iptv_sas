package com.hgys.iptv.service;

import com.hgys.iptv.model.Permission;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;

import java.util.Map;

public interface LoginService {

    User addUser(Map<String, Object> map);
    User findByUsername(String username);
    User findAllUser();//需要权限
    User updateUser(String username);
    User deleteUser(String username);

    Role addRole(Map<String, Object> map);
    Role updateRole(Role role);
    Role findByRolename(Role role);
    Role findAllRole();

    Permission addPermission(Map<String, Object> map);
}
