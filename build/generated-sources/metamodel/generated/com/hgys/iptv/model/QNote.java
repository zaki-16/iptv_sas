package com.hgys.iptv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNote is a Querydsl query type for Note
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNote extends EntityPathBase<Note> {

    private static final long serialVersionUID = 945959828L;

    public static final QNote note = new QNote("note");

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> create_time = createDateTime("create_time", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> note_type = createNumber("note_type", Integer.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QNote(String variable) {
        super(Note.class, forVariable(variable));
    }

    public QNote(Path<? extends Note> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNote(PathMetadata metadata) {
        super(Note.class, metadata);
    }

}

