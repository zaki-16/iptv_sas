package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderBusinessWithCp is a Querydsl query type for OrderBusinessWithCp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderBusinessWithCp extends EntityPathBase<OrderBusinessWithCp> {

    private static final long serialVersionUID = -2053279041L;

    public static final QOrderBusinessWithCp orderBusinessWithCp = new QOrderBusinessWithCp("orderBusinessWithCp");

    public final StringPath bucode = createString("bucode");

    public final StringPath buname = createString("buname");

    public final StringPath code = createString("code");

    public final StringPath col1 = createString("col1");

    public final NumberPath<Integer> col2 = createNumber("col2", Integer.class);

    public final StringPath col3 = createString("col3");

    public final DateTimePath<java.sql.Timestamp> createtime = createDateTime("createtime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final StringPath note = createString("note");

    public final StringPath obcode = createString("obcode");

    public final StringPath obname = createString("obname");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath weight = createString("weight");

    public QOrderBusinessWithCp(String variable) {
        super(OrderBusinessWithCp.class, forVariable(variable));
    }

    public QOrderBusinessWithCp(Path<? extends OrderBusinessWithCp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderBusinessWithCp(PathMetadata metadata) {
        super(OrderBusinessWithCp.class, metadata);
    }

}

