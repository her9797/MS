package com.ms.back.chatting.Room.service;

import com.ms.back.chatting.dto.RoomAndUserDTO;
import com.ms.back.chatting.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    @DisplayName("방 / 유저 조인 상세 조회 테스트")
    void selectRoomDetailTest() {

        // given
        int roomId = 1;

        // when
        RoomAndUserDTO roomDetail = roomService.selectRoomDetail(roomId);

        // then
        Assertions.assertNotNull(roomDetail);
    }



}
