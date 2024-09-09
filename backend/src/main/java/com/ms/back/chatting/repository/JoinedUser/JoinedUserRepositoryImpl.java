package com.ms.back.chatting.repository.JoinedUser;

import com.ms.back.chatting.dto.JoinedUserDTO;
import com.ms.back.chatting.dto.RoomAndUserDTO;
import com.ms.back.chatting.dto.RoomDTO;
import com.ms.back.chatting.entity.JoinedUser;
import com.ms.back.chatting.entity.QJoinedUser;
import com.ms.back.chatting.entity.QRoom;
import com.ms.back.chatting.entity.Room;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JoinedUserRepositoryImpl implements JoinedUserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RoomAndUserDTO> findRoomsByUserEmail(String userEmail) {
        QRoom room = QRoom.room;
        QJoinedUser joinedUser = QJoinedUser.joinedUser;

        // Room과 관련된 JoinedUser 목록을 조회할 때, joinedStatus가 'Y'인 사용자만 포함
        List<Room> roomList = jpaQueryFactory.selectFrom(room)
                .join(joinedUser).on(joinedUser.roomId.eq(room.roomId))
                .where(joinedUser.userEmail.eq(userEmail)
                        .and(joinedUser.joinedStatus.eq("Y"))) // joinedStatus가 'Y'인 사용자만 조회
                .fetch();

        // 각 Room에 대해 관련된 JoinedUser 목록을 조회할 때, joinedStatus가 'Y'인 사용자만 포함
        return roomList.stream().map(roomEntity -> {
            // RoomDTO 변환
            RoomDTO rooms = new RoomDTO(roomEntity.getRoomId(), roomEntity.getGroupStatus());

            // JoinedUser 목록 변환 (joinedStatus가 'Y'인 사용자만 포함)
            List<JoinedUser> joinedUsers = jpaQueryFactory.selectFrom(joinedUser)
                    .where(joinedUser.roomId.eq(roomEntity.getRoomId())
                            .and(joinedUser.joinedStatus.eq("Y"))) // joinedStatus가 'Y'인 사용자만 조회
                    .fetch();

            List<JoinedUserDTO> joinedUserList = joinedUsers.stream()
                    .map(user -> new JoinedUserDTO(user.getRoomId(), user.getUserEmail())) // 필드에 맞게 수정
                    .collect(Collectors.toList());

            return new RoomAndUserDTO(rooms, joinedUserList);
        }).collect(Collectors.toList());
    }
}
