package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.OrderQuantityWithCp;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QOrderQuantityWithCp is a Querydsl query type for OrderQuantityWithCp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderQuantityWithCp extends EntityPathBase<OrderQuantityWithCp> {

    private static final long serialVersionUID = -811134134L;

    public static final QOrderQuantityWithCp orderQuantityWithCp = new QOrderQuantityWithCp("orderQuantityWithCp");

    public final StringPath code = createString("code");

    public final StringPath col3 = createString("col3");

    public final StringPath cpcode = createString("cpcode");

    public final StringPath cpname = createString("cpname");

    public final DateTimePath<java.sql.Timestamp> createtime = createDateTime("createtime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final StringPath note = createString("note");

    public final StringPath oqcode = createString("oqcode");

    public final StringPath oqname = createString("oqname");

    public QOrderQuantityWithCp(String variable) {
        super(OrderQuantityWithCp.class, forVariable(variable));
    }

    public QOrderQuantityWithCp(Path<? extends OrderQuantityWithCp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderQuantityWithCp(PathMetadata metadata) {
        super(OrderQuantityWithCp.class, metadata);
    }

}

