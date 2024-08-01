package com.ms.back.Comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.back.Comment.dto.CmtDTO;
import com.ms.back.Notice.dto.NoticeDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("공지 내 댓글 등록 테스트")
    void insertCmtTests() throws Exception {

        // given
        CmtDTO cmtDTO = new CmtDTO(3, "댓글 내용", "userId", "userPws", "N", LocalDateTime.now());

        // when
        MvcResult result = mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cmtDTO)))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("등록 성공"))
                .andExpect(jsonPath("$.results").exists())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("response content : " + content);

    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void modifyComment() throws Exception {

        // given
        int commentNo = 1;

        CmtDTO cmtDTO = new CmtDTO(
                "컨트롤러 댓글 수정 테스트",
                LocalDateTime.now()
        );

        // JSON request body 생성
        String requestBody = String.format(
                "{\"cmtContent\": \"%s\", \"modifiedDate\": \"%s\"}",
                cmtDTO.getCmtContent(), cmtDTO.getModifiedDate()
        );

        // when
        MvcResult result = mockMvc.perform(patch("/comments/{commentNo}", commentNo)
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

}
