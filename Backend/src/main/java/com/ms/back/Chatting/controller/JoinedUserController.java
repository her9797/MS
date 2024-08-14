package com.ms.back.Chatting.controller;

import com.ms.back.Chatting.dto.JoinedUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.JoinedUser;
import com.ms.back.Chatting.entity.Room;
import com.ms.back.Chatting.service.JoinedUserService;
import com.ms.back.Chatting.service.RoomAndUserService;
import com.ms.back.Common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JoinedUserController {

    private final JoinedUserService joinedUserService;

    private final RoomAndUserService roomAndUserService;

    @Autowired
    public JoinedUserController(JoinedUserService joinedUserService, RoomAndUserService roomAndUserService) {
        this.joinedUserService = joinedUserService;
        this.roomAndUserService = roomAndUserService;
    }

    @GetMapping("/joinedUser/{userId}")
    public ResponseEntity<ResponseMessage> selectRoomListByUserId(@PathVariable("userId") String userId) {

        List<Room> rooms = roomAndUserService.selectRoomsByUserId(userId);

        Map<String, Object> results = new HashMap<>();
        results.put("rooms", rooms);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", results);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
