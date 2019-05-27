package com.hgys.iptv.repository;


import com.hgys.iptv.model.Authority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends BaseRepository<Authority,Integer>{

//    ResultVO findByName(String name);
    List<Authority> findByMenuId(Integer menuId);

}
