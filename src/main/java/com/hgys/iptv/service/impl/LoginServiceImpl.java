package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Permission;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;
import com.hgys.iptv.repository.RoleRepository;
import com.hgys.iptv.repository.UserRepository;
import com.hgys.iptv.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: wangzhen
 * @date:2019/4/19 16:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    //添加用户
    @Override
    public User addUser(Map<String, Object> map) {
        User user = new User();
        user.setUsername(map.get("username").toString());
        user.setPassword(map.get("password").toString());
        userRepository.save(user);
        return user;
    }

    //添加角色
    @Override
    public Role addRole(Map<String, Object> map) {
        Long id = Long.valueOf(map.get("userId").toString());
        //jpa2.05 版本后不适合userRepository.findOne()
        User user = userRepository.findById(id).get();
        List<User> users = new ArrayList<>();
        users.add(user);

        Role role = new Role();
        List<Role> roles = new ArrayList<>();
        role.setName(map.get("roleName").toString());
        role.setUsers(users);

        Permission permission1 = new Permission();
        permission1.setPermission("save");
        permission1.setRoles(roles);
        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission2.setRoles(roles);

        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleRepository.save(role);
        return role;
    }

    @Override
    public Permission addPermission(Map<String, Object> map) {
        return null;
    }

    //通过用户名查询用户
    @Override
    public User findByUsername(String name)
    {
        return userRepository.findByUsername(name);
    }

}
