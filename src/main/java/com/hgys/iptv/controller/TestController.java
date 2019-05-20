package com.hgys.iptv.controller;

import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.QCp;
import com.hgys.iptv.model.QCpProduct;
import com.hgys.iptv.model.QProduct;
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

/**
 * @ClassName TestController
 * @Auther: wangz
 * @Date: 2019/5/17 14:10
 * @Description: TODO
 */
@RestController
@RequestMapping("/test")
@Api(value="TestController",tags = "自用调试Api接口")
public class TestController {
    @Autowired
    private JPAQueryFactory queryFactory;

//    @Autowired
//    private EntityManager entityManager;

    //实例化控制器完成后执行该方法实例化JPAQueryFactory
//    @PostConstruct
//    public void initFactory() {
//        System.out.println("开始实例化JPAQueryFactory");
//        queryFactory = new JPAQueryFactory(entityManager);
//    }

    @PostMapping("/testc")
    @ApiOperation(value = "自用调试接口", notes = "")
    public Page<Cp> testc(@ApiParam(value = "当前页",required = true,example = "1")
                          @RequestParam(value = "pageNum")String pageNum,
                          @ApiParam(value = "当前页数量",required = true,example = "10")
                          @RequestParam(value = "pageSize")String pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum)-1 ,Integer.parseInt(pageSize),sort);

        return findByPage(pageable);
    }

    public Page<Cp> findByPage(Pageable pageable) {
        /**
         * 1.单表分页查询所有
         * 2.单表条件分页查询
         * 3.多表分页查询
         */
        /**1.单表分页查询所有*/
        QCp cp = QCp.cp;
        QProduct product = QProduct.product;
        QCpProduct cpProduct = QCpProduct.cpProduct;
        JPAQuery jpaQuery = queryFactory
                .select(cp).distinct()
                .from(cp)
//                .where(
//                        cpProduct.cpid.eq(cp.id)
//                                .and(cpProduct.pid.eq(product.id))
//                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        //查找结果
        QueryResults<Tuple> results = jpaQuery.fetchResults();
        Page<Cp> cpPage=new PageImpl(jpaQuery.fetch(),pageable,results.getTotal());
        return cpPage;
    }
}
