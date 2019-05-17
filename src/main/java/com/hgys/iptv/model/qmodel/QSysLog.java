package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.SysLog;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSysLog is a Querydsl query type for SysLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysLog extends EntityPathBase<SysLog> {

    private static final long serialVersionUID = -1313341351L;

    public static final QSysLog sysLog = new QSysLog("sysLog");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath loginName = createString("loginName");

    public final StringPath realName = createString("realName");

    public final StringPath result = createString("result");

    public final DateTimePath<java.sql.Timestamp> time = createDateTime("time", java.sql.Timestamp.class);

    public final StringPath type = createString("type");

    public QSysLog(String variable) {
        super(SysLog.class, forVariable(variable));
    }

    public QSysLog(Path<? extends SysLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysLog(PathMetadata metadata) {
        super(SysLog.class, metadata);
    }

}

