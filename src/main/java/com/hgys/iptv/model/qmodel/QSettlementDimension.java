package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.SettlementDimension;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSettlementDimension is a Querydsl query type for SettlementDimension
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettlementDimension extends EntityPathBase<SettlementDimension> {

    private static final long serialVersionUID = 985515611L;

    public static final QSettlementDimension settlementDimension = new QSettlementDimension("settlementDimension");

    public final StringPath code = createString("code");

    public final StringPath col1 = createString("col1");

    public final StringPath col2 = createString("col2");

    public final StringPath col3 = createString("col3");

    public final StringPath col4 = createString("col4");

    public final StringPath col5 = createString("col5");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> inputTime = createDateTime("inputTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final DateTimePath<java.sql.Timestamp> modifyTime = createDateTime("modifyTime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final StringPath remarks = createString("remarks");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QSettlementDimension(String variable) {
        super(SettlementDimension.class, forVariable(variable));
    }

    public QSettlementDimension(Path<? extends SettlementDimension> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettlementDimension(PathMetadata metadata) {
        super(SettlementDimension.class, metadata);
    }

}

