package com.hgys.iptv.util;

import com.hgys.iptv.controller.vm.ProductListVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.ProductBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RepositoryUtil
 * @Auther: wangz
 * @Date: 2019/5/9 18:45
 * @Description: TODO
 */
@Component
public class RepositoryUtil{
    @Autowired
    private EntityManager entityManager;
    public List <Object> findByCriteria(Class clazz, Map<String,Object> map) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> query = cb.createQuery((clazz));
        Root<Object> root = query.from(clazz);
        query.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if(map!=null && map.size()>0){
            for(Map.Entry<String,Object>entry:map.entrySet()){
                predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
            }
        }
        //where
        query.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Object> typedQuery = entityManager.createQuery(query);
        List <Object> content = typedQuery.getResultList();
        return content;
    }
}
