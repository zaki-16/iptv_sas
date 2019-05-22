package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSettlementMoney is a Querydsl query type for SettlementMoney
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettlementMoney extends EntityPathBase<SettlementMoney> {

    private static final long serialVersionUID = 583744565L;

    public static final QSettlementMoney settlementMoney = new QSettlementMoney("settlementMoney");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath masterCode = createString("masterCode");

    public final NumberPath<java.math.BigDecimal> money = createNumber("money", java.math.BigDecimal.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QSettlementMoney(String variable) {
        super(SettlementMoney.class, forVariable(variable));
    }

    public QSettlementMoney(Path<? extends SettlementMoney> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettlementMoney(PathMetadata metadata) {
        super(SettlementMoney.class, metadata);
    }

}

