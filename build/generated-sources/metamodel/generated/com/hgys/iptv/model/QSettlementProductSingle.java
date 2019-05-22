package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSettlementProductSingle is a Querydsl query type for SettlementProductSingle
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettlementProductSingle extends EntityPathBase<SettlementProductSingle> {

    private static final long serialVersionUID = -836344692L;

    public static final QSettlementProductSingle settlementProductSingle = new QSettlementProductSingle("settlementProductSingle");

    public final StringPath cpcode = createString("cpcode");

    public final StringPath cpname = createString("cpname");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final StringPath dimCode = createString("dimCode");

    public final StringPath dimName = createString("dimName");

    public final NumberPath<Integer> dimWeith = createNumber("dimWeith", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath masterCode = createString("masterCode");

    public final StringPath productCode = createString("productCode");

    public final StringPath productName = createString("productName");

    public final NumberPath<java.math.BigDecimal> setMoney = createNumber("setMoney", java.math.BigDecimal.class);

    public QSettlementProductSingle(String variable) {
        super(SettlementProductSingle.class, forVariable(variable));
    }

    public QSettlementProductSingle(Path<? extends SettlementProductSingle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettlementProductSingle(PathMetadata metadata) {
        super(SettlementProductSingle.class, metadata);
    }

}

