package com.ms.back.Notice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.back.Notice.dto.NoticeDTO;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NoticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("공지 조회(페이징) 테스트")
    void selectNoticeListTest() throws Exception {

        // given
        int size = 10;
        int page = 1;

        // when
        MvcResult result = mockMvc.perform(get("/notices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("size", String.valueOf(size))
                        .param("page", String.valueOf(page)))

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
    @DisplayName("공지사항 등록 테스트")
    void insertNotice() throws Exception {

        // given
        NoticeDTO noticeDTO = new NoticeDTO(3, "제목3", "내용3", 'N', LocalDateTime.now());

        // when
        MvcResult result = mockMvc.perform(post("/notices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noticeDTO)))
        // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("등록 성공"))
                .andExpect(jsonPath("$.results").exists())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("response content : " + content);

    }

}
