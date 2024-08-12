package com.ms.back.Chatting.JoinedUser.service;


import com.ms.back.Chatting.dto.JoinedUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.GroupStatus;
import com.ms.back.Chatting.service.JoinedUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JoinedUserServiceTest {

    @Autowired
    private JoinedUserService joinedUserService;

    @Test
    @DisplayName("방 생성 시, 해당 방 회원 목록 등록 테스트")
    void insertJoinedUserTest() {

        // given
        JoinedUserDTO joinedUserDTO = new JoinedUserDTO(1, "user01", "Y", LocalDateTime.now());

        // when
        Map<String, Object> result = new HashMap<>();

        try {
            joinedUserService.insertJoinedUser(joinedUserDTO);
            result.put("result", true);
            System.out.println("joinedUserDTO : " + joinedUserDTO);
        } catch (Exception e) {
            joinedUserService.insertJoinedUser(joinedUserDTO);
            result.put("result", false);
        }

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get("result"), true);

    }


}
