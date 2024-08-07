package com.ms.back.Alarm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.back.Alarm.dto.AlarmDTO;
import com.ms.back.Comment.dto.CmtDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AlarmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("알람 조회 테스트")
    void selectAlarmListTest() throws Exception {

        // given
        String userId = "user1";
        String readYn = "N";

        // when
        MvcResult result = mockMvc.perform(get("/alarms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", userId)
                        .param("readYn", readYn))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatusCode").value(200))
                .andExpect(jsonPath("$.message").value("조회 성공"))
                .andExpect(jsonPath("$.results").exists())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("Response Content: " + content);

    }

    @Test
    @DisplayName("알람 등록 테스트")
    void insertAlarmTests() throws Exception {

        // given
        AlarmDTO alarmDTO = new AlarmDTO("userId", "알람이 등록되었습니다 컨트롤러", "N", LocalDateTime.now());

        // when
        MvcResult result = mockMvc.perform(post("/alarms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alarmDTO)))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("등록 성공"))
                .andExpect(jsonPath("$.results").exists())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("response content : " + content);

    }


}
