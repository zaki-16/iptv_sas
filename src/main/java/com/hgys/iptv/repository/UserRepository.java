package com.hgys.iptv.repository;


import com.hgys.iptv.model.User;

public interface UserRepository extends BaseRepository<User,Long>{
    //根据用户名返回用户对象
    User findByUsername(String name);

}

