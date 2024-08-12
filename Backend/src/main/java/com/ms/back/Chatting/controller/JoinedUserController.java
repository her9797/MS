package com.ms.back.Chatting.controller;

import com.ms.back.Chatting.dto.JoinedUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.service.JoinedUserService;
import com.ms.back.Common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinedUserController {

    private final JoinedUserService joinedUserService;

    @Autowired
    public JoinedUserController(JoinedUserService joinedUserService) {
        this.joinedUserService = joinedUserService;
    }

    @PostMapping("/joinedUser")
    public ResponseEntity<ResponseMessage> insertJoinedUser(@RequestBody JoinedUserDTO joinedUserDTO) {


        return ResponseEntity.ok().body(new ResponseMessage(200, "등록 성공", joinedUserService.insertJoinedUser(joinedUserDTO)));
    }

}
