package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.OrderProductWithSCD;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QOrderProductWithSCD is a Querydsl query type for OrderProductWithSCD
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderProductWithSCD extends EntityPathBase<OrderProductWithSCD> {

    private static final long serialVersionUID = 1349499019L;

    public static final QOrderProductWithSCD orderProductWithSCD = new QOrderProductWithSCD("orderProductWithSCD");

    public final DateTimePath<java.sql.Timestamp> createtime = createDateTime("createtime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath masterCode = createString("masterCode");

    public final StringPath opcode = createString("opcode");

    public final StringPath opname = createString("opname");

    public final StringPath pcode = createString("pcode");

    public final StringPath pname = createString("pname");

    public final StringPath scdcode = createString("scdcode");

    public final StringPath scdname = createString("scdname");

    public final StringPath sdcode = createString("sdcode");

    public final StringPath sdname = createString("sdname");

    public QOrderProductWithSCD(String variable) {
        super(OrderProductWithSCD.class, forVariable(variable));
    }

    public QOrderProductWithSCD(Path<? extends OrderProductWithSCD> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderProductWithSCD(PathMetadata metadata) {
        super(OrderProductWithSCD.class, metadata);
    }

}

