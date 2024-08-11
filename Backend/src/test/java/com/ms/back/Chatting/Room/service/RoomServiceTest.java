package com.ms.back.Chatting.Room.service;

import com.ms.back.Alarm.dto.AlarmDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.GroupStatus;
import com.ms.back.Chatting.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    @DisplayName("방 생성 테스트")
    void insertRoomTest() {

        // given
        RoomDTO roomDTO = new RoomDTO(GroupStatus.ACTIVE);

        // when
        Map<String, Object> result = new HashMap<>();

        try {
            roomService.insertRoom(roomDTO);
            result.put("result", true);
            System.out.println("roomDTO : " + roomDTO);
        } catch (Exception e) {
            roomService.insertRoom(roomDTO);
            result.put("result", false);
        }

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get("result"), true);

    }

}
