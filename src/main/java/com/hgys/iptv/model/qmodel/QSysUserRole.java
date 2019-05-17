package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.SysUserRole;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSysUserRole is a Querydsl query type for SysUserRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysUserRole extends EntityPathBase<SysUserRole> {

    private static final long serialVersionUID = -1230075156L;

    public static final QSysUserRole sysUserRole = new QSysUserRole("sysUserRole");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QSysUserRole(String variable) {
        super(SysUserRole.class, forVariable(variable));
    }

    public QSysUserRole(Path<? extends SysUserRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysUserRole(PathMetadata metadata) {
        super(SysUserRole.class, metadata);
    }

}

