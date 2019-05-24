package com.hgys.iptv.util;

import com.google.common.collect.Maps;
import com.hgys.iptv.repository.BaseRepository;
import lombok.Getter;
import lombok.Setter;
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

    private RepositoryManager(){}

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
     * 获取单字段值
     * // select name from table where id=1 limit 1
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


    //============================返回模型视图===================================================================

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
}
