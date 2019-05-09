package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.Permission;
import com.hgys.iptv.model.Role;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QPermission is a Querydsl query type for Permission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPermission extends EntityPathBase<Permission> {

    private static final long serialVersionUID = 148342769L;

    public static final QPermission permission1 = new QPermission("permission1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath permission = createString("permission");

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath uri = createString("uri");

    public QPermission(String variable) {
        super(Permission.class, forVariable(variable));
    }

    public QPermission(Path<? extends Permission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPermission(PathMetadata metadata) {
        super(Permission.class, metadata);
    }

}

