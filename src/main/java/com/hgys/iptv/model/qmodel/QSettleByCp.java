package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.SettleByCp;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSettleByCp is a Querydsl query type for SettleByCp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettleByCp extends EntityPathBase<SettleByCp> {

    private static final long serialVersionUID = 738526513L;

    public static final QSettleByCp settleByCp = new QSettleByCp("settleByCp");

    public final StringPath col1 = createString("col1");

    public final StringPath col2 = createString("col2");

    public final StringPath cpCode = createString("cpCode");

    public final StringPath cpName = createString("cpName");

    public final NumberPath<Integer> grossIncome = createNumber("grossIncome", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> settleType = createNumber("settleType", Integer.class);

    public QSettleByCp(String variable) {
        super(SettleByCp.class, forVariable(variable));
    }

    public QSettleByCp(Path<? extends SettleByCp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettleByCp(PathMetadata metadata) {
        super(SettleByCp.class, metadata);
    }

}

