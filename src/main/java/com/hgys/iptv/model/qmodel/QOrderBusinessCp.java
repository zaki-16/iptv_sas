package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.OrderBusinessCp;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QOrderBusinessCp is a Querydsl query type for OrderBusinessCp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderBusinessCp extends EntityPathBase<OrderBusinessCp> {

    private static final long serialVersionUID = 778384729L;

    public static final QOrderBusinessCp orderBusinessCp = new QOrderBusinessCp("orderBusinessCp");

    public final StringPath bucode = createString("bucode");

    public final StringPath buname = createString("buname");

    public final StringPath col1 = createString("col1");

    public final NumberPath<Integer> col2 = createNumber("col2", Integer.class);

    public final StringPath col3 = createString("col3");

    public final StringPath cpcode = createString("cpcode");

    public final StringPath cpname = createString("cpname");

    public final DateTimePath<java.sql.Timestamp> createtime = createDateTime("createtime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final StringPath note = createString("note");

    public final StringPath obcode = createString("obcode");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath weight = createString("weight");

    public QOrderBusinessCp(String variable) {
        super(OrderBusinessCp.class, forVariable(variable));
    }

    public QOrderBusinessCp(Path<? extends OrderBusinessCp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderBusinessCp(PathMetadata metadata) {
        super(OrderBusinessCp.class, metadata);
    }

}

