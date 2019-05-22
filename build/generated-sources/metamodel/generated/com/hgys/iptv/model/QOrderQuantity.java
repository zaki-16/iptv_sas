package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderQuantity is a Querydsl query type for OrderQuantity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderQuantity extends EntityPathBase<OrderQuantity> {

    private static final long serialVersionUID = 1726321815L;

    public static final QOrderQuantity orderQuantity = new QOrderQuantity("orderQuantity");

    public final StringPath code = createString("code");

    public final StringPath col1 = createString("col1");

    public final NumberPath<Integer> col2 = createNumber("col2", Integer.class);

    public final StringPath col3 = createString("col3");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> inputTime = createDateTime("inputTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final DateTimePath<java.sql.Timestamp> modifyTime = createDateTime("modifyTime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QOrderQuantity(String variable) {
        super(OrderQuantity.class, forVariable(variable));
    }

    public QOrderQuantity(Path<? extends OrderQuantity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderQuantity(PathMetadata metadata) {
        super(OrderQuantity.class, metadata);
    }

}

