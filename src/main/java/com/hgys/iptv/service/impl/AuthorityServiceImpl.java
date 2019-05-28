package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Authority;
import com.hgys.iptv.model.Permission;
import com.hgys.iptv.model.SysMenu;
import com.hgys.iptv.model.bean.MenuNode;
import com.hgys.iptv.model.bean.PermissionNode;
import com.hgys.iptv.model.dto.AuthorityDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.PermissionRepository;
import com.hgys.iptv.repository.SysMenuRepository;
import com.hgys.iptv.service.AuthorityService;
import com.hgys.iptv.service.SysMenuService;
import com.hgys.iptv.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 为已有菜单和已有权限
     * 一键生成权限列表
     */
    @Autowired
    private SysMenuService sysMenuService;

    public void bootAuthority(List<MenuNode> menuNodes){
        try {
            menuNodes.forEach(menuNode -> {
                if(menuNode.getChildrens().size()==0){//菜单叶子结点
                    List<PermissionNode> permissionTree = menuNode.getPermissionTree();
                    reachPermLeaf(menuNode.getId(),permissionTree);
                }else{
                    bootAuthority(menuNode.getChildrens());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void reachPermLeaf(Integer pid,List<PermissionNode> permissionTree){
        permissionTree.forEach(permissionNode -> {//为每个叶子结点菜单关联上所有已存在的叶子结点权限
            if(permissionNode.getChildrens().size()==0){//权限叶子结点
                createAuthNode(pid,permissionNode.getId());
            }else{
                reachPermLeaf(pid,permissionNode.getChildrens());
            }
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void createAuthNode(Integer menuId,Integer permId){
        try {
            Authority authority = new Authority();
            authority.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            authority.setMenuId(menuId);
            SysMenu sysMenu = repositoryManager.findOneById(SysMenu.class, menuId);
            /**perm==
             * 6:批量上线
             * 7:批量下线
             * 8:批量下线
             * 9:批量导出
             * 10:初审
             * 11:复审
             * 12:终审
             * menu==
             * 15:审核
             */

            Permission permission = repositoryManager.findOneById(Permission.class, permId);
            if(permission.getId()<=4){//CRUD
                authority.setName(sysMenu.getName()+":"+permission.getName());
                authority.setMenuId(sysMenu.getId());
                authority.setMenuName(sysMenu.getName());// 如 cp管理
                authority.setText(permission.getDescription());//展示名
                authority.setStatus(0);
                authorityRepository.save(authority);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void bootAuthority(){
        try {
            List<MenuNode> menuNodes = sysMenuService.loadMenuTreeList();
            bootAuthority(menuNodes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
