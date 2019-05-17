package com.hgys.iptv.repository;

import com.hgys.iptv.model.Permission;

public interface PermissionRepository extends BaseRepository<Permission,Integer>{
    Permission findByName(String name);
}
