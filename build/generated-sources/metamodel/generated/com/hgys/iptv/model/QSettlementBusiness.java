package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSettlementBusiness is a Querydsl query type for SettlementBusiness
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettlementBusiness extends EntityPathBase<SettlementBusiness> {

    private static final long serialVersionUID = -802622421L;

    public static final QSettlementBusiness settlementBusiness = new QSettlementBusiness("settlementBusiness");

    public final StringPath businessCode = createString("businessCode");

    public final NumberPath<java.math.BigDecimal> businessMoney = createNumber("businessMoney", java.math.BigDecimal.class);

    public final StringPath businessName = createString("businessName");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath masterCode = createString("masterCode");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QSettlementBusiness(String variable) {
        super(SettlementBusiness.class, forVariable(variable));
    }

    public QSettlementBusiness(Path<? extends SettlementBusiness> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettlementBusiness(PathMetadata metadata) {
        super(SettlementBusiness.class, metadata);
    }

}

