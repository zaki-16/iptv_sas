package com.hgys.iptv.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.metamodel.SingularAttribute;

/**
 * @ClassName RelationProvider
 * @Auther: wangz
 * @Date: 2019/5/21 13:57
 * @Description: TODO
 */
@Data
@AllArgsConstructor
public class RelationProvider<L,M,R> {
    private L l;
    private Class<L> leftClass;

    private SingularAttribute<? super L, ?> leftAttribute;

    private M m;
    private Class<M> midClass;
    private String left_k;//中间表关联的左表键名
    private Integer left_v;//中间表关联的左表键值
    private String right_k;
    private Integer right_v;
    private SingularAttribute<? super M, ?> midAttribute;

    private R r;
    private Class<R> rightClass;
    private SingularAttribute<? super R, ?> rightAttribute;

}
