//package com.hgys.iptv.util;
//
//import com.hgys.iptv.model.Cp;
//import com.hgys.iptv.repository.CpRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName Test
// * @Auther: wangz
// * @Date: 2019/5/8 14:35
// * @Description: TODO
// */
//public class Test {
//    @Autowired
//    private CpRepository cpRepository;
//    @org.junit.Test
//    public void test(){
//        JpqlQuery jpqlQuery =new JpqlQuery<Cp, PageBean<Cp>>();
//        Cp cp = new Cp();
//        cp.setName("");
//        cp.setId(1);
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        Pageable pageable = PageRequest.of(1,1,sort);
//        Page d =findAll(cp,pageable);
//        System.out.println(d.toString());
//    }
//    public Page<Cp> findAll(Cp cp, Pageable pageable) {
//        Page<Cp> page = cpRepository
//                .findAll((Root<Cp> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//                    List<Predicate> predicates = new ArrayList<Predicate>();
//                    predicates.add(cb.like(root.get("userName").as(String.class), "%"+cp.getName() + "%"));
//                    predicates.add(cb.equal(root.get("isdelete").as(Integer.class), cp.getIsdelete()));
//                    return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
//                }, pageable);
//        return page;
//
//    }
//
//}
