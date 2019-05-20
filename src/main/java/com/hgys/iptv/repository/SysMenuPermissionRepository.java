package com.hgys.iptv.repository;

import com.hgys.iptv.model.SysMenuPermission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface SysMenuPermissionRepository extends BaseRepository<SysMenuPermission,Integer>{
    //

    /**
     * 通过 menuId 查询中间表其关联权限 id集合
     */
    @Query(value = "select permId from SysMenuPermission where menuId=?1")
    Set<Integer> findAllPermId(Integer menuId);

    //    按 menu_id 删中间表
    @Query("delete from SysMenuPermission where menuId =?1")
    @Modifying
    @Transactional
    void deleteAllByMenuId(Integer menuId);
}
