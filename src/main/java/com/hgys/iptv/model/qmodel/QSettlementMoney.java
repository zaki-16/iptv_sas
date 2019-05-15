package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.SettlementMoney;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


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

