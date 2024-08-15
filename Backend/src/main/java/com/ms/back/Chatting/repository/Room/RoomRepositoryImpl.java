package com.ms.back.Chatting.repository.Room;

import com.ms.back.Chatting.dto.JoinedUserDTO;
import com.ms.back.Chatting.dto.RoomAndUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.QJoinedUser;
import com.ms.back.Chatting.entity.QRoom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<RoomAndUserDTO> findRoomDetailById(int roomId) {
        QRoom room = QRoom.room;
        QJoinedUser user = QJoinedUser.joinedUser;

        // RoomDTO 조회
        RoomDTO roomDTO = queryFactory
                .select(Projections.constructor(
                        RoomDTO.class,
                        room.roomId,
                        room.groupStatus
                ))
                .from(room)
                .where(room.roomId.eq(roomId))
                .fetchOne();

        System.out.println(roomDTO);

        // JoinedUserDTO 목록 조회
        List<JoinedUserDTO> joinedUserDTO = queryFactory
                .select(Projections.constructor(
                        JoinedUserDTO.class,
                        user.joinedCode,
                        user.roomId,
                        user.userId,
                        user.joinedStatus,
                        user.createdAt
                ))
                .from(user)
                .where(user.roomId.eq(roomId))
                .fetch();

        System.out.println(joinedUserDTO);


        // RoomDTO와 JoinedUserDTO 목록을 RoomAndUserDTO로 묶어 반환
        RoomAndUserDTO roomAndUserDTO = new RoomAndUserDTO(roomDTO, joinedUserDTO);

        return Optional.ofNullable(roomAndUserDTO);
    }
}
