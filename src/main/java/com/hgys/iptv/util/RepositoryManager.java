package com.hgys.iptv.util;

import com.google.common.collect.Maps;
import com.hgys.iptv.repository.BaseRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * 对 JpaRepository、JpaSpecificationExecutor的进一步封装
     * 基本CURD 依然使用jpa 已有方法。
     * 拓展条件+分页查询
     * 多表处理等
     *
     * 需要继承 BaseRepository
     */



    /**
     * 根据条件分页查询
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
                    predicates.add(builder.equal(root.get(entry.getKey()), entry.getValue()));
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

    /**
     * 自定义装配内容
     *
     * @param baseRepository
     * @param criteria
     * @param pageable
     * @param func
     * @param <T>
     * @return
     */
    public <T> Page<T> findByCriteriaPage(BaseRepository baseRepository,Map<String,Object> criteria, Pageable pageable, Function func) {
        return this.findByCriteriaPage(baseRepository,criteria, pageable).map(func);
    }


    /**
     * 查询所有+分页查询
     * @param baseRepository
     * @param pageable
     * @param <T>
     * @return
     */
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
     * 多表查询--根据主表id 查主表+关联的从表信息
     * 1.先查主表
     * 2.按主表id查中间表获取所有从表id或中间表对象
     * 3.按从表 idSet 返回所有从表目标对象
     * T==自定义返回视图
     *
     * 多表新增、修改、删除
     **/


    /**
     * 自定义条件查询
     *
     * @param clazz
     * @param map
     * @return
     */
    public <T> List <T> findByCriteria(Class<?> clazz, Map<String,Object> map) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery((clazz));
        Root root = query.from(clazz);
        query.select(root);
//        query.multiselect(root.get("name"),cb.count(root.get("id")))
//                .groupBy(root.get("name")).having(cb.gt(cb.count(root.get("id")),3));
        List<Predicate> predicates = new ArrayList<>();
        if(map!=null && map.size()>0){
            for(Map.Entry<String,Object>entry:map.entrySet()){
                predicates.add(builder.equal(root.get(entry.getKey()), entry.getValue()));
            }
        }
        query.where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * 根据实体类型查找
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List <T> find(Class<?> clazz) {
        return findByCriteria(clazz,null);
    }

    /**
     * 单字段查询
     *
     * @param clazz
     * @param colName
     * @param colValue
     * @param <T>
     * @return
     */
    public <T> List <T> findByCriteria(Class<?> clazz,String colName,Object colValue) {
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
    public T findOneById(Class<?> clazz,Integer id) {
        List<Object> list = findByCriteria(clazz, "id",id);
        if(list!=null && list.size()>0){
            return (T)list.get(0);
        }
        return null;
    }
















//    public <E> RepositoryManager.ModelView decorate(BaseRepository repository, RelationProvider relationProvider, Integer id){
//        ModelView<M,F> modelView = new ModelView<>();
//        E e = (E)repository.findById(id).orElse(null);
//        modelView.setM(e);
//        return modelView;
//    }

    @Getter@Setter
    private static class ModelView<E,F>{
        private E e;
        private List<F> list;
    }
}
