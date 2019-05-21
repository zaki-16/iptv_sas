package com.hgys.iptv.controller;

import com.hgys.iptv.model.*;
import com.hgys.iptv.model.QCp;
import com.hgys.iptv.model.QCpProduct;
import com.hgys.iptv.model.QProduct;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AuthorityRepository;
import com.hgys.iptv.repository.RoleRepository;
import com.hgys.iptv.repository.SysRoleAuthorityRepository;
import com.hgys.iptv.util.RepositoryManager;
import com.hgys.iptv.util.ResultVOUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

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
        Role role = roleRepository.findById(1).get();
        HashSet<Integer> set = new HashSet<>();
        List<SysRoleAuthority> sysRoleAuthorities = repositoryManager.findByCriteria(SysRoleAuthority.class, "roleId", 2);
        sysRoleAuthorities.forEach(c->{
            set.add(c.getAuthId());
        });
        List<Authority> authorityList = authorityRepository.findAllById(set);
        RepositoryManager.ModelView<Role,Authority> modelView = RepositoryManager.getModelView();
        modelView.setE(role);
        modelView.setList(authorityList);
        return ResultVOUtil.success(modelView);
    }

}
