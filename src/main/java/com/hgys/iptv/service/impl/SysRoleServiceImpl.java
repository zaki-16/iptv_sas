package com.hgys.iptv.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Maps;
import com.hgys.iptv.common.Criteria;
import com.hgys.iptv.common.Restrictions;
import com.hgys.iptv.controller.vm.SysRoleVM;
import com.hgys.iptv.model.Authority;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.SysMenu;
import com.hgys.iptv.model.SysRoleAuthority;
import com.hgys.iptv.model.dto.SysRoleDTO;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.SysMenuRepository;
import com.hgys.iptv.service.SysRoleService;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName SysRoleServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/16 19:26
 * @Description: TODO
 */
@Service
public class SysRoleServiceImpl extends SysServiceImpl implements SysRoleService {
    @Autowired
    private SysMenuRepository sysMenuRepository;
    @Override
    public ResultVO findByRoleName(String name) {
        Role byName = roleRepository.findByName(name);
        if(byName==null){
            return ResultVOUtil.error("1","该角色不存在！");
        }
        List<Authority> allAuthorityByRoleId = findAllAuthorityByRoleId(byName.getId());
        SysRoleVM sysRoleVM = new SysRoleVM();
        BeanUtils.copyProperties(byName,sysRoleVM);
        sysRoleVM.setList(allAuthorityByRoleId);
        return ResultVOUtil.success(sysRoleVM);
    }


