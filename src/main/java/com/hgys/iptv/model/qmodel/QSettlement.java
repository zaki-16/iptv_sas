package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.Settlement;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSettlement is a Querydsl query type for Settlement
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettlement extends EntityPathBase<Settlement> {

    private static final long serialVersionUID = 739789643L;

    public static final QSettlement settlement = new QSettlement("settlement");

    public final StringPath code = createString("code");

    public final StringPath col1 = createString("col1");

    public final StringPath col2 = createString("col2");

    public final StringPath grossIncome = createString("grossIncome");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> inputTime = createDateTime("inputTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final DateTimePath<java.sql.Timestamp> modifyTime = createDateTime("modifyTime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final StringPath remakes = createString("remakes");

    public final DateTimePath<java.sql.Timestamp> settleEndTime = createDateTime("settleEndTime", java.sql.Timestamp.class);

    public final StringPath settleMode = createString("settleMode");

    public final StringPath settleModeCode = createString("settleModeCode");

    public final DateTimePath<java.sql.Timestamp> settleStartTime = createDateTime("settleStartTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> settleType = createNumber("settleType", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QSettlement(String variable) {
        super(Settlement.class, forVariable(variable));
    }

    public QSettlement(Path<? extends Settlement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettlement(PathMetadata metadata) {
        super(Settlement.class, metadata);
    }

}

