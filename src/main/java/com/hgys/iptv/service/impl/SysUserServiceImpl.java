package com.hgys.iptv.service.impl;

import com.google.common.collect.Maps;
import com.hgys.iptv.controller.vm.SysUserVM;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.SysUserRole;
import com.hgys.iptv.model.User;
import com.hgys.iptv.model.dto.SysUserDTO;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.security.UserDetailsServiceImpl;
import com.hgys.iptv.service.SysUserService;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UpdateTool;
import com.hgys.iptv.util.UserSessionInfoHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName SysUserServiceImpl
 * @Auther: wangz
 * @Date: 2019/5/16 19:21
 * @Description: TODO
 */
@Service
public class SysUserServiceImpl extends SysServiceImpl implements SysUserService {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final String menuName = "用户管理";

    private static final String rawPwd = "123456";

    @Override
    public ResultVO findByUserName(String username) {
        User byUsername = userRepository.findByUsername(username);
        if(byUsername==null){
            return ResultVOUtil.error("1","该用户不存在！");
        }
        return ResultVOUtil.success(byUsername);
    }

    @Override
    public ResultVO findUserById(Integer id) {
        SysUserVM sysUserVM = new SysUserVM();
        User byId = userRepository.findById(id).get();
        BeanUtils.copyProperties(byId,sysUserVM);
        List<Role> allById = this.findAllRoleByUserId(id);
        sysUserVM.setList(allById);
        return ResultVOUtil.success(sysUserVM);
    }

