package com.hgys.iptv.model.qmodel;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSettleByQuantity is a Querydsl query type for SettleByQuantity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettleByQuantity extends EntityPathBase<SettleByQuantity> {

    private static final long serialVersionUID = 1444461135L;

    public static final QSettleByQuantity settleByQuantity = new QSettleByQuantity("settleByQuantity");

    public final StringPath col1 = createString("col1");

    public final StringPath col2 = createString("col2");

    public final StringPath cpCode = createString("cpCode");

    public final StringPath cpName = createString("cpName");

    public final NumberPath<Integer> cpQuantity = createNumber("cpQuantity", Integer.class);

    public final NumberPath<Integer> grossIncome = createNumber("grossIncome", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QSettleByQuantity(String variable) {
        super(SettleByQuantity.class, forVariable(variable));
    }

    public QSettleByQuantity(Path<? extends SettleByQuantity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettleByQuantity(PathMetadata metadata) {
        super(SettleByQuantity.class, metadata);
    }

}

