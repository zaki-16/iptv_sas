package com.hgys.iptv.repository;

import com.hgys.iptv.model.Permission;
import org.springframework.data.jpa.repository.Query;

public interface PermissionRepository extends BaseRepository<Permission,Integer>{
    Permission findByName(String name);

    @Query(value = "SELECT MAX(p.level) FROM sys_permission p",nativeQuery = true)
    Integer getLevel();
}
