//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hgys.iptv.model.dao;

import com.hgys.iptv.model.vo.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EntityDaoImpl implements EntityDao {
	//通过该注释可以保证将线程安全的EntityManager注入到DAO组件中
    @PersistenceContext
    protected EntityManager em;

    public EntityDaoImpl() {
    }

    public <T> T get(Class<T> entityClass, Object entityId) throws SQLException {
        return this.em.find(entityClass, entityId);
    }

    public <T> T get(Class<T> entityClass, String whereJpql, Object[] queryParams) throws SQLException {
        QueryResult qr = this.getPagingData(entityClass, 1, 1, whereJpql, queryParams);
        List result = qr.getRows();
        return result != null && !result.isEmpty()?(T)result.get(0):null;
    }

    @Override
    public <T> QueryResult<T> getPagingData(Class<T> entityClass, int firstIndex, int maxResult, String whereJpql, Object[] queryParams, LinkedHashMap<String, String> orderBy) throws SQLException {
        QueryResult qr = new QueryResult();
        Query query = this.em.createQuery("SELECT o FROM " + this.getEntityName(entityClass) + " o " + (whereJpql == null?"":"WHERE " + whereJpql) + this.buildOrderby(orderBy));
        this.setQueryParams(query, queryParams);
        if(firstIndex != -1 && maxResult != -1) {
            if(firstIndex < 1) {
                firstIndex = 1;
            }

            if(maxResult < 1) {
                maxResult = 1;
            }

            query.setFirstResult((firstIndex - 1) * maxResult).setMaxResults(maxResult);
        }

        qr.setRows(query.getResultList());
        query = this.em.createQuery("SELECT COUNT(o) FROM " + this.getEntityName(entityClass) + " o " + (whereJpql == null?"":"WHERE " + whereJpql));
        this.setQueryParams(query, queryParams);
        qr.setTotal(((Long)query.getSingleResult()).longValue());
        return qr;
    }

    private void setQueryParams(Query query, Object[] queryParams) {
        if(queryParams != null && queryParams.length > 0) {
            for(int i = 0; i < queryParams.length; ++i) {
                query.setParameter(i + 1, queryParams[i]);
            }
        }

    }

    private String buildOrderby(LinkedHashMap<String, String> orderby) {
        StringBuffer orderbyql = new StringBuffer("");
        if(orderby != null && orderby.size() > 0) {
            orderbyql.append(" ORDER BY ");
            Iterator i$ = orderby.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                orderbyql.append("o.").append(key).append(" ").append((String)orderby.get(key)).append(",");
            }

            orderbyql.deleteCharAt(orderbyql.length() - 1);
        }

        return orderbyql.toString();
    }

    private <T> String getEntityName(Class<T> entityClass) {
        String entityname = entityClass.getSimpleName();
        Entity entity = (Entity)entityClass.getAnnotation(Entity.class);
        if(entity.name() != null && !"".equals(entity.name())) {
            entityname = entity.name();
        }

        return entityname;
    }

    @Override
    public <T> QueryResult<T> getPagingData(Class<T> entityClass, int firstIndex, int maxResult, String whereJpql, Object[] queryParams) throws SQLException {
        return this.getPagingData(entityClass, firstIndex, maxResult, whereJpql, queryParams, (LinkedHashMap)null);
    }

    @Override
    public <T> QueryResult<T> getPagingData(Class<T> entityClass, int firstIndex, int maxResult, LinkedHashMap<String, String> orderBy) throws SQLException {
        return this.getPagingData(entityClass, firstIndex, maxResult, (String)null, (Object[])null, orderBy);
    }

    @Override
    public <T> QueryResult<T> getPagingData(Class<T> entityClass, int firstIndex, int maxResult) throws SQLException {
        return this.getPagingData(entityClass, firstIndex, maxResult, (String)null, (Object[])null, (LinkedHashMap)null);
    }

    @Override
    public <T> QueryResult<T> getPagingData(Class<T> entityClass, String whereJpql, Object[] queryParams) throws SQLException {
        return this.getPagingData(entityClass, -1, -1, whereJpql, queryParams, (LinkedHashMap)null);
    }

    @Override
    public <T> QueryResult<T> getPagingData(Class<T> entityClass) throws SQLException {
        return this.getPagingData(entityClass, -1, -1, (String)null, (Object[])null, (LinkedHashMap)null);
    }

    @Override
    public <T> List<T> getPagingDataByJpql(Class<T> entityClass, String jpql, Object[] queryParams, int firstIndex, int maxResult) throws SQLException {
        if(StringUtils.isEmpty(jpql)) {
            return null;
        } else {
            TypedQuery query = this.em.createQuery(jpql, entityClass);
            this.setQueryParams(query, queryParams);
            if(firstIndex != -1 && maxResult != -1) {
                if(firstIndex < 1) {
                    firstIndex = 1;
                }

                if(maxResult < 1) {
                    maxResult = 1;
                }

                query.setFirstResult((firstIndex - 1) * maxResult).setMaxResults(maxResult);
            }

            return query.getResultList();
        }
    }

    @Override
    public List<Map<String, Object>> getPagingDataByHql(String hql, Object[] queryParams, int firstIndex, int maxResult) throws SQLException {
        if(StringUtils.isEmpty(hql)) {
            return null;
        } else {
            Session session = (Session)this.em.getDelegate();
            org.hibernate.Query query = session.createQuery(hql);
            if(queryParams != null && queryParams.length > 0) {
                for(int results = 0; results < queryParams.length; ++results) {
                    query.setParameter(results, queryParams[results]);
                }
            }

            if(firstIndex != -1 && maxResult != -1) {
                if(firstIndex < 1) {
                    firstIndex = 1;
                }

                if(maxResult < 1) {
                    maxResult = 1;
                }

                query.setFirstResult((firstIndex - 1) * maxResult).setMaxResults(maxResult);
            }

            List var8 = query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
            return var8;
        }
    }

    @Override
    public List<Map<String, Object>> getPagingDataBySql(String sql, Object[] queryParams, int firstIndex, int maxResult) throws SQLException {
        if(StringUtils.isEmpty(sql)) {
            return null;
        } else {
            Session session = (Session)this.em.getDelegate();
            SQLQuery query = session.createSQLQuery(sql);
            if(queryParams != null && queryParams.length > 0) {
                for(int results = 0; results < queryParams.length; ++results) {
                    query.setParameter(results, queryParams[results]);
                }
            }

            if(firstIndex != -1 && maxResult != -1) {
                if(firstIndex < 1) {
                    firstIndex = 1;
                }

                if(maxResult < 1) {
                    maxResult = 1;
                }

                query.setFirstResult((firstIndex - 1) * maxResult).setMaxResults(maxResult);
            }

            List var8 = query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
            return var8;
        }
    }

    @Override
    public <T> List<T> getPagingDataBySql(Class<T> entityClass, String sql, Object[] queryParams, int firstIndex, int maxResult) throws SQLException {
        if(StringUtils.isEmpty(sql)) {
            return null;
        } else {
            Query query = this.em.createNativeQuery(sql, entityClass);
            if(queryParams != null && queryParams.length > 0) {
                for(int i = 0; i < queryParams.length; ++i) {
                    query.setParameter(i, queryParams[i]);
                }
            }

            if(firstIndex != -1 && maxResult != -1) {
                if(firstIndex < 1) {
                    firstIndex = 1;
                }

                if(maxResult < 1) {
                    maxResult = 1;
                }

                query.setFirstResult((firstIndex - 1) * maxResult).setMaxResults(maxResult);
            }

            return query.getResultList();
        }
    }

    @Override
    public Integer save(Object entity) throws SQLException {
        Session session = (Session)this.em.getDelegate();
        Integer id = (Integer) session.save(entity);
        return id;
    }

    @Override
    public boolean update(Object entity) throws SQLException {
        return this.em.merge(entity) != null;
    }

    @Override
    public void delete(Object entity) throws SQLException {
        this.em.remove(entity);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }
}
