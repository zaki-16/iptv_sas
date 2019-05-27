package com.hgys.iptv.common;

/**
 * @ClassName Criteria
 * @Auther: wangz
 * @Date: 2019/5/25 17:11
 * @Description: TODO
 */

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件容器
 *
 * @param <T>
 */
public class Criteria<T> implements Specification<T> {
    private static final long serialVersionUID = -1693502719868886705L;

    private List<Criterion> criterions = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (!criterions.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            for(Criterion c : criterions){
                predicates.add(c.toPredicate(root, query,builder));
            }
            // 将所有条件用 and 联合起来
            if (predicates.size() > 0) {
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return builder.conjunction();
    }
    /**
     * 增加简单条件表达式
     * @Methods Name add
     * @param
     */
    public Criteria<T> add(Criterion criterion){

        if(criterion!=null){
            criterions.add(criterion);
        }

        return this;
    }
}
