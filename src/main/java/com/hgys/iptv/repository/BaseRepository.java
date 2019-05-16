package com.hgys.iptv.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 抽取公共接口
 * @param <T>
 * @param <I>
 */
@NoRepositoryBean
public interface BaseRepository<T,I extends Serializable> extends JpaRepository<T,I>, JpaSpecificationExecutor<T> {

}

