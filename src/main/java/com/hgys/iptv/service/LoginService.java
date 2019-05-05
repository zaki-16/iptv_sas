package com.hgys.iptv.service;

import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;

import java.util.Map;

public interface LoginService {

    User findByUsername(String username);

    User addUser(Map<String, Object> map);

    Role addRole(Map<String, Object> map);
}
