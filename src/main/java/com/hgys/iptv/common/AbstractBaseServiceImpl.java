package com.hgys.iptv.common;

/**

 * @Auther: wangz
 * @Date: 2019/5/9 11:33
 * @Description: TODO
 */

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.*;

@NoRepositoryBean
public abstract class AbstractBaseServiceImpl {

    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * 自定义条件查询
     *
     * @param clazz
     * @param map
     * @return
     */
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

    /**
     * 使用jpql进行分页查询
     * @param entityClass
     * @param whereJpql
     * @param params
     * @param start
     * @param limit
     * @param order
     * @param <T>
     * @return
     */
    public <T> Page<T> findPageByHql(Class<T> entityClass, String whereJpql, Object[] params, Integer start, Integer limit, LinkedHashMap<String, String> order) {
        StringBuffer dataQuery = new StringBuffer("");
        StringBuffer dataCount = new StringBuffer("");
        dataQuery.append("SELECT o FROM ").append(this.getEntityName(entityClass)).append(" o ");
        if (whereJpql != null) {
            dataQuery.append("WHERE ").append(whereJpql);
        }
        if (order != null) {
            Set<String> orderNames = order.keySet();
            Iterator<String> orderNamesIter = orderNames.iterator();
            if (orderNames.size() != 0) {
                dataQuery.append(" order by ");
            }
            while (orderNamesIter.hasNext()) {
                String orderName = orderNamesIter.next();
                dataQuery.append("o.");
                dataQuery.append(orderName);
                dataQuery.append(" ");
                dataQuery.append(order.get(orderName));
                if (orderNamesIter.hasNext()) {
                    dataQuery.append(",");
                }
            }
        }
        dataCount.append("SELECT count(o) FROM ").append(this.getEntityName(entityClass)).append(" o ");
        if (whereJpql != null) {
            dataCount.append("WHERE ").append(whereJpql);
        }
        Query query = entityManager.createQuery(dataQuery.toString());
        Query countQuery = entityManager.createQuery(dataCount.toString());
        setHQueryParameter(params,query);
        setHQueryParameter(params,countQuery);
        query.setFirstResult(start * limit).setMaxResults(limit);
        List<T> list = query.getResultList();
        Long count = (Long) countQuery.getSingleResult();
        return new PageImpl<T>(list, PageRequest.of(start, limit), count);
    }

    /**
     * 使用hql进行分页查询
     * @param start
     * @param limit
     * @param hql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> findPageByHql(String hql, int start, int limit, Object[] params, LinkedHashMap<String, String> order) {
        if(StringUtils.isEmpty(hql)) {
            return null;
        } else {
            Session session = (Session)this.entityManager.getDelegate();
            StringBuffer sb = new StringBuffer(hql);
            if (order != null) {
                Set<String> orderNames = order.keySet();
                Iterator<String> orderNamesIter = orderNames.iterator();
                if (orderNames.size() != 0) {
                    sb.append(" order by ");
                }
                while (orderNamesIter.hasNext()) {
                    String orderName = orderNamesIter.next();
                    sb.append(orderName);
                    sb.append(" ");
                    sb.append(order.get(orderName));
                    if (orderNamesIter.hasNext()) {
                        sb.append(",");
                    }
                }
            }
            org.hibernate.Query query = session.createQuery(sb.toString());
            setHQueryParameter(params,query);
            query.setFirstResult(start * limit).setMaxResults(limit);
            return query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
        }
    }

    /**
     * 使用sql进行分页查询
     * @param sql
     * @param firstIndex
     * @param maxResult
     * @param queryParams
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> findPageBySql(String sql, int firstIndex, int maxResult, Object[] queryParams) {
        if(StringUtils.isEmpty(sql)) {
            return null;
        } else {
            Session session = (Session)this.entityManager.getDelegate();
            SQLQuery query = session.createSQLQuery(sql);
            setHQueryParameter(queryParams,query);
            setHQueryPage(firstIndex,maxResult,query);
            return query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
        }
    }

    /**
     *
     * @param firstIndex
     * @param maxResult
     * @param query
     */
    private void setHQueryPage(int firstIndex,int maxResult,Query query){
        if(firstIndex != -1 && maxResult != -1) {
            firstIndex = getNumber(firstIndex);
            maxResult = getNumber(maxResult);
            query.setFirstResult((firstIndex - 1) * maxResult).setMaxResults(maxResult);
        }
    }

    /**
     *
     * @param queryParams
     * @param query
     */
    private void setHQueryParameter(Object[] queryParams, Query query){
        if(queryParams != null && queryParams.length > 0) {
            for(int results = 0; results < queryParams.length; ++results) {
                query.setParameter(results, queryParams[results]);
            }
        }
    }

    /**
     *
     * @param queryParams
     * @param query
     */
    private void setQueryParameter(Object[] queryParams,Query query) {
        if(queryParams != null && queryParams.length > 0) {
            for(int i = 0; i < queryParams.length; ++i) {
                query.setParameter(i + 1, queryParams[i]);
            }
        }
    }

    /**
     *
     * @param num
     * @return
     */
    private int getNumber(int num){
        if(num < 1) {
            num = 1;
        }
        return num;
    }

    /**
     *
     * @param entityClass
     * @param <T>
     * @return
     */
    private <T> String getEntityName(Class<T> entityClass) {
        String entityname = entityClass.getSimpleName();
        Entity entity = (Entity) entityClass.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name())) {
            entityname = entity.name();
        }

        return entityname;
    }
}
