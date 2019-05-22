package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCpSettlementMoney is a Querydsl query type for CpSettlementMoney
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCpSettlementMoney extends EntityPathBase<CpSettlementMoney> {

    private static final long serialVersionUID = -220491640L;

    public static final QCpSettlementMoney cpSettlementMoney = new QCpSettlementMoney("cpSettlementMoney");

    public final StringPath businessCode = createString("businessCode");

    public final StringPath businessName = createString("businessName");

    public final StringPath cpcode = createString("cpcode");

    public final StringPath cpname = createString("cpname");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath masterCode = createString("masterCode");

    public final StringPath masterName = createString("masterName");

    public final StringPath productCode = createString("productCode");

    public final StringPath productName = createString("productName");

    public final NumberPath<java.math.BigDecimal> settlementMoney = createNumber("settlementMoney", java.math.BigDecimal.class);

    public QCpSettlementMoney(String variable) {
        super(CpSettlementMoney.class, forVariable(variable));
    }

    public QCpSettlementMoney(Path<? extends CpSettlementMoney> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCpSettlementMoney(PathMetadata metadata) {
        super(CpSettlementMoney.class, metadata);
    }

}

