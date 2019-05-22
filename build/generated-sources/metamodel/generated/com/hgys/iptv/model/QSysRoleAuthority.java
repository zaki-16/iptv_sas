package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysRoleAuthority is a Querydsl query type for SysRoleAuthority
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysRoleAuthority extends EntityPathBase<SysRoleAuthority> {

    private static final long serialVersionUID = 1596166722L;

    public static final QSysRoleAuthority sysRoleAuthority = new QSysRoleAuthority("sysRoleAuthority");

    public final NumberPath<Integer> authId = createNumber("authId", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public QSysRoleAuthority(String variable) {
        super(SysRoleAuthority.class, forVariable(variable));
    }

    public QSysRoleAuthority(Path<? extends SysRoleAuthority> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysRoleAuthority(PathMetadata metadata) {
        super(SysRoleAuthority.class, metadata);
    }

}

