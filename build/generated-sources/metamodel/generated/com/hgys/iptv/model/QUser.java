package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 946171757L;

    public static final QUser user = new QUser("user");

    public final StringPath cpAbbr = createString("cpAbbr");

    public final NumberPath<Integer> cpId = createNumber("cpId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createdTime = createDateTime("createdTime", java.sql.Timestamp.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final StringPath mobilePhone = createString("mobilePhone");

    public final DateTimePath<java.sql.Timestamp> modifyTime = createDateTime("modifyTime", java.sql.Timestamp.class);

    public final StringPath password = createString("password");

    public final StringPath realName = createString("realName");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath telephone = createString("telephone");

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

