package com.ms.back.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = -278516401L;

    public static final QComment comment = new QComment("comment");

    public final com.ms.back.common.QBaseTime _super = new com.ms.back.common.QBaseTime(this);

    public final StringPath cmtContent = createString("cmtContent");

    public final NumberPath<Integer> commentNo = createNumber("commentNo", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> noticeNo = createNumber("noticeNo", Integer.class);

    public final StringPath privateYn = createString("privateYn");

    public final StringPath userId = createString("userId");

    public final StringPath userPwd = createString("userPwd");

    public QComment(String variable) {
        super(Comment.class, forVariable(variable));
    }

    public QComment(Path<? extends Comment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComment(PathMetadata metadata) {
        super(Comment.class, metadata);
    }

}

