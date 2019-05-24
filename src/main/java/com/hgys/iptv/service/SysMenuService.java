package com.hgys.iptv.service;

import com.hgys.iptv.model.bean.MenuNode;
import com.hgys.iptv.model.dto.SysMenuDTO;
import com.hgys.iptv.model.vo.ResultVO;

import java.util.List;

public interface SysMenuService {

    ResultVO loadMenuTree();

    List<MenuNode> loadMenuTreeList();

    ResultVO loadPermByMenuId(Integer id);

    ResultVO createMenuNode(SysMenuDTO sysMenuDTO);

    ResultVO updateMenuNode(SysMenuDTO sysMenuDTO);

    ResultVO deleteMenuNodeById(Integer id);

}
