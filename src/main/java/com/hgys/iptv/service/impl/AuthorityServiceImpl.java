package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Authority;
import com.hgys.iptv.model.dto.AuthorityDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.AuthorityService;
import com.hgys.iptv.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName AuthorityServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/20 09:25
 * @Description: TODO
 */
@Service
public class AuthorityServiceImpl extends SysServiceImpl implements AuthorityService {

//    public ResultVO findByName(String name) {
//        return ResultVOUtil.success(authorityRepository.findByName(name));
//    }

    @Transactional(rollbackFor = Exception.class)
    public ResultVO addAuthority(AuthorityDTO authorityDTO) {
        try{
            Authority authority = new Authority();
            BeanUtils.copyProperties(authorityDTO,authority);
//            authority.setName(authorityDTO.getMenuName()+":"+authorityDTO.getPermName());
            // 状态0:启用，1：禁用--默认新增时就启用
            if(authorityDTO.getStatus()!=1)
                authority.setStatus(0);
            authority.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            authorityRepository.save(authority);
        }catch (Exception e){
            return ResultVOUtil.error("1","新增或修改异常！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }
    @Transactional(rollbackFor = Exception.class)
    public ResultVO updateAuthority(AuthorityDTO authorityDTO) {
        if (!(authorityDTO.getId() != null && authorityDTO.getId() > 0)) {
            return ResultVOUtil.error("1", "id不能为空！");
        }
        return this.addAuthority(authorityDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultVO deleteById(Integer id) {
        try {
            authorityRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","删除异常！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    public ResultVO findAllAuthority() {
        List<Authority> all = authorityRepository.findAll();
        if(all.size()>0)
            return ResultVOUtil.success(all);
        return ResultVOUtil.error("1","所查询列表不存在!");
    }
}
