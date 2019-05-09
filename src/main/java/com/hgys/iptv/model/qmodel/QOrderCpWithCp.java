package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.OrderCpWithCp;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QOrderCpWithCp is a Querydsl query type for OrderCpWithCp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderCpWithCp extends EntityPathBase<OrderCpWithCp> {

    private static final long serialVersionUID = -1631649108L;

    public static final QOrderCpWithCp orderCpWithCp = new QOrderCpWithCp("orderCpWithCp");

    public final StringPath code = createString("code");

    public final StringPath col3 = createString("col3");

    public final StringPath cpcode = createString("cpcode");

    public final StringPath cpname = createString("cpname");

    public final DateTimePath<java.sql.Timestamp> createtime = createDateTime("createtime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final NumberPath<Integer> money = createNumber("money", Integer.class);

    public final StringPath note = createString("note");

    public final StringPath occode = createString("occode");

    public final StringPath ocname = createString("ocname");

    public final NumberPath<Integer> settleaccounts = createNumber("settleaccounts", Integer.class);

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QOrderCpWithCp(String variable) {
        super(OrderCpWithCp.class, forVariable(variable));
    }

    public QOrderCpWithCp(Path<? extends OrderCpWithCp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderCpWithCp(PathMetadata metadata) {
        super(OrderCpWithCp.class, metadata);
    }

}

