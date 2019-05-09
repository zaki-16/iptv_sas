package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.SettlementCombinatorialDimensionFrom;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSettlementCombinatorialDimensionFrom is a Querydsl query type for SettlementCombinatorialDimensionFrom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettlementCombinatorialDimensionFrom extends EntityPathBase<SettlementCombinatorialDimensionFrom> {

    private static final long serialVersionUID = 1968956963L;

    public static final QSettlementCombinatorialDimensionFrom settlementCombinatorialDimensionFrom = new QSettlementCombinatorialDimensionFrom("settlementCombinatorialDimensionFrom");

    public final DateTimePath<java.sql.Timestamp> create_time = createDateTime("create_time", java.sql.Timestamp.class);

    public final StringPath dim_code = createString("dim_code");

    public final StringPath dim_name = createString("dim_name");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final StringPath master_code = createString("master_code");

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QSettlementCombinatorialDimensionFrom(String variable) {
        super(SettlementCombinatorialDimensionFrom.class, forVariable(variable));
    }

    public QSettlementCombinatorialDimensionFrom(Path<? extends SettlementCombinatorialDimensionFrom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettlementCombinatorialDimensionFrom(PathMetadata metadata) {
        super(SettlementCombinatorialDimensionFrom.class, metadata);
    }

}