    //添加用户
    @Override
    @Transactional(rollbackFor = Exception.class)
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
            return ResultVOUtil.error("1","用户名已被占用！");
        }

        try {
            User user = new User();
            Integer cpId = userDTO.getCpId();
            if(-1 == cpId){
                user.setCpAbbr("平台用户");
            }else if(cpId > 0){
                Cp cp = repositoryManager.findOneById(Cp.class, cpId);
                user.setCpAbbr(cp.getCpAbbr());
            }
            // 状态0:启用，1：禁用--默认新增时就启用
            if(userDTO.getStatus()==null || userDTO.getStatus()!=1)
                user.setStatus(0);
            BeanUtils.copyProperties(userDTO,user);
            //加密
            user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
            user.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            user.setIsdelete(0);//删除状态
            User user_add = userRepository.save(user);
//处理中间表
            handleRelation(userDTO,user_add.getId());
            //记录日志
            logger.log_add_success(menuName,"SysUserServiceImpl.addUser");
        }catch (Exception e){
            e.printStackTrace();
            logger.log_add_fail(menuName,"SysUserServiceImpl.addUser");
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
        try {
            if(sysUserDTO.getRids()==null)//没有关联关系直接
                return;
            List<String> ids = Arrays.asList(StringUtils.split(sysUserDTO.getRids(), ","));
            //2.插中间表
            List<SysUserRole> relationList =new ArrayList<>();
            ids.forEach(rid->{
                SysUserRole relation = new SysUserRole();
                relation.setRoleId(Integer.parseInt(rid));
                relation.setUserId(id);
                relationList.add(relation);
            });
            sysUserRoleRepository.saveAll(relationList);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 修改用户信息需要提供正确密码
     *
     * @param userDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO updateUser(SysUserDTO userDTO) {
        if(!(userDTO.getId()!=null && userDTO.getId()>0)){
            return ResultVOUtil.error("1","用户id不能为空！");
        }
        if(StringUtils.isBlank(userDTO.getUsername())){
            return ResultVOUtil.error("1","用户名不能为空！");
        }
        String password = userDTO.getPassword().trim();
        if(StringUtils.isBlank(password)){
            return ResultVOUtil.error("1","密码不能为空！");
        }
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                return ResultVOUtil.error("1","用户或密码错误！");
            }
            User user = new User();
            //复制了raw pwd
            BeanUtils.copyProperties(userDTO,user);
            //1.重写加密
            user.setPassword(passwordEncoder.encode(password));
            //2.密码比对通过后，直接取该密码覆盖
//            user.setPassword(userDetails.getPassword());
            user.setModifyTime(new Timestamp(System.currentTimeMillis()));
            //处理 null值
            User byIdUser = userRepository.findById(userDTO.getId()).get();
            if(byIdUser==null)
                return ResultVOUtil.error("1","用户已不存在！");
            UpdateTool.copyNullProperties(byIdUser,user);
            userRepository.saveAndFlush(user);
            // 先删除后插入
            //在中间表中按userId删除，用户-角色关系--需要更新角色关系时才删除
            if(StringUtils.isNotBlank(userDTO.getRids()))
                sysUserRoleRepository.deleteAllByUserId(user.getId());
            handleRelation(userDTO,user.getId());

            logger.log_up_success(menuName,"SysUserServiceImpl.updateUser");

        }catch (Exception e){
            e.printStackTrace();
            logger.log_up_fail(menuName,"SysUserServiceImpl.updateUser");
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 修改密码
     * @param password_old
     * @param password_new
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO modifyPassword(String password_old,String password_new){
        try {
            String username = UserSessionInfoHolder.getCurrentUsername();
            if(null == username || (username.compareTo("anonymousUser")==0))
                return  ResultVOUtil.error("1","密码已过期或未登录！");

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password_old,userDetails.getPassword())){
                return ResultVOUtil.error("1","用户或密码错误！");
            }
            User byUsername = userRepository.findByUsername(username);
            byUsername.setPassword(passwordEncoder.encode(password_new));
            userRepository.saveAndFlush(byUsername);

            logger.log_up_success(menuName,"SysUserServiceImpl.modifyPassword");
        }catch (Exception e){
            logger.log_up_fail(menuName,"SysUserServiceImpl.modifyPassword");
            return ResultVOUtil.error("1","密码修改异常！");
        }
        return ResultVOUtil.success("密码修改成功！");
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO resetPassword(String username) {
        if(StringUtils.isBlank(username)){
            return ResultVOUtil.error("1","用户名不能为空！");
        }
        try {
            User byIdUser = userRepository.findByUsername(username);
            if(byIdUser==null)
                return ResultVOUtil.error("1","用户已不存在！");
            byIdUser.setPassword(rawPwd);
            userRepository.saveAndFlush(byIdUser);

            logger.log_up_success(menuName,"SysUserServiceImpl.resetPassword");
        }catch (Exception e){
            e.printStackTrace();
            logger.log_up_fail(menuName,"SysUserServiceImpl.resetPassword");
            return ResultVOUtil.error("1","重置密码异常！");
        }
        return ResultVOUtil.success("重置密码成功！");
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
                    User u = userRepository.findById(id).get();
                    userRepository.logicDelete(u.getUsername()+"-remove",id);
                    //删除关系映射
                    sysUserRoleRepository.deleteAllByUserId(id);
                }

                logger.log_rm_success(menuName,"SysUserServiceImpl.batchLogicDelete");
            }
        }catch (Exception e){
            logger.log_rm_fail(menuName,"SysUserServiceImpl.batchLogicDelete");
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }


//    @Override
//    public ResultVO findAllUser() {
//        List<User> all = userRepository.findAll();
//        if(all.size()>0)
//            return ResultVOUtil.success(all);
//        return ResultVOUtil.error("1","所查询列表不存在!");
//    }

    @Override
    public Page<User> findAllUserOfPage(String username,String realName,Integer status,Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum -1 ,pageSize);
        HashMap<String, Object> map = Maps.newHashMap();
        if(username!=null)
            map.put("username","%"+username+"%");
        if(realName!=null)
            map.put("realName",realName);
        if(status!=null&&status>0)
            map.put("status",status);
        map.put("isdelete",0);
        return repositoryManager.findByCriteriaPage(userRepository,map,pageable);
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
