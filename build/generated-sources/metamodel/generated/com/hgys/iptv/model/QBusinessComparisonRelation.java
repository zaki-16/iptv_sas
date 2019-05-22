package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBusinessComparisonRelation is a Querydsl query type for BusinessComparisonRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBusinessComparisonRelation extends EntityPathBase<BusinessComparisonRelation> {

    private static final long serialVersionUID = 819100455L;

    public static final QBusinessComparisonRelation businessComparisonRelation = new QBusinessComparisonRelation("businessComparisonRelation");

    public final StringPath businessCode = createString("businessCode");

    public final StringPath businessName = createString("businessName");

    public final StringPath code = createString("code");

    public final DateTimePath<java.sql.Timestamp> create_time = createDateTime("create_time", java.sql.Timestamp.class);

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

