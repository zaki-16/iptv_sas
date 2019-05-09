package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.OrderBusinessComparison;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QOrderBusinessComparison is a Querydsl query type for OrderBusinessComparison
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderBusinessComparison extends EntityPathBase<OrderBusinessComparison> {

    private static final long serialVersionUID = 662595829L;

    public static final QOrderBusinessComparison orderBusinessComparison = new QOrderBusinessComparison("orderBusinessComparison");

    public final StringPath businessCode = createString("businessCode");

    public final StringPath businessName = createString("businessName");

    public final StringPath code = createString("code");

    public final StringPath col1 = createString("col1");

    public final StringPath col2 = createString("col2");

    public final StringPath col3 = createString("col3");

    public final StringPath col4 = createString("col4");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> inputTime = createDateTime("inputTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final NumberPath<Integer> mode = createNumber("mode", Integer.class);

    public final DateTimePath<java.sql.Timestamp> modifyTime = createDateTime("modifyTime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final StringPath remakes = createString("remakes");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QOrderBusinessComparison(String variable) {
        super(OrderBusinessComparison.class, forVariable(variable));
    }

    public QOrderBusinessComparison(Path<? extends OrderBusinessComparison> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderBusinessComparison(PathMetadata metadata) {
        super(OrderBusinessComparison.class, metadata);
    }

}

