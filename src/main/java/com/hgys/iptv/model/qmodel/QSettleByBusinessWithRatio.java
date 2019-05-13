package com.hgys.iptv.model.qmodel;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSettleByBusinessWithRatio is a Querydsl query type for SettleByBusinessWithRatio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettleByBusinessWithRatio extends EntityPathBase<SettleByBusinessWithRatio> {

    private static final long serialVersionUID = -1702217279L;

    public static final QSettleByBusinessWithRatio settleByBusinessWithRatio = new QSettleByBusinessWithRatio("settleByBusinessWithRatio");

    public final StringPath businessCode = createString("businessCode");

    public final NumberPath<Integer> businessIncome = createNumber("businessIncome", Integer.class);

    public final StringPath businessName = createString("businessName");

    public final StringPath col1 = createString("col1");

    public final StringPath col2 = createString("col2");

    public final StringPath cpCode = createString("cpCode");

    public final StringPath cpName = createString("cpName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> settleType = createNumber("settleType", Integer.class);

    public QSettleByBusinessWithRatio(String variable) {
        super(SettleByBusinessWithRatio.class, forVariable(variable));
    }

    public QSettleByBusinessWithRatio(Path<? extends SettleByBusinessWithRatio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettleByBusinessWithRatio(PathMetadata metadata) {
        super(SettleByBusinessWithRatio.class, metadata);
    }

}

