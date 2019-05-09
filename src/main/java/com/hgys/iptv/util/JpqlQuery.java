package com.hgys.iptv.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jqpl查询语句构造器
 *
 * @author 杨鹏
 */
public class JpqlQuery<E, T> {
    private static final String OR = " or ";

    private static final String AND = " and ";

    private static final String ON = " on ";

    private static final String ORDER_BY = " order by ";

    private static final String WHERE = " where ";

    private static final String LEFT_BRACKET = " ( ";
    /**
     * 实体管理器，通过构造器传入
     */
    private EntityManager entityManager;
    /**
     * 查询数据的查询语句
     */
    private StringBuilder queryStringBuilder;
    /**
     * 查询数据的查询语句
     */
    private StringBuilder countQueryStringBuilder;

    /**
     * 查询数据的where语句
     */
    private StringBuilder whereStringBuilder;

    private Map<Class, String> entityAliases;

    private Map<String, Object> whereParameters;

    private Class<E> resultEntity;
    private Class<T> searchEntity;
    private int paramIndex = 1;

    public JpqlQuery() {
        this.entityAliases = new HashMap<>(10);
        this.whereParameters = new HashMap<>(10);
        this.whereStringBuilder = new StringBuilder(" where 1 = 1 ");
        this.queryStringBuilder = new StringBuilder();
        this.countQueryStringBuilder = new StringBuilder();

    }

