package com.ms.back.chatting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJoinedUser is a Querydsl query type for JoinedUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJoinedUser extends EntityPathBase<JoinedUser> {

    private static final long serialVersionUID = 438525123L;

    public static final QJoinedUser joinedUser = new QJoinedUser("joinedUser");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> joinedCode = createNumber("joinedCode", Integer.class);

    public final StringPath joinedStatus = createString("joinedStatus");

    public final NumberPath<Integer> roomId = createNumber("roomId", Integer.class);

    public final StringPath userId = createString("userId");

    public QJoinedUser(String variable) {
        super(JoinedUser.class, forVariable(variable));
    }

    public QJoinedUser(Path<? extends JoinedUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJoinedUser(PathMetadata metadata) {
        super(JoinedUser.class, metadata);
    }

}

