package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCpBusiness is a Querydsl query type for CpBusiness
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCpBusiness extends EntityPathBase<CpBusiness> {

    private static final long serialVersionUID = 1032336751L;

    public static final QCpBusiness cpBusiness = new QCpBusiness("cpBusiness");

    public final NumberPath<Integer> bid = createNumber("bid", Integer.class);

    public final NumberPath<Integer> cpid = createNumber("cpid", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QCpBusiness(String variable) {
        super(CpBusiness.class, forVariable(variable));
    }

    public QCpBusiness(Path<? extends CpBusiness> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCpBusiness(PathMetadata metadata) {
        super(CpBusiness.class, metadata);
    }

}

