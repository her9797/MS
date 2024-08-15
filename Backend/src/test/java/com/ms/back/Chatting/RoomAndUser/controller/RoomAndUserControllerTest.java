package com.ms.back.Chatting.RoomAndUser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.back.Chatting.dto.JoinedUserDTO;
import com.ms.back.Chatting.dto.RoomAndUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.GroupStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomAndUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("방과 유저 동시 등록 테스트")
    void insertRoomAndUserTests() throws Exception {

        // given
        RoomDTO roomDTO = new RoomDTO(GroupStatus.ACTIVE);

        List<JoinedUserDTO> joinedUserDTOList = new ArrayList<>();
        JoinedUserDTO joinedUserDTO = new JoinedUserDTO("user01", "Y", LocalDateTime.now());

        joinedUserDTOList.add(joinedUserDTO);

        RoomAndUserDTO roomAndUserDTO = new RoomAndUserDTO(roomDTO, joinedUserDTOList);

        // when
        MvcResult result = mockMvc.perform(post("/roomAndUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomAndUserDTO)))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("등록 성공"))
                .andExpect(jsonPath("$.results").exists())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("response content : " + content);

    }


}
