package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.OrderCp;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QOrderCp is a Querydsl query type for OrderCp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderCp extends EntityPathBase<OrderCp> {

    private static final long serialVersionUID = -1527433863L;

    public static final QOrderCp orderCp = new QOrderCp("orderCp");

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

    public final NumberPath<Integer> settleaccounts = createNumber("settleaccounts", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QOrderCp(String variable) {
        super(OrderCp.class, forVariable(variable));
    }

    public QOrderCp(Path<? extends OrderCp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderCp(PathMetadata metadata) {
        super(OrderCp.class, metadata);
    }

}

