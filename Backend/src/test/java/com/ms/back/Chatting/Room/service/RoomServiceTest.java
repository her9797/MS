package com.ms.back.Chatting.Room.service;

import com.ms.back.Alarm.dto.AlarmDTO;
import com.ms.back.Chatting.dto.RoomAndUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.GroupStatus;
import com.ms.back.Chatting.entity.Room;
import com.ms.back.Chatting.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
