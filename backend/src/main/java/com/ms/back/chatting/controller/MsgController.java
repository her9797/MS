package com.ms.back.chatting.controller;

import com.ms.back.chatting.dto.MsgDTO;
import com.ms.back.chatting.entity.Message;
import com.ms.back.chatting.service.MsgService;
import com.ms.back.common.ResponseMessage;
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