    /**
     * 新增角色
     * 查询表：sys_menu sys_permission
     * 操作表：sys_role sys_role_authority authority
     * @param sysRoleDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO addRole(SysRoleDTO sysRoleDTO) {
        if(StringUtils.isBlank(sysRoleDTO.getName())){
            return ResultVOUtil.error("1","角色名不能为空！");
        }
        //检查名称
        Role byName = roleRepository.findByName(sysRoleDTO.getName());
        if(byName!=null)
            return ResultVOUtil.error("1","角色名已存在！");
        // 添加角色--关联权限
        try {
            Role role = new Role();
            // 状态0:启用，1：禁用--默认新增时就启用
            if(sysRoleDTO.getStatus()==null || sysRoleDTO.getStatus()!=1)
                role.setStatus(0);
            BeanUtils.copyProperties(sysRoleDTO,role);
            role.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            role.setIsdelete(0);//删除状态
            Role role_add = roleRepository.save(role);
//处理中间表
            if(sysRoleDTO.getIds()!=null)
                handleRelation(sysRoleDTO,role_add.getId());
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","新增角色异常！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 处理中间表等
     *      * 查询表：sys_menu sys_permission
     *      * 操作表：sys_role sys_role_authority authority
     * @param sysRoleDTO
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    protected void handleRelation(SysRoleDTO sysRoleDTO, Integer id){
        try{
            List<String> ids = Arrays.asList(StringUtils.split(sysRoleDTO.getIds(), ","));
            if(ids.size()>0){
                List<SysRoleAuthority> relationList =new ArrayList<>();
                ids.forEach(authId->{
                    SysRoleAuthority relation = new SysRoleAuthority();
                    relation.setRoleId(id);
                    relation.setAuthId(Integer.parseInt(authId));
                    relationList.add(relation);
                });
                sysRoleAuthorityRepository.saveAll(relationList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO updateRole(SysRoleDTO sysRoleDTO) {
        if(!(sysRoleDTO.getId()!=null && sysRoleDTO.getId()>0)){
            return ResultVOUtil.error("1","用户id不能为空！");
        }
        if(StringUtils.isBlank(sysRoleDTO.getName())){
            return ResultVOUtil.error("1","角色名不能为空！");
        }
        try{
            Role role = new Role();
            BeanUtils.copyProperties(sysRoleDTO,role);
            role.setModifyTime(new Timestamp(System.currentTimeMillis()));
            //处理 null值
            Role byId = roleRepository.findById(sysRoleDTO.getId()).orElse(null);
            if(byId==null)
                return ResultVOUtil.error("1","角色已不存在！");
            UpdateTool.copyNullProperties(byId,role);
            roleRepository.saveAndFlush(role);
            // 先删除后插入
            sysRoleAuthorityRepository.deleteAllByRoleId(role.getId());
            handleRelation(sysRoleDTO,role.getId());
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO deleteRoleById(Integer id) {
        //删除用户，同时删除关联的角色关系，不是删除角色本身
        try {
            roleRepository.deleteById(id);
            sysRoleAuthorityRepository.deleteAllByRoleId(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO batchLogicDelete(String ids) {
        try{
            List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
            if(idLists.size()>0){
                Set<Integer> pidSets = new HashSet<>();
                idLists.forEach(id->{
                    pidSets.add(Integer.parseInt(id));
                });
                for (Integer id : pidSets){
                    Role role = roleRepository.findById(id).get();
                    roleRepository.logicDelete(role.getName()+"-remove",id);
                    //删除关系映射
                    sysRoleAuthorityRepository.deleteAllByRoleId(id);
                }
            }
        }catch (Exception e){
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO batchOnRole(String ids) {
        try {
            ArrayList<String> list = CollUtil.newArrayList(StringUtils.split(ids, ","));
            if(list.size()>0){
                list.forEach(id->{
                    Role role = roleRepository.findById(Integer.parseInt(id)).orElse(null);
                    if(role!=null){
                        role.setStatus(0);
                        role.setModifyTime(new Timestamp(System.currentTimeMillis()));
                        roleRepository.save(role);
                    }
                });
            }
        }catch (Exception e){
            return  ResultVOUtil.error("1","批量启用异常！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO batchOffRole(String ids) {
        try {
            ArrayList<String> list = CollUtil.newArrayList(StringUtils.split(ids, ","));
            if(list.size()>0){
                list.forEach(id->{
                    Role role = roleRepository.findById(Integer.parseInt(id)).orElse(null);
                    if(role!=null){
                        role.setStatus(1);
                        role.setModifyTime(new Timestamp(System.currentTimeMillis()));
                        roleRepository.save(role);
                    }
                });
            }
        }catch (Exception e){
            return  ResultVOUtil.error("1","批量停用异常！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }


    /**
     * 新增用户时查询所有未删除、未停用的角色
     * @return
     */
    @Override
    public ResultVO findAllRole() {
        Criteria<Role> criteria = new Criteria<>();
        criteria
                .add(Restrictions.eq("status",0))
                .add(Restrictions.eq("isdelete",0));
        List<Role> all = roleRepository.findAll(criteria);
        if(all.size()>0)
            return ResultVOUtil.success(all);
        return ResultVOUtil.error("1","未发现启用的角色!");
    }
    @Override
    public Page<Role> findAllRoleOfPage(String name, Integer status, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum -1 ,pageSize);
        HashMap<String, Object> map = Maps.newHashMap();
        if(name!=null)
            map.put("name",name);
        if(status!=null&&status>0)
            map.put("status",status);
        map.put("isdelete",0);
        return repositoryManager.findByCriteriaPage(roleRepository,map,pageable);
    }

    @Override
    public ResultVO findRoleById(Integer roleId) {
        SysRoleVM sysRoleVM = new SysRoleVM();
        Role role = roleRepository.findById(roleId).orElse(null);
        BeanUtils.copyProperties(role,sysRoleVM);
        Set<Integer> allAuthId = sysRoleAuthorityRepository.findAllAuthId(roleId);
        List<Authority> allById = authorityRepository.findAllById(allAuthId);
        sysRoleVM.setList(allById);
        return ResultVOUtil.success(sysRoleVM);
    }

    /**
     *按角色id查关联的所有权限列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Authority> findAllAuthorityByRoleId(Integer roleId) {
        Set<Integer> allAuthId = sysRoleAuthorityRepository.findAllAuthId(roleId);
        return authorityRepository.findAllById(allAuthId);
    }
}
