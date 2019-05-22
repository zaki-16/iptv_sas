package com.hgys.iptv.repository;


import com.hgys.iptv.model.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RoleRepository extends BaseRepository<Role,Integer> {
    /**根据角色名查找*/
    Role findByName(String name);

    @Query(value = "update Role set isdelete = 1,name=:newName WHERE id = :pk")
    @Modifying
    @Transactional
    void logicDelete(
            @Param("newName") String newName, @Param("pk")int id);
}

