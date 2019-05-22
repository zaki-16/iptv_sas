package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSettlementCombinatorialDimensionMaster is a Querydsl query type for SettlementCombinatorialDimensionMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettlementCombinatorialDimensionMaster extends EntityPathBase<SettlementCombinatorialDimensionMaster> {

    private static final long serialVersionUID = -1728102757L;

    public static final QSettlementCombinatorialDimensionMaster settlementCombinatorialDimensionMaster = new QSettlementCombinatorialDimensionMaster("settlementCombinatorialDimensionMaster");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> inputTime = createDateTime("inputTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final DateTimePath<java.sql.Timestamp> modifyTime = createDateTime("modifyTime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final StringPath remakes = createString("remakes");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QSettlementCombinatorialDimensionMaster(String variable) {
        super(SettlementCombinatorialDimensionMaster.class, forVariable(variable));
    }

    public QSettlementCombinatorialDimensionMaster(Path<? extends SettlementCombinatorialDimensionMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettlementCombinatorialDimensionMaster(PathMetadata metadata) {
        super(SettlementCombinatorialDimensionMaster.class, metadata);
    }

}

