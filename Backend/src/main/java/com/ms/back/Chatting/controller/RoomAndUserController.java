package com.ms.back.Chatting.controller;

import com.ms.back.Chatting.dto.RoomAndUserDTO;
import com.ms.back.Chatting.service.RoomAndUserService;
import com.ms.back.Common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RoomAndUserController {

    private final RoomAndUserService roomAndUserService;

    @Autowired
    public RoomAndUserController(RoomAndUserService roomAndUserService) {
        this.roomAndUserService = roomAndUserService;
    }

    @PostMapping("/roomAndUser")
    public ResponseEntity<ResponseMessage> createRoomAndUser(@RequestBody RoomAndUserDTO roomAndUserDTO) {

        return ResponseEntity.ok().body(new ResponseMessage(200, "등록 성공", roomAndUserService.createRoomAndUser(roomAndUserDTO.getRoomDTO(), roomAndUserDTO.getJoinedUserDTO())));
    }

}
