package com.hgys.iptv.repository;

import com.hgys.iptv.model.SysUserRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface SysUserRoleRepository  extends BaseRepository<SysUserRole,Integer>{
    /**
     * 通过 userId 查询中间表其下属角色 roleId 集合
     */
    @Query(value = "select roleId from SysUserRole where userId=?1")
    Set<Integer> findAllRoleId(Integer userId);

    //    按 roleId 删中间表
    @Query("delete from SysUserRole where roleId=?1")
    @Modifying
    @Transactional
    void deleteAllByRoleId(Integer roleId);

    //    按 userId 删中间表
    @Query("delete from SysUserRole where userId=?1")
    @Modifying
    @Transactional
    void deleteAllByUserId(Integer userId);
}
