package com.hgys.iptv.controller;

import com.hgys.iptv.model.User;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AuthorityRepository;
import com.hgys.iptv.repository.RoleRepository;
import com.hgys.iptv.repository.SysRoleAuthorityRepository;
import com.hgys.iptv.util.RepositoryManager;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UserSessionInfoHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @ClassName TestController
 * @Auther: wangz
 * @Date: 2019/5/17 14:10
 * @Description: TODO
 */
@RestController
@RequestMapping("/test")
@Api(value="TestController",tags = "自用调试Api接口")
public class TestController   {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private SysRoleAuthorityRepository sysRoleAuthorityRepository;
    @Autowired
    private RepositoryManager repositoryManager;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/myTest")
    public ResultVO myTest(){
        String currentUsername = UserSessionInfoHolder.getCurrentUsername();
//        User oneById = repositoryManager.findOneById(User.class, 8);
//        RepositoryManager.ModelView modelView = RepositoryManager.getModelView();
//        modelView.setElem(oneById);
        return ResultVOUtil.success(currentUsername);
    }

    @PostMapping("/testUsername")
    public ResultVO myTest1(){
        String currentUsername = UserSessionInfoHolder.getCurrentUsername();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        User oneById = repositoryManager.findOneById(User.class, 8);
//        RepositoryManager.ModelView modelView = RepositoryManager.getModelView();
//        modelView.setElem(oneById);
        return ResultVOUtil.success(currentUsername);
    }
}
