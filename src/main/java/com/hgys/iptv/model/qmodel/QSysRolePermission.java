package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.SysRolePermission;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSysRolePermission is a Querydsl query type for SysRolePermission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysRolePermission extends EntityPathBase<SysRolePermission> {

    private static final long serialVersionUID = -1075340624L;

    public static final QSysRolePermission sysRolePermission = new QSysRolePermission("sysRolePermission");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> permissionId = createNumber("permissionId", Integer.class);

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public QSysRolePermission(String variable) {
        super(SysRolePermission.class, forVariable(variable));
    }

    public QSysRolePermission(Path<? extends SysRolePermission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysRolePermission(PathMetadata metadata) {
        super(SysRolePermission.class, metadata);
    }

}

