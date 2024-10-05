package com.ms.back.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1928073953L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userGender = createString("userGender");

    public final StringPath userName = createString("userName");

    public final StringPath userNickname = createString("userNickname");

    public final QUserProfile userProfile;

    public final StringPath userPwd = createString("userPwd");

    public final EnumPath<com.ms.back.user.dto.UserRole> userRole = createEnum("userRole", com.ms.back.user.dto.UserRole.class);

    public final StringPath userStatus = createString("userStatus");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userProfile = inits.isInitialized("userProfile") ? new QUserProfile(forProperty("userProfile"), inits.get("userProfile")) : null;
    }

}

