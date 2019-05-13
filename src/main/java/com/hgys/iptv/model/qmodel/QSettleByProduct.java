package com.hgys.iptv.model.qmodel;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSettleByProduct is a Querydsl query type for SettleByProduct
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettleByProduct extends EntityPathBase<SettleByProduct> {

    private static final long serialVersionUID = 609857195L;

    public static final QSettleByProduct settleByProduct = new QSettleByProduct("settleByProduct");

    public final StringPath col1 = createString("col1");

    public final StringPath col2 = createString("col2");

    public final StringPath cpCode = createString("cpCode");

    public final StringPath cpName = createString("cpName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath prodCode = createString("prodCode");

    public final NumberPath<Integer> prodIncome = createNumber("prodIncome", Integer.class);

    public final StringPath prodName = createString("prodName");

    public QSettleByProduct(String variable) {
        super(SettleByProduct.class, forVariable(variable));
    }

    public QSettleByProduct(Path<? extends SettleByProduct> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettleByProduct(PathMetadata metadata) {
        super(SettleByProduct.class, metadata);
    }

}

