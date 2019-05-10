package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.BusinessComparisonRelation;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.TimePath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QBusinessComparisonRelation is a Querydsl query type for BusinessComparisonRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBusinessComparisonRelation extends EntityPathBase<BusinessComparisonRelation> {

    private static final long serialVersionUID = 819100455L;

    public static final QBusinessComparisonRelation businessComparisonRelation = new QBusinessComparisonRelation("businessComparisonRelation");

    public final StringPath businessCode = createString("businessCode");

    public final StringPath businessName = createString("businessName");

    public final TimePath<java.sql.Time> create_time = createTime("create_time", java.sql.Time.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath masterCode = createString("masterCode");

    public final StringPath masterName = createString("masterName");

    public QBusinessComparisonRelation(String variable) {
        super(BusinessComparisonRelation.class, forVariable(variable));
    }

    public QBusinessComparisonRelation(Path<? extends BusinessComparisonRelation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusinessComparisonRelation(PathMetadata metadata) {
        super(BusinessComparisonRelation.class, metadata);
    }

}

