package com.ms.back.chatting.JoinedUser.service;


import com.ms.back.chatting.entity.Room;
import com.ms.back.chatting.service.JoinedUserService;
import com.ms.back.chatting.service.RoomAndUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JoinedUserServiceTest {

    @Autowired
    private JoinedUserService joinedUserService;

    @Autowired
    private RoomAndUserService roomAndUserService;

    @Test
    @DisplayName("방 / 유저 조인 조회 테스트")
    void selectRoomAndUserByRoomIdTest() {

        // given
        String userId = "user01";

        // when
        List<Room> joinedUser = roomAndUserService.selectRoomsByUserId(userId);

        // then
        Assertions.assertNotNull(joinedUser);
    }


}
