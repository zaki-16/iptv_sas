package com.hgys.iptv.model.qmodel;

import com.hgys.iptv.model.CpOrderBusinessComparison;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCpOrderBusinessComparison is a Querydsl query type for CpOrderBusinessComparison
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCpOrderBusinessComparison extends EntityPathBase<CpOrderBusinessComparison> {

    private static final long serialVersionUID = 1848293960L;

    public static final QCpOrderBusinessComparison cpOrderBusinessComparison = new QCpOrderBusinessComparison("cpOrderBusinessComparison");

    public final StringPath cp_code = createString("cp_code");

    public final StringPath cp_name = createString("cp_name");

    public final DateTimePath<java.sql.Timestamp> create_time = createDateTime("create_time", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath masterCode = createString("masterCode");

    public final NumberPath<java.math.BigDecimal> money = createNumber("money", java.math.BigDecimal.class);

    public final NumberPath<Integer> proportion = createNumber("proportion", Integer.class);

    public QCpOrderBusinessComparison(String variable) {
        super(CpOrderBusinessComparison.class, forVariable(variable));
    }

    public QCpOrderBusinessComparison(Path<? extends CpOrderBusinessComparison> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCpOrderBusinessComparison(PathMetadata metadata) {
        super(CpOrderBusinessComparison.class, metadata);
    }

}

