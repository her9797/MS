package com.ms.back.Chatting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoom is a Querydsl query type for Room
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoom extends EntityPathBase<Room> {

    private static final long serialVersionUID = 1242541642L;

    public static final QRoom room = new QRoom("room");

    public final EnumPath<GroupStatus> groupStatus = createEnum("groupStatus", GroupStatus.class);

    public final ListPath<JoinedUser, QJoinedUser> joinedUser = this.<JoinedUser, QJoinedUser>createList("joinedUser", JoinedUser.class, QJoinedUser.class, PathInits.DIRECT2);

    public final NumberPath<Integer> roomId = createNumber("roomId", Integer.class);

    public QRoom(String variable) {
        super(Room.class, forVariable(variable));
    }

    public QRoom(Path<? extends Room> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoom(PathMetadata metadata) {
        super(Room.class, metadata);
    }

}

