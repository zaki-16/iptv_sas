package com.hgys.iptv.controller;

import com.hgys.iptv.common.Criteria;
import com.hgys.iptv.common.Restrictions;
import com.hgys.iptv.model.Authority;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AuthorityRepository;
import com.hgys.iptv.repository.RoleRepository;
import com.hgys.iptv.util.RepositoryManager;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UserSessionInfoHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName TestController
 * @Auther: wangz
 * @Date: 2019/5/17 14:10
 * @Description: TODO
 */
@RestController
@RequestMapping("/test")
@Api(value="TestController",tags = "a自用调试Api接口")
public class TestController   {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private AuthorityRepository authorityRepository;
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

    @GetMapping("/tester")
    public ResultVO myTest1(HttpServletRequest request){
//        Page<Authority> pageByHql = repositoryManager.findPageByHql(Authority.class, null, null, 0, 10, null);
////        List<Map<String, Object>> pageBySql = repositoryManager.findPageBySql("SELECT * FROM authority", 1, 10, null);
//        Map<String, Object> criteria = repositoryManager.initCriteria();
//        criteria.put("menuId",1);
//        Page<Object> objects = repositoryManager.selectOfView(authorityRepository, pageable);

        Criteria<Authority> criteria = new Criteria<>();
//        criteria.add(Restrictions.eq("id",1)).add(Restrictions.like("name","a"));
        criteria.add(Restrictions.gt("id",1));
        List<Authority> all = authorityRepository.findAll(criteria);
        return ResultVOUtil.success(all);
    }
}
