package com.ms.back.Alarm.controller;

import com.ms.back.Alarm.dto.AlarmDTO;
import com.ms.back.Alarm.entity.Alarm;
import com.ms.back.Alarm.service.AlarmService;
import com.ms.back.Comment.dto.CmtDTO;
import com.ms.back.Common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AlarmController {

    private final AlarmService alarmService;

    @Autowired
    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @GetMapping("/alarms")
    public ResponseEntity<ResponseMessage> selectAlarmList(@RequestParam( value = "readYn", defaultValue = "N") String readYn,
                                                           @RequestParam( value = "userId") String userId) {

        Alarm alarmList = alarmService.selectAlarmList(readYn, userId);

        Map<String, Object> result = new HashMap<>();


        if (alarmList != null) {
            result.put("true", alarmList);
        } else {
            String errorMessage = "알람이 없습니다.";
            result.put("false", errorMessage);
        }

        // 알람이 없어도 메시지를 뿌려주기 위함이긴 하나, 변경 될 가능성 높음
        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", result);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /** 알람 등록 */
    @PostMapping("/alarms")
    public ResponseEntity<ResponseMessage> insertAlarm(@RequestBody AlarmDTO alarmDTO) {

        alarmDTO.setCreatedDate(LocalDateTime.now());

        return ResponseEntity.ok().body(new ResponseMessage(200, "등록 성공", alarmService.insertAlarm(alarmDTO)));
    }

    /** 알람 읽음 처리 수정 */
    @PutMapping("/alarms/{alarmNo}")
    public ResponseEntity<ResponseMessage> modifyAlarm(@PathVariable("alarmNo") int alarmNo,
                                                       @RequestBody String readYn) {

        readYn = "Y";

        return ResponseEntity.ok().body(new ResponseMessage(200, "수정 성공", alarmService.modifyAlarm(alarmNo, readYn)));

    }

}
