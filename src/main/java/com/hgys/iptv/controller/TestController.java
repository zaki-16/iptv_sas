package com.hgys.iptv.controller;

import com.hgys.iptv.aop.SystemControllerLog;
import com.hgys.iptv.common.Criteria;
import com.hgys.iptv.common.Restrictions;
import com.hgys.iptv.model.Authority;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.AuthorityRepository;
import com.hgys.iptv.repository.RoleRepository;
import com.hgys.iptv.service.impl.SettleDataOfCpServiceImpl_;
import com.hgys.iptv.util.RepositoryManager;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.UserSessionInfoHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    private SettleDataOfCpServiceImpl_ service;



    @GetMapping("/tester")
//    @SystemControllerLog(target = "测试target",type = "测试type")
    public ResultVO tester(String cpName){
        return ResultVOUtil.success(service.getCpSettleDataOfPie(null,null,cpName));
    }


}
