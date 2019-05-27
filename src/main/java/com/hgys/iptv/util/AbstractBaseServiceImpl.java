package com.hgys.iptv.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

/**

 * @Auther: wangz
 * @Date: 2019/5/9 11:33
 * @Description: TODO
 */
@Component
public abstract class AbstractBaseServiceImpl {
    @Autowired
    private RepositoryManager repositoryManager;
    /**
     * 自定义条件查询
     *
     * @param clazz
     * @param map
     * @return
     */
    public <T> List <T> findByCriteria(Class<T> clazz, Map<String,Object> map) {
        return repositoryManager.findByCriteria(clazz,map);
    }



}
