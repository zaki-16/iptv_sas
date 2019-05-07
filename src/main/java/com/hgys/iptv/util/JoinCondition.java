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

    public Class<X> getLeftEntity() {
        return leftEntity;
    }

    public void setLeftEntity(Class<X> leftEntity) {
        this.leftEntity = leftEntity;
    }

    public SingularAttribute<? super X, ?> getLeftAttribute() {
        return leftAttribute;
    }

    public void setLeftAttribute(SingularAttribute<? super X, ?> leftAttribute) {
        this.leftAttribute = leftAttribute;
    }

    public Class<Y> getRightEntity() {
        return rightEntity;
    }

    public void setRightEntity(Class<Y> rightEntity) {
        this.rightEntity = rightEntity;
    }

    public SingularAttribute<? super Y, ?> getRightAttribute() {
        return rightAttribute;
    }

    public void setRightAttribute(SingularAttribute<? super Y, ?> rightAttribute) {
        this.rightAttribute = rightAttribute;
    }
}
