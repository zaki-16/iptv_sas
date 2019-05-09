//package com.hgys.iptv.util;
//
//import com.hgys.iptv.controller.vm.ProductListVM;
//import com.hgys.iptv.model.Business;
//import com.hgys.iptv.model.ProductBusiness;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName RepositoryUtil
// * @Auther: wangz
// * @Date: 2019/5/9 18:45
// * @Description: TODO
// */
//
//public class RepositoryUtil<T> {
//
//    @Autowired
//    private EntityManager entityManager;
//    public List<T> findListById(Class clazz, Integer id) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<T> query = cb.createQuery(clazz);
//        Root<T> cpRoot = query.from(clazz);
//        query.select(cpRoot);
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(cb.equal(cpRoot.get("id"), id));
//        //where
//        query.where(predicates.toArray(new Predicate[]{}));
//        TypedQuery<T> typedQuery = entityManager.createQuery(query);
//        List <T> content = typedQuery.getResultList();
//        return content;
//    }
//    //
//    public List<Business> findBusinessListById(Integer id) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Business> query = cb.createQuery(Business.class);
//        Root<Business> cpRoot = query.from(Business.class);
//        query.select(cpRoot);
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(cb.equal(cpRoot.get("id"), id));
//        //where
//        query.where(predicates.toArray(new Predicate[]{}));
//        TypedQuery<Business> typedQuery = entityManager.createQuery(query);
//        List <Business> content = typedQuery.getResultList();
//        return content;
//    }
//
//    //
//    public List<ProductBusiness> findProductBusinessListBy(Integer pid) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ProductBusiness> query = cb.createQuery(ProductBusiness.class);
//        Root<ProductBusiness> cpRoot = query.from(ProductBusiness.class);
//        query.select(cpRoot);
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(cb.equal(cpRoot.get("pid"), pid));
//        //where
//        query.where(predicates.toArray(new Predicate[]{}));
//        TypedQuery<ProductBusiness> typedQuery = entityManager.createQuery(query);
//        List <ProductBusiness> content = typedQuery.getResultList();
//        return content;
//    }
//}