    public <X, Y> JpqlQuery<E, T> setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        return this;
    }

    public JpqlQuery<E, T> less(SingularAttribute<? super T, ?> attribute, Object value) {
        return less(searchEntity, attribute, value);
    }

    public <X, Y> JpqlQuery<E, T> less(Class<? extends X> entityType, SingularAttribute<X, Y> attribute, Object value) {
        if (entityAliases.get(entityType) == null) {
            throw new RuntimeException("实体未配置,无法设置条件");
        }
        if (attribute == null || value == null) {
            return this;
        }
        if (needAnd()) {
            and();
        }
        String entityTypeAlias = entityAliases.get(entityType);
        String paraName = attribute.getName() + paramIndex;
        paramIndex++;
        whereStringBuilder.append(entityTypeAlias).append(".").append(attribute.getName()).append(" <= :").append(paraName).append(" ");
        whereParameters.put(paraName, value);
        return this;
    }

    public <X, Y> JpqlQuery<E, T> greater(Class<? extends X> entityType, SingularAttribute<X, Y> attribute, Object value) {
        if (entityAliases.get(entityType) == null) {
            throw new RuntimeException("实体未配置,无法设置条件");
        }
        if (attribute == null || value == null) {
            return this;
        }
        if (needAnd()) {
            and();
        }
        String entityTypeAlias = entityAliases.get(entityType);
        String paraName = attribute.getName() + paramIndex;
        paramIndex++;
        whereStringBuilder.append(entityTypeAlias).append(".").append(attribute.getName()).append(" >= :").append(paraName).append(" ");
        whereParameters.put(paraName, value);
        return this;
    }

    public JpqlQuery<E, T> greater(SingularAttribute<? super T, ?> attribute, Object value) {
        return greater(searchEntity, attribute, value);
    }

    public <X, Y> JpqlQuery<E, T> eq(Class<? extends X> entityType, SingularAttribute<X, Y> attribute, Object value) {
        if (entityAliases.get(entityType) == null) {
            throw new RuntimeException("实体未配置,无法设置条件");
        }
        if (attribute == null || value == null) {
            return this;
        }
        if (needAnd()) {
            and();
        }
        String entityTypeAlias = entityAliases.get(entityType);
        String paraName = attribute.getName() + paramIndex;
        paramIndex++;
        whereStringBuilder.append(entityTypeAlias).append(".").append(attribute.getName()).append(" = :").append(paraName).append(" ");
        whereParameters.put(paraName, value);
        return this;
    }

    public JpqlQuery<E, T> eq(SingularAttribute<? super T, ?> attribute, Object value) {
        return eq(searchEntity, attribute, value);
    }

    public <X, Y> JpqlQuery<E, T> isNull(Class<? extends X> entityType, SingularAttribute<X, Y> attribute) {
        if (entityAliases.get(entityType) == null) {
            throw new RuntimeException("实体未配置,无法设置条件");
        }
        if (attribute == null) {
            return this;
        }
        if (needAnd()) {
            and();
        }
        String entityTypeAlias = entityAliases.get(entityType);
        whereStringBuilder.append(entityTypeAlias).append(".").append(attribute.getName()).append(" is null ");
        return this;
    }

    public JpqlQuery<E, T> isNull(SingularAttribute<? super T, ?> attribute) {
        return isNull(searchEntity, attribute);
    }

    public <X, Y> JpqlQuery<E, T> notEq(Class<? extends X> entityType, SingularAttribute<X, Y> attribute, Object value) {
        if (entityAliases.get(entityType) == null) {
            throw new RuntimeException("实体未配置,无法设置条件");
        }
        if (attribute == null || value == null) {
            return this;
        }
        if (needAnd()) {
            and();
        }
        String entityTypeAlias = entityAliases.get(entityType);
        String paraName = attribute.getName() + paramIndex;
        paramIndex++;
        whereStringBuilder.append(entityTypeAlias).append(".").append(attribute.getName()).append(" != :").append(paraName).append(" ");
        whereParameters.put(paraName, value);
        return this;
    }

    public <X, Y> JpqlQuery<E, T> in(Class<? extends X> entityType, SingularAttribute<X, Y> attribute, List<?> value) {
        if (entityAliases.get(entityType) == null) {
            throw new RuntimeException("实体未配置,无法设置条件");
        }
        if (attribute == null || value == null || value.isEmpty()) {
            return this;
        }
        if (needAnd()) {
            and();
        }
        String entityTypeAlias = entityAliases.get(entityType);
        String paraName = attribute.getName() + paramIndex;
        paramIndex++;
        whereStringBuilder.append(entityTypeAlias).append(".").append(attribute.getName()).append(" in :").append(paraName).append(" ");
        whereParameters.put(paraName, value);
        return this;
    }

    public JpqlQuery<E, T> in(SingularAttribute<T, ?> attribute, List<?> value) {
        return in(searchEntity, attribute, value);
    }


    public <X, Y> JpqlQuery<E, T> like(Class<? extends X> entityType, SingularAttribute<X, Y> attribute, Object value) {
        if (entityAliases.get(entityType) == null) {
            throw new RuntimeException("实体未配置,无法设置条件");
        }
        if (attribute == null || value == null) {
            return this;
        }
        if (needAnd()) {
            and();
        }
        String entityTypeAlias = entityAliases.get(entityType);
        String paraName = attribute.getName() + paramIndex;
        paramIndex++;
        whereStringBuilder.append(entityTypeAlias).append(".").append(attribute.getName()).append(" like :").append(paraName).append(" ");
        whereParameters.put(paraName, new StringBuilder("%").append(value).append("%").toString());
        return this;
    }

    public JpqlQuery<E, T> like(SingularAttribute<? super T, ?> attribute, Object value) {
        return like(searchEntity, attribute, value);
    }

    public JpqlQuery<E, T> leftBracket() {
        if (needAnd()) {
            and();
        }
        whereStringBuilder.append(" ( ");
        return this;
    }

    public JpqlQuery<E, T> rightBracket() {
        whereStringBuilder.append(" ) ");
        return this;
    }

    public JpqlQuery<E, T> and() {
        whereStringBuilder.append(AND);
        return this;
    }

    public JpqlQuery<E, T> or() {
        whereStringBuilder.append(OR);
        return this;
    }

    public JpqlQuery<E, T> innerJoin(Class joinEntityType, List<JoinCondition> joinConditions) {
        String joinEntityTypeAlias = WordUtils.uncapitalize(joinEntityType.getSimpleName());
        entityAliases.put(joinEntityType, joinEntityTypeAlias);
        StringBuilder innerJoinString = new StringBuilder(" inner join " + joinEntityType.getSimpleName() + " " + joinEntityTypeAlias + " on ");
        innerJoinString.append(join(joinEntityType, joinConditions));
        this.queryStringBuilder.append(innerJoinString);
        this.countQueryStringBuilder.append(innerJoinString);
        return this;
    }

    public JpqlQuery<E, T> innerJoin(Class joinEntityType, JoinCondition joinCondition) {
        this.innerJoin(joinEntityType, Lists.newArrayList(joinCondition));
        return this;
    }

    public JpqlQuery<E, T> leftJoin(Class joinEntityType, List<JoinCondition> joinConditions) {
        String joinEntityTypeAlias = WordUtils.uncapitalize(joinEntityType.getSimpleName());
        entityAliases.put(joinEntityType, joinEntityTypeAlias);
        StringBuilder leftJoinString = new StringBuilder(" left join " + joinEntityType.getSimpleName() + " " + entityAliases.get(joinEntityType) + " on ");
        leftJoinString.append(join(joinEntityType, joinConditions));
        this.queryStringBuilder.append(leftJoinString);
        this.countQueryStringBuilder.append(leftJoinString);
        return this;
    }

    public JpqlQuery<E, T> leftJoin(Class joinEntityType, JoinCondition joinCondition) {
        this.leftJoin(joinEntityType, Lists.newArrayList(joinCondition));
        return this;
    }

    public JpqlQuery<E, T> select(Class<E> resultEntity) {
        String entityAlias = WordUtils.uncapitalize(resultEntity.getSimpleName());
        this.resultEntity = resultEntity;
        this.entityAliases.put(resultEntity, entityAlias);
        this.queryStringBuilder = new StringBuilder("select ").append(entityAlias);
        this.countQueryStringBuilder = new StringBuilder("select count (").append(entityAlias).append(") ");
        return this;
    }

    public JpqlQuery<E, T> distinctSelect(Class<E> resultEntity) {
        String entityAlias = WordUtils.uncapitalize(resultEntity.getSimpleName());
        this.resultEntity = resultEntity;
        this.entityAliases.put(resultEntity, entityAlias);
        this.queryStringBuilder.append("select distinct ").append(entityAlias);
        this.countQueryStringBuilder.append("select count (distinct ").append(entityAlias).append(") ");
        ;
        return this;
    }

    public JpqlQuery<E, T> from(Class<T> searchEntity) {
        this.searchEntity = searchEntity;
        String entityAlias = WordUtils.uncapitalize(searchEntity.getSimpleName());
        this.entityAliases.put(searchEntity, entityAlias);
        this.queryStringBuilder.append(" from ").append(searchEntity.getSimpleName()).append(" ").append(entityAlias);
        this.countQueryStringBuilder.append(" from ").append(searchEntity.getSimpleName()).append(" ").append(entityAlias);
        return this;
    }

    public StringBuilder join(Class joinEntityType, List<JoinCondition> joinConditions) {
        if (entityAliases.get(joinEntityType) == null) {
            throw new RuntimeException("未设置的实体，无法进行join");
        }
        String joinEntityTypeAlias = entityAliases.get(joinEntityType);
        StringBuilder joinString = new StringBuilder();
        joinConditions.forEach(joinCondition -> {
            if (StringUtils.endsWithIgnoreCase(joinString.toString(), ON)) {
                joinString.append(AND);
            }
            joinString
                    .append(entityAliases.get(joinCondition.getLeftEntity()))
                    .append(".")
                    .append(joinCondition.getLeftAttribute().getName())
                    .append(" = ")
                    .append(entityAliases.get(joinCondition.getRightEntity()))
                    .append(".")
                    .append(joinCondition.getRightAttribute().getName());
        });
        return joinString;
    }

    public JpqlQuery<E, T> sort(String sortCondition, Sort.Direction direction) {
        if (!StringUtils.contains(queryStringBuilder.toString(), WHERE)) {
            queryStringBuilder.append(whereStringBuilder);
            countQueryStringBuilder.append(whereStringBuilder);
        }
        if (!StringUtils.contains(queryStringBuilder.toString(), ORDER_BY)) {
            queryStringBuilder.append(ORDER_BY);
        }
        queryStringBuilder.append(sortCondition).append(" ").append(direction.name()).append(", ");
        return this;
    }


    public PageImpl<E> execute(Pageable pageable) {
        Sort sort = pageable.getSort();
        if (!StringUtils.contains(queryStringBuilder.toString(), WHERE)) {
            queryStringBuilder.append(whereStringBuilder);
            countQueryStringBuilder.append(whereStringBuilder);
        }
        if (sort.stream().count() > 0) {
            if (!StringUtils.contains(queryStringBuilder.toString(), ORDER_BY)) {
                queryStringBuilder.append(ORDER_BY);
            }
            sort.forEach(order -> {
                if (StringUtils.containsIgnoreCase(queryStringBuilder.toString(), Sort.Direction.DESC.toString()) || StringUtils.containsIgnoreCase(queryStringBuilder.toString(), Sort.Direction.ASC.toString())) {
                    queryStringBuilder.append(", ");
                }
                queryStringBuilder
                        .append(entityAliases.get(searchEntity))
                        .append(".")
                        .append(order.getProperty())
                        .append(" ")
                        .append(order.getDirection().toString().toLowerCase());
            });
        }
        String queryString = queryStringBuilder.toString();
        queryString = StringUtils.removeEnd(queryString, ", ");
        String countQueryString = countQueryStringBuilder.toString();
        countQueryString = StringUtils.removeEnd(countQueryString, ", ");
        TypedQuery<E> query = entityManager.createQuery(queryString, resultEntity);
        TypedQuery<Long> countQuery = entityManager.createQuery(countQueryString, Long.class);
        if (!whereParameters.isEmpty()) {
            whereParameters.forEach((key, value) -> {
                query.setParameter(key, value);
                countQuery.setParameter(key, value);
            });
        }
        Long firstResult = pageable.getOffset();

        List<E> list = query.setFirstResult(firstResult.intValue()).setMaxResults(pageable.getPageSize()).getResultList();
        Long count = countQuery.getSingleResult();
        if (list.isEmpty()) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
        return new PageImpl<>(list, pageable, count);
    }

    private boolean needAnd() {
        if (StringUtils.endsWithIgnoreCase(whereStringBuilder.toString(), OR)) {
            return false;
        }
        if (StringUtils.endsWithIgnoreCase(whereStringBuilder.toString(), AND)) {
            return false;
        }
        return !StringUtils.endsWithIgnoreCase(whereStringBuilder.toString(), LEFT_BRACKET);
    }
}
