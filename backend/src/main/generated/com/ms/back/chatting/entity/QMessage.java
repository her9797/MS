package com.ms.back.chatting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessage extends EntityPathBase<Message> {

    private static final long serialVersionUID = -1012182952L;

    public static final QMessage message = new QMessage("message");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath msgContent = createString("msgContent");

    public final NumberPath<Integer> msgId = createNumber("msgId", Integer.class);

    public final NumberPath<Integer> roomId = createNumber("roomId", Integer.class);

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userName = createString("userName");

    public QMessage(String variable) {
        super(Message.class, forVariable(variable));
    }

    public QMessage(Path<? extends Message> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessage(PathMetadata metadata) {
        super(Message.class, metadata);
    }

}

