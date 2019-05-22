package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysMenu is a Querydsl query type for SysMenu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysMenu extends EntityPathBase<SysMenu> {

    private static final long serialVersionUID = -2058855702L;

    public static final QSysMenu sysMenu = new QSysMenu("sysMenu");

    public final StringPath icon = createString("icon");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath navigateUrl = createString("navigateUrl");

    public final NumberPath<Integer> parentId = createNumber("parentId", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath text = createString("text");

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

