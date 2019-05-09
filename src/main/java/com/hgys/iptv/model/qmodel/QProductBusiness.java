package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.ProductBusiness;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QProductBusiness is a Querydsl query type for ProductBusiness
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductBusiness extends EntityPathBase<ProductBusiness> {

    private static final long serialVersionUID = 159438061L;

    public static final QProductBusiness productBusiness = new QProductBusiness("productBusiness");

    public final NumberPath<Integer> bid = createNumber("bid", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> pid = createNumber("pid", Integer.class);

    public QProductBusiness(String variable) {
        super(ProductBusiness.class, forVariable(variable));
    }

    public QProductBusiness(Path<? extends ProductBusiness> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductBusiness(PathMetadata metadata) {
        super(ProductBusiness.class, metadata);
    }

}

