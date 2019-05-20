package com.hgys.iptv.service;

import com.hgys.iptv.model.dto.SysMenuDTO;
import com.hgys.iptv.model.vo.ResultVO;

public interface SysMenuService {

    ResultVO loadMenuTree();

    ResultVO loadPermByMenuId(Integer id);

    ResultVO createMenuNode(SysMenuDTO sysMenuDTO);

    ResultVO updateMenuNode(SysMenuDTO sysMenuDTO);

    ResultVO deleteMenuNodeById(Integer id);

}
