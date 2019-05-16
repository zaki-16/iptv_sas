package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.SysUserRole;
import com.hgys.iptv.model.User;
import com.hgys.iptv.model.dto.SysUserDTO;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.SysUserService;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @ClassName SysUserServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/16 19:21
 * @Description: TODO
 */
@Service
public class SysUserServiceImpl extends SysServiceImpl implements SysUserService {

    @Override
    public ResultVO findByUserName(String username) {
        User byUsername = userRepository.findByUsername(username);
        if(byUsername==null){
            return ResultVOUtil.error("1","该用户不存在！");
        }
        return ResultVOUtil.success(byUsername);
    }

    //添加用户
    @Override
    @Transactional(rollbackFor = Exception.class)
//    @PreAuthorize("hasPermission('SAVE', 'UPDATE') or hasRole('ROLE_ADMIN')")
    public ResultVO<?> addUser(SysUserDTO userDTO) {
        if(StringUtils.isBlank(userDTO.getUsername())){
            return ResultVOUtil.error("1","用户名不能为空！");
        }
        if(StringUtils.isBlank(userDTO.getPassword())){
            return ResultVOUtil.error("1","密码不能为空！");
        }
        //校验用户名是否已存在
        Integer i = userRepository.countByUsername(userDTO.getUsername());
        if(i>0){
            return ResultVOUtil.error("1","用户名已存在！");
        }
        try {
            User user = new User();
            // 状态0:启用，1：禁用--默认新增时就启用
            if(userDTO.getStatus()!=1)
                user.setStatus(0);
            BeanUtils.copyProperties(userDTO,user);
            //加密
            user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
            user.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            User user_add = userRepository.save(user);
//处理中间表
            handleRelation(userDTO,user_add.getId());
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error("1","新增用户异常！");
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 处理中间表
     * @param sysUserDTO
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    protected void handleRelation(SysUserDTO sysUserDTO,Integer id){
        if(sysUserDTO.getRids()==null)//没有关联关系直接
            return;
        List<String> ids = Arrays.asList(StringUtils.split(sysUserDTO.getRids(), ","));
        //2.插cp-product中间表
        List<SysUserRole> relationList =new ArrayList<>();
        ids.forEach(rid->{
            SysUserRole relation = new SysUserRole();
            relation.setRoleId(Integer.parseInt(rid));
            relation.setUserId(id);
            relationList.add(relation);
        });
        sysUserRoleRepository.saveAll(relationList);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO updateUser(SysUserDTO userDTO) {
        if(!(userDTO.getId()!=null && userDTO.getId()>0)){
            return ResultVOUtil.error("1","用户id不能为空！");
        }
        if(StringUtils.isBlank(userDTO.getUsername())){
            return ResultVOUtil.error("1","用户名不能为空！");
        }
        if(StringUtils.isBlank(userDTO.getPassword())){
            return ResultVOUtil.error("1","密码不能为空！");
        }
        try{
            User user = new User();
            BeanUtils.copyProperties(userDTO,user);
            user.setModifyTime(new Timestamp(System.currentTimeMillis()));
            //处理 null值
            User byIdUser = userRepository.findById(userDTO.getId()).get();
            if(byIdUser==null)
                return ResultVOUtil.error("1","用户已不存在！");
            UpdateTool.copyNullProperties(byIdUser,user);
            userRepository.saveAndFlush(user);
            // 先删除后插入
            //在中间表中按userId删除，用户-角色关系
            sysUserRoleRepository.deleteAllByUserId(user.getId());
            handleRelation(userDTO,user.getId());
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO deleteUserById(Integer id) {
        //删除用户，同时删除关联的角色关系，不是删除角色本身\
        try {
            userRepository.deleteById(id);
            sysUserRoleRepository.deleteAllByUserId(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public ResultVO findAllUser() {
        List<User> all = userRepository.findAll();
        if(all.size()>0)
            return ResultVOUtil.success(all);
        return ResultVOUtil.error("1","所查询列表不存在!");
    }

    /**
     * 按用户id查关联的所有角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> findAllRoleByUserId(Integer userId) {
        //1.按userId 查中间表获取其拥有的角色id集合
        Set<Integer> allRoleId = sysUserRoleRepository.findAllRoleId(userId);
        //2.按角色id查询所有角色对象
        return roleRepository.findAllById(allRoleId);
    }
}
