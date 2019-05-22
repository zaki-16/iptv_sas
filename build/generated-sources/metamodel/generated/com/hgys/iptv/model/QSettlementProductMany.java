package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSettlementProductMany is a Querydsl query type for SettlementProductMany
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettlementProductMany extends EntityPathBase<SettlementProductMany> {

    private static final long serialVersionUID = 736372643L;

    public static final QSettlementProductMany settlementProductMany = new QSettlementProductMany("settlementProductMany");

    public final StringPath cpcode = createString("cpcode");

    public final StringPath cpname = createString("cpname");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final StringPath dimACode = createString("dimACode");

    public final StringPath dimAName = createString("dimAName");

    public final StringPath dimBCode = createString("dimBCode");

    public final StringPath dimBName = createString("dimBName");

    public final StringPath dimCCode = createString("dimCCode");

    public final StringPath dimCName = createString("dimCName");

    public final StringPath dimDCode = createString("dimDCode");

    public final StringPath dimDName = createString("dimDName");

    public final StringPath dimECode = createString("dimECode");

    public final StringPath dimEName = createString("dimEName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath masterCode = createString("masterCode");

    public final StringPath productCode = createString("productCode");

    public final StringPath productName = createString("productName");

    public final NumberPath<java.math.BigDecimal> setMoney = createNumber("setMoney", java.math.BigDecimal.class);

    public QSettlementProductMany(String variable) {
        super(SettlementProductMany.class, forVariable(variable));
    }

    public QSettlementProductMany(Path<? extends SettlementProductMany> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettlementProductMany(PathMetadata metadata) {
        super(SettlementProductMany.class, metadata);
    }

}

