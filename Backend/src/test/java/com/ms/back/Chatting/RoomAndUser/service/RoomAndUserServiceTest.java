package com.ms.back.Chatting.RoomAndUser.service;

import com.ms.back.Alarm.entity.Alarm;
import com.ms.back.Chatting.dto.JoinedUserDTO;
import com.ms.back.Chatting.dto.RoomAndUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.GroupStatus;
import com.ms.back.Chatting.entity.JoinedUser;
import com.ms.back.Chatting.entity.Room;
import com.ms.back.Chatting.service.JoinedUserService;
import com.ms.back.Chatting.service.RoomAndUserService;
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
import java.util.Optional;

@SpringBootTest
public class RoomAndUserServiceTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomAndUserService roomAndUserService;

    @Test
    @DisplayName("방과 유저 동시 생성 테스트")
    void insertRoomWithUserTest() {

        // given
        RoomDTO roomDTO = new RoomDTO(GroupStatus.ACTIVE);
        JoinedUserDTO joinedUserDTO = new JoinedUserDTO( "user01", "Y", LocalDateTime.now());

        RoomAndUserDTO roomAndUserDTO = new RoomAndUserDTO(roomDTO, joinedUserDTO);

        // when
        Map<String, Object> result = new HashMap<>();

        try {
            roomAndUserService.createRoomAndUser(roomAndUserDTO);
            result.put("result", true);
            System.out.println("roomDTO : " + roomDTO);
        } catch (Exception e) {
            roomAndUserService.createRoomAndUser(roomAndUserDTO);
            result.put("result", false);
        }

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get("result"), true);

    }




}
