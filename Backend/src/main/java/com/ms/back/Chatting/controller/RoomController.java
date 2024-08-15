package com.ms.back.Chatting.controller;

import com.ms.back.Alarm.dto.AlarmDTO;
import com.ms.back.Chatting.dto.RoomAndUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.service.RoomService;
import com.ms.back.Common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ResponseMessage> getRoomDetail(@PathVariable("roomId") int roomId) {

        RoomAndUserDTO roomDetail = roomService.selectRoomDetail(roomId);
        Map<String, Object> response = new HashMap<>();
        response.put("roomAndUser", roomDetail);

        /** 조건문 / 예외 등 필요 */
        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", response);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
