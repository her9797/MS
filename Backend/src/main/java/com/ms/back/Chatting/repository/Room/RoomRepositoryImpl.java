package com.ms.back.Chatting.repository.Room;

import com.ms.back.Chatting.dto.JoinedUserDTO;
import com.ms.back.Chatting.dto.RoomAndUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.QJoinedUser;
import com.ms.back.Chatting.entity.QRoom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<RoomAndUserDTO> findRoomDetailById(int roomId) {
        QRoom room = QRoom.room;
        QJoinedUser user = QJoinedUser.joinedUser;

        RoomAndUserDTO roomDetailDto = queryFactory
                .select(Projections.constructor(
                        RoomAndUserDTO.class,
                        Projections.constructor(
                                RoomDTO.class,
                                room.roomId,
                                room.groupStatus
                        ),
                        Projections.constructor(
                                JoinedUserDTO.class,
                                user.joinedCode,
                                Projections.constructor(
                                        RoomDTO.class,
                                        room.roomId,
                                        room.groupStatus
                                ),
                                user.userId,
                                user.joinedStatus,
                                user.createdAt
                        )
                ))
                .from(room)
                .leftJoin(user).on(user.room.eq(room)) // `room`과 `joinedUser`를 연결
                .where(room.roomId.eq(roomId))
                .fetchOne();

        return Optional.ofNullable(roomDetailDto);
    }
}
