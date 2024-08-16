package com.ms.back.Chatting.controller;

import com.ms.back.Alarm.dto.AlarmDTO;
import com.ms.back.Chatting.dto.MsgDTO;
import com.ms.back.Chatting.service.MsgService;
import com.ms.back.Common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MsgController {

    private final MsgService msgService;

    @Autowired
    public MsgController(MsgService msgService) {
        this.msgService = msgService;
    }

    /** 알람 등록 */
    @PostMapping("/messages")
    public ResponseEntity<ResponseMessage> insertAlarm(@RequestBody MsgDTO msgDTO) {

        msgDTO.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.ok().body(new ResponseMessage(200, "등록 성공", msgService.insertMsg(msgDTO)));
    }

}
