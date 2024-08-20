package com.ms.back.chatting.controller;

import com.ms.back.chatting.dto.RoomAndUserDTO;
import com.ms.back.chatting.service.RoomService;
import com.ms.back.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
