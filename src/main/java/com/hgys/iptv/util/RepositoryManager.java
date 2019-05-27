package com.hgys.iptv.util;

import com.google.common.collect.Maps;
import com.hgys.iptv.repository.BaseRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

/**
 * @ClassName RepositoryManager
 * @Auther: wangz
 * @Date: 2019/5/21 11:38
 * @Description: TODO
 */
@Component
@SuppressWarnings("unchecked")
public class RepositoryManager {
    @PersistenceContext
    private EntityManager entityManager;

    private final String LIKE_FLAG = "%";

    private RepositoryManager(){}

    /**
     * 1.view层需要：按单、多条件(模糊、时间域)查询、分页、排序
     * 2.业务内部调用一般 查单个实体，查all的list
     * select name from table where
     *
     */
    /**
     * 排序：
     *
     List<Order> orders=new ArrayList<>();
     orders.add( new Order(Direction. ASC, "c"));
     orders.add( new Order(Direction. DESC, "d"));
     Pageable pageable= new PageRequest(pageNumber, pageSize, new Sort(orders));

     时间：
     predicates.add(builder.between(root.get("time"),startTime,endTime))
     predicates.add(builder.lessThanOrEqualTo(root.get("commitTime"), etime));
     predicates.add(builder.greaterThanOrEqualTo(root.get("commitTime"), stime));
     *
     */


    public void select(Class<?> clazz){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery((clazz));
        Root root = query.from(clazz);
        query.select(root);
    }

    /**************************************************************条件分页查询***********************************************************************/

    /**
     * 带条件分页+模糊查询
     *
     * @param baseRepository
     * @param criteria
     * @param pageable
     * @param <T>
     * @return
     */
    public <T> Page<T> findByCriteriaPage(BaseRepository baseRepository,Map<String,Object> criteria, Pageable pageable) {
        return baseRepository.findAll(((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null && criteria.size()>0){
                for(Map.Entry<String,Object>entry:criteria.entrySet()){
                    if(entry.getValue()!=null){
                        // 是否需要模糊查询
                        if(entry.getValue().toString().startsWith("%")||
                                entry.getValue().toString().endsWith("%"))
                            predicates.add(builder.like(root.get(entry.getKey()), entry.getValue().toString()));
//                        else if()//判断是否是日期或时间格式

                        else
                            predicates.add(builder.equal(root.get(entry.getKey()), entry.getValue()));
                    }
                }
            }
            if (!predicates.isEmpty()){
                return builder.and(predicates.toArray(new Predicate[0]));
            }
            return builder.conjunction();
        }),pageable);
    }

    public <T> Page<T> findByCriteriaPage(BaseRepository baseRepository, Map<String,Object> criteria, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum -1 ,pageSize);
        return this.findByCriteriaPage(baseRepository,criteria,pageable);
    }

    public <T> Page<T> findByCriteriaPage(BaseRepository baseRepository,Map<String,Object> criteria, Pageable pageable, Function func) {
        return this.findByCriteriaPage(baseRepository,criteria, pageable).map(func);
    }

    public <T> Page<T> findByPage(BaseRepository baseRepository, Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    public <T> Page<T> findByPage(BaseRepository baseRepository, Integer pageNum, Integer pageSize) {
//        Sort sort = new Sort(Sort.Direction.DESC,"");
        Pageable pageable = PageRequest.of(pageNum -1 ,pageSize);
        return this.findByPage(baseRepository,pageable);
    }

    public <T> Page<T> findByPage(BaseRepository baseRepository, Pageable pageable, Function func) {
        return this.findByPage(baseRepository, pageable).map(func);
    }


    /**
     * 按实体类型条件+模糊查询
     *
     * @param clazz
     * @param map
     * @return
     */
    public <T> List <T> findByCriteria(Class<T> clazz, Map<String,Object> map) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery((clazz));
        Root root = query.from(clazz);
        query.select(root);
//        query.multiselect(root.get("name"),cb.count(root.get("id")))
//                .groupBy(root.get("name")).having(cb.gt(cb.count(root.get("id")),3));
        List<Predicate> predicates = new ArrayList<>();
        if(map!=null && map.size()>0){
            for(Map.Entry<String,Object>entry:map.entrySet()){
                if(entry.getValue()!=null){
                    //是否需要模糊查询
                    if(entry.getValue().toString().startsWith("%")||
                            entry.getValue().toString().endsWith("%"))
                        predicates.add(builder.like(root.get(entry.getKey()), entry.getValue().toString()));
                    else
                        predicates.add(builder.equal(root.get(entry.getKey()), entry.getValue()));
                }
            }
        }
        query.where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }



    public <T> List <T> find(Class<T> clazz) {
        return findByCriteria(clazz,null);
    }

    /**
     * 单字段查询
     * where colName = colValue;
     *
     * @param clazz
     * @param colName
     * @param colValue
     * @param <T>
     * @return
     */
    public <T> List <T> findByCriteria(Class<T> clazz,String colName,Object colValue) {
        LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
        map.put(colName,colValue);
        return findByCriteria(clazz,map);
    }

    /**
     * 根据实体类型和id返回该对象
     *
     * @param clazz
     * @param id
     * @return
     */
    public <T> T findOneById(Class<T> clazz, Integer id) {
        List<T> list = findByCriteria(clazz, "id",id);
        if(list!=null && list.size()>0){
            return (T)list.get(0);
        }
        return null;
    }

    /**
     * 一般按id、索引、unique的字段 查询--返回唯一结果
     * 否则返回list的第一个元素
     * @param clazz
     * @param colName
     * @param colValue
     * @param <T>
     * @return
     */
    public <T> T findOne(Class<T> clazz,String colName,Object colValue) {
        List<T> list = findByCriteria(clazz, colName,colValue);
        if(list!=null && list.size()>0){
            return (T)list.get(0);
        }
        return null;
    }

    /**
     * 获取单字段值的集合
     * @param var1 选择的字段名
     * @param table 表名
     * @param var2 条件名
     * @param var2_val 条件值
     * @return set
     */
    public Set<Object> getSingleColSet(BaseRepository baseRepository,String var1,String table, String var2, Object var2_val) {
        return baseRepository.getSingleColSet(var1,table,var2,var2_val);
    }



    /**************************************************************返回模型视图***********************************************************************/

    //
    public <E,F>ModelView decorate(BaseRepository repository, Integer id){
        ModelView modelView = new ModelView<>();
        E e = (E)repository.findById(id).orElse(null);
        modelView.setElem(e);

        return modelView;
    }


    @Getter@Setter
    public static class ModelView<E,F>{
        private E elem;
        private List<F> list;
    }

    public static ModelView getModelView(){
        return new RepositoryManager.ModelView();
    }

    /**************************************************************sql查询***********************************************************************/
    /**
     * 使用jpql进行分页查询
     * @param entityClass
     * @param whereJpql
     * @param params
     * @param start
     * @param limit
     * @param order
     * @param <T>
     * @return SELECT o FROM entityClass.getName whereJpql order by
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
            org.hibernate.query.Query query = session.createQuery(sb.toString());
            setHQueryParameter(params,query);
            query.setFirstResult(start * limit).setMaxResults(limit);
            return query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

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
            NativeQuery query = session.createSQLQuery(sql);
            setHQueryParameter(queryParams,query);
            setHQueryPage(firstIndex,maxResult,query);
            return query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
//            return query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
        }
    }

    /**
     *
     * @param firstIndex
     * @param maxResult
     * @param query
     */
    private void setHQueryPage(int firstIndex, int maxResult, Query query){
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
