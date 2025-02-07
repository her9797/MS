package com.ms.back.chatting.Message.service;

import com.ms.back.chatting.dto.MsgDTO;
import com.ms.back.chatting.entity.Message;
import com.ms.back.chatting.service.MsgService;
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
public class MsgServiceTest {

    @Autowired
    private MsgService msgService;

    @Test
    @DisplayName("메시지 등록 테스트")
    void insertMsgTest() {

        // given
        MsgDTO msgDTO = new MsgDTO(1, "user02", "이인호", "ㅎㅇ", LocalDateTime.now());

        // when
        Map<String, Object> result = new HashMap<>();

        try {
            msgService.insertMsg(msgDTO);
            result.put("result", true);
            System.out.println("msgDTO : " + msgDTO);
        } catch (Exception e) {
            msgService.insertMsg(msgDTO);
            result.put("result", false);
        }

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get("result"), true);

    }

    @Test
    @DisplayName("메시지 조회 테스트")
    void selectMsgTest() {

        // given
        int roomId = 1;

        // when
        List<Message> msgList = msgService.selectMsg(roomId);

        // then
        Assertions.assertNotNull(msgList);

    }

}
