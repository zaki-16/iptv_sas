package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.SysMenu;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSysMenu is a Querydsl query type for SysMenu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysMenu extends EntityPathBase<SysMenu> {

    private static final long serialVersionUID = -2058855702L;

    public static final QSysMenu sysMenu = new QSysMenu("sysMenu");

    public final StringPath code = createString("code");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final StringPath icon = createString("icon");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath navigateUrl = createString("navigateUrl");

    public final NumberPath<Integer> parentId = createNumber("parentId", Integer.class);

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QSysMenu(String variable) {
        super(SysMenu.class, forVariable(variable));
    }

    public QSysMenu(Path<? extends SysMenu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysMenu(PathMetadata metadata) {
        super(SysMenu.class, metadata);
    }

}

