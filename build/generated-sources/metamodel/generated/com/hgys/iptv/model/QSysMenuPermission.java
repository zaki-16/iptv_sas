package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysMenuPermission is a Querydsl query type for SysMenuPermission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysMenuPermission extends EntityPathBase<SysMenuPermission> {

    private static final long serialVersionUID = 1663230041L;

    public static final QSysMenuPermission sysMenuPermission = new QSysMenuPermission("sysMenuPermission");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> menuId = createNumber("menuId", Integer.class);

    public final NumberPath<Integer> permId = createNumber("permId", Integer.class);

    public QSysMenuPermission(String variable) {
        super(SysMenuPermission.class, forVariable(variable));
    }

    public QSysMenuPermission(Path<? extends SysMenuPermission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysMenuPermission(PathMetadata metadata) {
        super(SysMenuPermission.class, metadata);
    }

}

