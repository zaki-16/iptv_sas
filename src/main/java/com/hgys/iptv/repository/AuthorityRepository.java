package com.hgys.iptv.repository;


import com.hgys.iptv.model.Authority;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends BaseRepository<Authority,Integer>{

    ResultVO findByName(String name);

}
