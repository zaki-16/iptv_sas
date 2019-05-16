package com.hgys.iptv.repository;

import com.hgys.iptv.model.SysRolePermission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface SysRolePermissionRepository extends BaseRepository<SysRolePermission,Integer>{

    /**
     * 通过 roleId 查询中间表其下属角色 permissionId 集合
     */
    @Query(value = "select permissionId from SysRolePermission where roleId=?1")
    Set<Integer> findAllPermId(Integer roleId);

    //    按 roleId 删中间表
    @Query("delete from SysRolePermission where roleId=?1")
    @Modifying
    @Transactional
    void deleteAllByRoleId(Integer roleId);

    //    按 permissionId 删中间表
    @Query("delete from SysRolePermission where permissionId=?1")
    @Modifying
    @Transactional
    void deleteAllByPermId(Integer permissionId);
}
