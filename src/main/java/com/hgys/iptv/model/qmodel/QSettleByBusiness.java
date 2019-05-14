package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.SettleByBusiness;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSettleByBusiness is a Querydsl query type for SettleByBusiness
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettleByBusiness extends EntityPathBase<SettleByBusiness> {

    private static final long serialVersionUID = 1582634372L;

    public static final QSettleByBusiness settleByBusiness = new QSettleByBusiness("settleByBusiness");

    public final StringPath col1 = createString("col1");

    public final StringPath col2 = createString("col2");

    public final StringPath cpCode = createString("cpCode");

    public final StringPath cpName = createString("cpName");

    public final NumberPath<Integer> grossIncome = createNumber("grossIncome", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QSettleByBusiness(String variable) {
        super(SettleByBusiness.class, forVariable(variable));
    }

    public QSettleByBusiness(Path<? extends SettleByBusiness> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettleByBusiness(PathMetadata metadata) {
        super(SettleByBusiness.class, metadata);
    }

}

