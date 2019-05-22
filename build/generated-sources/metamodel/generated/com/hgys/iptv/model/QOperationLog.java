package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOperationLog is a Querydsl query type for OperationLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOperationLog extends EntityPathBase<OperationLog> {

    private static final long serialVersionUID = -235746817L;

    public static final QOperationLog operationLog = new QOperationLog("operationLog");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath loginName = createString("loginName");

    public final StringPath methodName = createString("methodName");

    public final StringPath operObj = createString("operObj");

    public final DateTimePath<java.sql.Timestamp> operTime = createDateTime("operTime", java.sql.Timestamp.class);

    public final StringPath operType = createString("operType");

    public final StringPath realName = createString("realName");

    public final StringPath Result = createString("Result");

    public QOperationLog(String variable) {
        super(OperationLog.class, forVariable(variable));
    }

    public QOperationLog(Path<? extends OperationLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOperationLog(PathMetadata metadata) {
        super(OperationLog.class, metadata);
    }

}

