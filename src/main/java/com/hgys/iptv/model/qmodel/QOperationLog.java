package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.OperationLog;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QOperationLog is a Querydsl query type for OperationLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOperationLog extends EntityPathBase<OperationLog> {

    private static final long serialVersionUID = -235746817L;

    public static final QOperationLog operationLog = new QOperationLog("operationLog");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath loginName = createString("loginName");

    public final StringPath operObj = createString("operObj");

    public final StringPath operResult = createString("operResult");

    public final DateTimePath<java.sql.Timestamp> operTime = createDateTime("operTime", java.sql.Timestamp.class);

    public final StringPath operType = createString("operType");

    public final StringPath realName = createString("realName");

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

