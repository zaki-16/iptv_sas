package com.hgys.iptv.controller;

import com.google.common.collect.Maps;
import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AuthorityRepository;
import com.hgys.iptv.repository.RoleRepository;
import com.hgys.iptv.repository.SysRoleAuthorityRepository;
import com.hgys.iptv.util.RepositoryManager;
import com.hgys.iptv.util.ResultVOUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
//        Role role = roleRepository.findById(1).get();
//        HashSet<Integer> set = new HashSet<>();
//        List<SysRoleAuthority> sysRoleAuthorities = repositoryManager.findByCriteria(SysRoleAuthority.class, "roleId", 2);
//        sysRoleAuthorities.forEach(c->{
//            set.add(c.getAuthId());
//        });
        Pageable pageable = PageRequest.of(0,10);

        Page<Role> byPage = repositoryManager.findByPage(roleRepository, pageable);
        Page<Role> byPage1 = repositoryManager.findByPage(roleRepository, 1,10);
        HashMap<String, Object> map = Maps.newHashMap();

        Page<Role> byCriteriaPage1 = repositoryManager.findByCriteriaPage(roleRepository, map, pageable);
        return ResultVOUtil.success(byCriteriaPage1);
    }

}
