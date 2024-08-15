package com.ms.back.Chatting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJoinedUser is a Querydsl query type for JoinedUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJoinedUser extends EntityPathBase<JoinedUser> {

    private static final long serialVersionUID = -554108701L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJoinedUser joinedUser = new QJoinedUser("joinedUser");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> joinedCode = createNumber("joinedCode", Integer.class);

    public final StringPath joinedStatus = createString("joinedStatus");

    public final QRoom room;

    public final StringPath userId = createString("userId");

    public QJoinedUser(String variable) {
        this(JoinedUser.class, forVariable(variable), INITS);
    }

    public QJoinedUser(Path<? extends JoinedUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJoinedUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJoinedUser(PathMetadata metadata, PathInits inits) {
        this(JoinedUser.class, metadata, inits);
    }

    public QJoinedUser(Class<? extends JoinedUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new QRoom(forProperty("room")) : null;
    }

}

