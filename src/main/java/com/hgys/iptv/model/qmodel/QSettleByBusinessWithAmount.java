package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.SettleByBusinessWithAmount;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSettleByBusinessWithAmount is a Querydsl query type for SettleByBusinessWithAmount
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettleByBusinessWithAmount extends EntityPathBase<SettleByBusinessWithAmount> {

    private static final long serialVersionUID = -1704878750L;

    public static final QSettleByBusinessWithAmount settleByBusinessWithAmount = new QSettleByBusinessWithAmount("settleByBusinessWithAmount");

    public final StringPath businessCode = createString("businessCode");

    public final NumberPath<Integer> businessIncome = createNumber("businessIncome", Integer.class);

    public final StringPath businessName = createString("businessName");

    public final StringPath cpCode = createString("cpCode");

    public final StringPath cpName = createString("cpName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> settleType = createNumber("settleType", Integer.class);

    public QSettleByBusinessWithAmount(String variable) {
        super(SettleByBusinessWithAmount.class, forVariable(variable));
    }

    public QSettleByBusinessWithAmount(Path<? extends SettleByBusinessWithAmount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettleByBusinessWithAmount(PathMetadata metadata) {
        super(SettleByBusinessWithAmount.class, metadata);
    }

}

