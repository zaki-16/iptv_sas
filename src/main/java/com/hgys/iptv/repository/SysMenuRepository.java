package com.hgys.iptv.repository;

import com.hgys.iptv.model.SysMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMenuRepository extends BaseRepository<SysMenu,Integer>{

    @Query(value = "SELECT MAX(m.level) FROM sys_menu m",nativeQuery = true)
    Integer getLevel();

}
