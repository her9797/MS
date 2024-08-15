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
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

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

        List<JoinedUserDTO> joinedUserDTOList = new ArrayList<>();
        JoinedUserDTO joinedUserDTO = new JoinedUserDTO("user01", "Y", LocalDateTime.now());
        JoinedUserDTO joinedUserDTO2 = new JoinedUserDTO("user02", "Y", LocalDateTime.now());

        joinedUserDTOList.add(joinedUserDTO);
        joinedUserDTOList.add(joinedUserDTO2);


        RoomAndUserDTO roomAndUserDTO = new RoomAndUserDTO(roomDTO, joinedUserDTOList);

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
