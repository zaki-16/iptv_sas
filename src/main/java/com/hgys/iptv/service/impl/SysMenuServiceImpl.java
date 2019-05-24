package com.hgys.iptv.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.hgys.iptv.controller.vm.SysMenuVM;
import com.hgys.iptv.model.Authority;
import com.hgys.iptv.model.Permission;
import com.hgys.iptv.model.SysMenu;
import com.hgys.iptv.model.SysMenuPermission;
import com.hgys.iptv.model.bean.MenuAuthNode;
import com.hgys.iptv.model.bean.MenuNode;
import com.hgys.iptv.model.bean.PermissionNode;
import com.hgys.iptv.model.dto.SysMenuDTO;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AuthorityRepository;
import com.hgys.iptv.repository.PermissionRepository;
import com.hgys.iptv.repository.SysMenuPermissionRepository;
import com.hgys.iptv.repository.SysMenuRepository;
import com.hgys.iptv.service.SysMenuService;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @ClassName SysMenuServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/19 14:47
 * @Description: TODO
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuRepository sysMenuRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private SysMenuPermissionRepository sysMenuPermissionRepository;

    @Override
    public List<MenuAuthNode> getMenuAuthTree(){
        List<Authority> all = authorityRepository.findAll();
        List<MenuAuthNode> menuNodes = new ArrayList<>();
        all.forEach(authority->{
            MenuAuthNode menuNode = new MenuAuthNode();
            BeanUtils.copyProperties(authority,menuNode);
            menuNodes.add(menuNode);
        });
        //组装菜单树
        return assembleMenuAuthTree(0, menuNodes);
    }

    private List<MenuAuthNode> assembleMenuAuthTree(Integer pId, List<MenuAuthNode> menuNodes) {
        List<MenuAuthNode> tree = CollUtil.newArrayList();
        for (MenuAuthNode menuNode : menuNodes) {
            if (pId.equals(menuNode.getParentId())) {
                menuNode.setChildrens(assembleMenuAuthTree(menuNode.getId(), menuNodes));
                tree.add(menuNode);
            }
        }
        return tree;
    }
    /**
     * 将菜单组装成树
     * 将菜单下关联的权限也组装成树
     *
     * @return
     */
    @Override
    public ResultVO loadMenuTree(){
        List<SysMenuVM> sysMenuVMList = new ArrayList<>();
        //1. 查所有菜单
        List<SysMenu> sysMenus = sysMenuRepository.findAll();
        List<MenuNode> menuNodes = new ArrayList<>();
        sysMenus.forEach(sysMenu->{
            MenuNode menuNode = new MenuNode();
            BeanUtils.copyProperties(sysMenu,menuNode);
            menuNodes.add(menuNode);
        });
        //组装菜单树
        List<MenuNode> menuTree = assembleMenuTree(0, menuNodes);
        List<MenuNode> assemble = assemble(menuTree);
        // 对每个叶子结点查找 其关联的权限
//        sysMenus.forEach(sysMenu -> {
//            SysMenuVM sysMenuVM = new SysMenuVM();
//            BeanUtils.copyProperties(sysMenu,sysMenuVM);
//            List<Authority> byMenuId = authorityRepository.findByMenuId(sysMenu.getId());
//            sysMenuVM.setList(byMenuId);
//            sysMenuVMList.add(sysMenuVM);
//        });
        return ResultVOUtil.success(assemble);
    }

    public List<MenuNode> assemble(List<MenuNode> menuTree){
        menuTree.forEach(menu->{
            if(menu.getChildrens().size()==0){
                List<Authority> byMenuId = authorityRepository.findByMenuId(menu.getId());
                menu.setAuthorities(byMenuId);
            }else{
                assemble(menu.getChildrens());
            }
        });
        return menuTree;
    }
    public List<MenuNode> loadMenuTreeList() {
//        ArrayList<SysMenuListVM> sysMenuListVMs = new ArrayList<>();
        // 加载菜单
        //根据 authority 的
        List<SysMenu> sysMenus = sysMenuRepository.findAll();
        List<MenuNode> menuNodes = new ArrayList<>();
        sysMenus.forEach(sysMenu->{
            MenuNode menuNode = new MenuNode();
            BeanUtils.copyProperties(sysMenu,menuNode);
            menuNodes.add(menuNode);
        });
        //组装菜单树
        List<MenuNode> menuTree = assembleMenuTree(0, menuNodes);

        //所有的权限
        List<Permission> permissions = permissionRepository.findAll();
        List<PermissionNode> permissionNodes = new ArrayList<>();
        for(Permission permission : permissions){
            PermissionNode permissionNode = new PermissionNode();
            BeanUtils.copyProperties(permission,permissionNode);
            permissionNodes.add(permissionNode);
        }
        //组装权限树
        List<PermissionNode> permissionTree = assemblePermTree(0, permissionNodes);//对每个菜单下的所有权限结点进行组装成树

        //对每个叶子结点(childrens为空)的菜单--关联一颗权限树--递归

//        sysMenus.forEach(sysMenu -> {
//            SysMenuListVM sysMenuListVM = new SysMenuListVM();
//            BeanUtils.copyProperties(sysMenu,sysMenuListVM);
//            sysMenuListVM.setList(permissionTree);
//            sysMenuListVMs.add(sysMenuListVM);
//        });
        return assembleMenuPerm(menuTree, permissionTree);
    }

    private List<MenuNode> assembleMenuPerm(List<MenuNode> menuTree, List<PermissionNode> permissionTree){
        menuTree.forEach(menu->{
            if(menu.getChildrens().size()==0){// 定位到叶子
                menu.setPermissionTree(permissionTree);
            }else{
                assembleMenuPerm(menu.getChildrens(),permissionTree);
            }
        });
        return menuTree;
    }

    /**
     * 组装权限树-找兄弟法
     * @param permissionNodes
     * @return
     */
    //递归遍历树
    private List<PermissionNode> assemblePermTree(Integer pId, List<PermissionNode> permissionNodes) {
        List<PermissionNode> tree = CollUtil.newArrayList();
        for (PermissionNode permissionNode : permissionNodes) {
            if (pId.equals(permissionNode.getParentId())) {
                permissionNode.setChildrens(assemblePermTree(permissionNode.getId(), permissionNodes));
                tree.add(permissionNode);
            }
        }
        return tree;
    }

    /**
     * 组装菜单树
     * @param pId
     * @param menuNodes
     * @return
     */
    private List<MenuNode> assembleMenuTree(Integer pId, List<MenuNode> menuNodes) {
        List<MenuNode> tree = CollUtil.newArrayList();
        for (MenuNode menuNode : menuNodes) {
            if (pId.equals(menuNode.getParentId())) {
                menuNode.setChildrens(assembleMenuTree(menuNode.getId(), menuNodes));
                tree.add(menuNode);
            }
        }
        return tree;
    }

    @Override
    public ResultVO loadPermByMenuId(Integer id) {
        Set<Integer> allPermId = sysMenuPermissionRepository.findAllPermId(id);
        List<Permission> perms = permissionRepository.findAllById(allPermId);
        return ResultVOUtil.success(perms);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO createMenuNode(SysMenuDTO sysMenuDTO) {
        // 添加菜单--关联权限
        try {
            SysMenu menu = new SysMenu();
            // 状态0:启用，1：禁用--默认新增时就启用
            if(sysMenuDTO.getStatus()==null || sysMenuDTO.getStatus()!=1)
                menu.setStatus(0);
            BeanUtils.copyProperties(sysMenuDTO,menu);
            SysMenu menu_add = sysMenuRepository.save(menu);
//处理中间表
            handleRelation(sysMenuDTO,menu_add.getId());
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","新增菜单结点异常！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }



    /**
     * 处理中间表
     * @param sysMenuDTO
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    protected void handleRelation(SysMenuDTO sysMenuDTO, Integer id){
        try{
            if(sysMenuDTO.getPids()==null)//没有关联关系直接
                return;
            List<String> ids = Arrays.asList(StringUtils.split(sysMenuDTO.getPids(), ","));
            //2.插中间表
            List<SysMenuPermission> relationList =new ArrayList<>();
            ids.forEach(pid->{
                SysMenuPermission relation = new SysMenuPermission();
                relation.setMenuId(id);
                relation.setPermId(Integer.parseInt(pid));
                relationList.add(relation);
            });
            sysMenuPermissionRepository.saveAll(relationList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO updateMenuNode(SysMenuDTO sysMenuDTO) {
        if(!(sysMenuDTO.getId()!=null && sysMenuDTO.getId()>0)){
            return ResultVOUtil.error("1","用户id不能为空！");
        }
        try{
            SysMenu menu = new SysMenu();
            BeanUtils.copyProperties(sysMenuDTO,menu);
            //处理 null值
            SysMenu byId = sysMenuRepository.findById(sysMenuDTO.getId()).get();
            if(byId==null)
                return ResultVOUtil.error("1","菜单结点已不存在！");
            UpdateTool.copyNullProperties(byId,menu);
            sysMenuRepository.saveAndFlush(menu);
            // 先删除后插入
            if(StringUtils.isNotBlank(sysMenuDTO.getPids()))
                sysMenuPermissionRepository.deleteAllByMenuId(menu.getId());
            handleRelation(sysMenuDTO,menu.getId());
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO deleteMenuNodeById(Integer id) {

        //删除用户，同时删除关联的角色关系，不是删除角色本身\
        try {
            sysMenuRepository.deleteById(id);
            sysMenuPermissionRepository.deleteAllByMenuId(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }





}
