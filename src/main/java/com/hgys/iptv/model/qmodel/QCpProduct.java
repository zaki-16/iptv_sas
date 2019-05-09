package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.CpProduct;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCpProduct is a Querydsl query type for CpProduct
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCpProduct extends EntityPathBase<CpProduct> {

    private static final long serialVersionUID = 1839031648L;

    public static final QCpProduct cpProduct = new QCpProduct("cpProduct");

    public final NumberPath<Integer> cpid = createNumber("cpid", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> pid = createNumber("pid", Integer.class);

    public QCpProduct(String variable) {
        super(CpProduct.class, forVariable(variable));
    }

    public QCpProduct(Path<? extends CpProduct> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCpProduct(PathMetadata metadata) {
        super(CpProduct.class, metadata);
    }

}

