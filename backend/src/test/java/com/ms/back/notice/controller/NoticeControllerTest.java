package com.ms.back.notice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.back.notice.dto.NoticeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @DisplayName("공지사항 상세 조회")
    void selectByNoticeIdTest() throws Exception {

        // given
        int noticeNo = 5;

        // when
        MvcResult result = mockMvc.perform(get("/notices/{noticeNo}", noticeNo)
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatusCode").value(200))
                .andExpect(jsonPath("$.message").value("조회 성공"))
                .andExpect(jsonPath("$.results").exists())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("Content: " + content);

    }


    @Test
    @DisplayName("공지사항 등록 테스트")
    void insertNotice() throws Exception {

        // given
        NoticeDTO noticeDTO = new NoticeDTO(5, "제목3", "내용3", 'N', LocalDateTime.now(), "user01");

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

    @Test
    @DisplayName("공지사항 수정 테스트")
    void modifyNotice() throws Exception {

        // given
        int noticeNo = 5;

        NoticeDTO noticeDTO = new NoticeDTO(
                "컨트롤러 본문 수정 테스트",
                "컨트롤러 제목 수정",
                LocalDateTime.now(),
                "user01"
        );

        String modifiedDateStr = noticeDTO.getModifiedDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // JSON request body 생성
        String requestBody = String.format(
                "{\"content\": \"%s\", \"title\": \"%s\", \"modifiedDate\": \"%s\", \"userEmail\": \"%s\"}",
                noticeDTO.getContent(), noticeDTO.getTitle(), modifiedDateStr, noticeDTO.getUserEmail()
        );

        // when
        MvcResult result = mockMvc.perform(patch("/notices/{noticeNo}", noticeNo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatusCode").value(200))
                .andExpect(jsonPath("$.message").value("수정 성공"))
                .andExpect(jsonPath("$.results").exists())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("Content: " + content);
    }

    @Test
    @DisplayName("공지사항 삭제 테스트")
    void deleteNotice() throws Exception {

        /* 댓글 기능으로 인한 @PutMapping */

        // given
        int noticeNo = 4;

        // when
        MvcResult result = mockMvc.perform(put("/notices/{noticeNo}", noticeNo)
                .contentType(MediaType.APPLICATION_JSON))

        // then
            .andExpect(jsonPath("$.httpStatusCode").value(200))
            .andExpect(jsonPath("$.message").value("삭제 성공"))
            .andExpect(jsonPath("$.results").exists())
            .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("Content: " + content);

    }

}
