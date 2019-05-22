package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCpOrderQuantity is a Querydsl query type for CpOrderQuantity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCpOrderQuantity extends EntityPathBase<CpOrderQuantity> {

    private static final long serialVersionUID = 630514090L;

    public static final QCpOrderQuantity cpOrderQuantity = new QCpOrderQuantity("cpOrderQuantity");

    public final StringPath col1 = createString("col1");

    public final NumberPath<Integer> col2 = createNumber("col2", Integer.class);

    public final StringPath col3 = createString("col3");

    public final StringPath cpname = createString("cpname");

    public final DateTimePath<java.sql.Timestamp> createtime = createDateTime("createtime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final StringPath oqname = createString("oqname");

    public QCpOrderQuantity(String variable) {
        super(CpOrderQuantity.class, forVariable(variable));
    }

    public QCpOrderQuantity(Path<? extends CpOrderQuantity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCpOrderQuantity(PathMetadata metadata) {
        super(CpOrderQuantity.class, metadata);
    }

}

