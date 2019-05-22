package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSettlementOrder is a Querydsl query type for SettlementOrder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettlementOrder extends EntityPathBase<SettlementOrder> {

    private static final long serialVersionUID = 585671363L;

    public static final QSettlementOrder settlementOrder = new QSettlementOrder("settlementOrder");

    public final StringPath cpcode = createString("cpcode");

    public final StringPath cpname = createString("cpname");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath masterCode = createString("masterCode");

    public final NumberPath<java.math.BigDecimal> orderMoney = createNumber("orderMoney", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> orderQuantity = createNumber("orderQuantity", java.math.BigDecimal.class);

    public QSettlementOrder(String variable) {
        super(SettlementOrder.class, forVariable(variable));
    }

    public QSettlementOrder(Path<? extends SettlementOrder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettlementOrder(PathMetadata metadata) {
        super(SettlementOrder.class, metadata);
    }

}

