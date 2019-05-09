package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.Cp;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCp is a Querydsl query type for Cp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCp extends EntityPathBase<Cp> {

    private static final long serialVersionUID = -1277226865L;

    public static final QCp cp = new QCp("cp");

    public final DateTimePath<java.sql.Timestamp> cancelTime = createDateTime("cancelTime", java.sql.Timestamp.class);

    public final StringPath code = createString("code");

    public final StringPath contactMail = createString("contactMail");

    public final StringPath contactNm = createString("contactNm");

    public final StringPath contactTel = createString("contactTel");

    public final StringPath cpAbbr = createString("cpAbbr");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isdelete = createNumber("isdelete", Integer.class);

    public final DateTimePath<java.sql.Timestamp> modifyTime = createDateTime("modifyTime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final DateTimePath<java.sql.Timestamp> regisTime = createDateTime("regisTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QCp(String variable) {
        super(Cp.class, forVariable(variable));
    }

    public QCp(Path<? extends Cp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCp(PathMetadata metadata) {
        super(Cp.class, metadata);
    }

}

