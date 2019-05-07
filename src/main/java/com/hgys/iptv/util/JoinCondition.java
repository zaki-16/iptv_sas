package com.hgys.iptv.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.metamodel.SingularAttribute;

/**
 * join条件
 *
 * @author 杨鹏
 */
@Data
@AllArgsConstructor
public class JoinCondition<X, Y> {
    private Class<X> leftEntity;
    private SingularAttribute<? super X, ?> leftAttribute;
    private Class<Y> rightEntity;
    private SingularAttribute<? super Y, ?> rightAttribute;
}
