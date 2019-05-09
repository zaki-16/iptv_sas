package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.CpAccountSettlement;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCpAccountSettlement is a Querydsl query type for CpAccountSettlement
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCpAccountSettlement extends EntityPathBase<CpAccountSettlement> {

    private static final long serialVersionUID = 977230439L;

    public static final QCpAccountSettlement cpAccountSettlement = new QCpAccountSettlement("cpAccountSettlement");

    public final StringPath col1 = createString("col1");

    public final StringPath col2 = createString("col2");

    public final StringPath cpCode = createString("cpCode");

    public final StringPath cpName = createString("cpName");

    public final DateTimePath<java.sql.Timestamp> create_time = createDateTime("create_time", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath masterCode = createString("masterCode");

    public final StringPath masterName = createString("masterName");

    public final StringPath productCode = createString("productCode");

    public final StringPath productName = createString("productName");

    public final NumberPath<Integer> set_money = createNumber("set_money", Integer.class);

    public QCpAccountSettlement(String variable) {
        super(CpAccountSettlement.class, forVariable(variable));
    }

    public QCpAccountSettlement(Path<? extends CpAccountSettlement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCpAccountSettlement(PathMetadata metadata) {
        super(CpAccountSettlement.class, metadata);
    }

}

