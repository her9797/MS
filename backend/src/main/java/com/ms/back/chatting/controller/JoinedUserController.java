package com.ms.back.chatting.controller;

import com.ms.back.chatting.dto.JoinedUserDTO;
import com.ms.back.chatting.dto.RoomAndUserDTO;
import com.ms.back.chatting.entity.Room;
import com.ms.back.chatting.service.JoinedUserService;
import com.ms.back.chatting.service.RoomAndUserService;
import com.ms.back.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/joinedUser")
public class JoinedUserController {

    private final JoinedUserService joinedUserService;

    private final RoomAndUserService roomAndUserService;

    @Autowired
    public JoinedUserController(JoinedUserService joinedUserService, RoomAndUserService roomAndUserService) {
        this.joinedUserService = joinedUserService;
        this.roomAndUserService = roomAndUserService;
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<ResponseMessage> selectRoomListByUserEmail(@PathVariable("userEmail") String userEmail) {

        List<RoomAndUserDTO> roomAndUser = roomAndUserService.selectRoomsByUserEmail(userEmail);

        Map<String, Object> results = new HashMap<>();
        results.put("results", roomAndUser);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", results);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PatchMapping("/{roomId}")
    public ResponseEntity<ResponseMessage> modifyJoinedStatus(@PathVariable("roomId") int roomId,
                                                              @RequestBody JoinedUserDTO joinedUserDTO) {

        return ResponseEntity.ok().body(new ResponseMessage(200, "수정(삭제) 성공", joinedUserService.modifyJoinedStatus(roomId, joinedUserDTO)));
    }



}
