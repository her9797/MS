package com.ms.back.chatting.controller;

import com.ms.back.chatting.dto.RoomAndUserDTO;
import com.ms.back.chatting.service.RoomAndUserService;
import com.ms.back.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomAndUserController {

    private final RoomAndUserService roomAndUserService;

    @Autowired
    public RoomAndUserController(RoomAndUserService roomAndUserService) {
        this.roomAndUserService = roomAndUserService;
    }

    @PostMapping("/roomAndUser")
    public ResponseEntity<ResponseMessage> insertRoomAndUser(@RequestBody RoomAndUserDTO roomAndUserDTO) {

        // DTO 두개를 하나의 DTO로 묶어 작업
        return ResponseEntity.ok().body(new ResponseMessage(200, "등록 성공", roomAndUserService.createRoomAndUser(roomAndUserDTO)));
    }


}
