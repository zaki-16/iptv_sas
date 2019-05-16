package com.hgys.iptv.repository;


import com.hgys.iptv.model.Role;

public interface RoleRepository extends BaseRepository<Role,Integer> {
    /**根据角色名查找*/
    Role findByName(String name);
}

