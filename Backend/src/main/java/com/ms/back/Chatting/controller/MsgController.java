package com.ms.back.Chatting.controller;

import com.ms.back.Alarm.dto.AlarmDTO;
import com.ms.back.Chatting.dto.MsgDTO;
import com.ms.back.Chatting.entity.Message;
import com.ms.back.Chatting.entity.Room;
import com.ms.back.Chatting.service.MsgService;
import com.ms.back.Common.ResponseMessage;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MsgController {

    private final MsgService msgService;

    @Autowired
    public MsgController(MsgService msgService) {
        this.msgService = msgService;
    }

    /** 메시지 등록 */
    @PostMapping("")
    public ResponseEntity<ResponseMessage> insertMessage(@RequestBody MsgDTO msgDTO) {

        msgDTO.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.ok().body(new ResponseMessage(200, "등록 성공", msgService.insertMsg(msgDTO)));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ResponseMessage> selectMsgList(@PathVariable("roomId") int roomId) {

        List<Message> msg = msgService.selectMsg(roomId);

        Map<String, Object> results = new HashMap<>();
        results.put("msg", msg);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", results);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
