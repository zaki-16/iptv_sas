package com.hgys.iptv.repository;


import com.hgys.iptv.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends BaseRepository<User,Integer>{
    //根据用户名返回用户对象
    User findByUsername(String name);

    Integer countByUsername(String username);

    //UPDATE sys_user SET isdelete = 1, username="ddd" WHERE id = 11
    @Query(value = "update User set isdelete = 1,username=:newName WHERE id = :pk")
    @Modifying
    @Transactional
    void logicDelete(@Param("newName") String newName,
                     @Param("pk")int id) ;



}

