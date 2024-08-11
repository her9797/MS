package com.ms.back.Chatting.controller;

import com.ms.back.Alarm.dto.AlarmDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.service.RoomService;
import com.ms.back.Common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/rooms")
    public ResponseEntity<ResponseMessage> insertRoom(@RequestBody RoomDTO roomDTO) {

        return ResponseEntity.ok().body(new ResponseMessage(200, "등록 성공", roomService.insertRoom(roomDTO)));
    }

}
