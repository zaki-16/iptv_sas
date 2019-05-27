package com.hgys.iptv.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 抽取公共接口
 * @param <T>
 * @param <I>
 */
@NoRepositoryBean
public interface BaseRepository<T,I extends Serializable> extends JpaRepository<T,I>, JpaSpecificationExecutor<T> {

    /**
     * 获取单字段值的集合
     * @param var1 选择的字段名
     * @param table 表名
     * @param var2 条件名
     * @param var2_val 条件值
     * @return set
     */
    @Query(value = "select :var1 from :table where 1=1 and :var2 = :var2_val",nativeQuery = true)
    Set<Object> getSingleColSet(
            @Param("var1") String var1,
            @Param("table") String table,
            @Param("var2") String var2,
            @Param("var2_val") Object var2_val
    );

}

