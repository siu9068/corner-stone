package kr.cornerstone.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseDateTime is a Querydsl query type for BaseDateTime
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseDateTime extends EntityPathBase<BaseDateTime> {

    private static final long serialVersionUID = -192714350L;

    public static final QBaseDateTime baseDateTime = new QBaseDateTime("baseDateTime");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updDate = createDateTime("updDate", java.time.LocalDateTime.class);

    public QBaseDateTime(String variable) {
        super(BaseDateTime.class, forVariable(variable));
    }

    public QBaseDateTime(Path<? extends BaseDateTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseDateTime(PathMetadata metadata) {
        super(BaseDateTime.class, metadata);
    }

}

