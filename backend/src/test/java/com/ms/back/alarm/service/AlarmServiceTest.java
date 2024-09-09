package com.ms.back.alarm.service;

import com.ms.back.alarm.dto.AlarmDTO;
import com.ms.back.alarm.entity.Alarm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class AlarmServiceTest {

    @Autowired
    private AlarmService alarmService;

    @Test
    @DisplayName("알람 조회 테스트")
    void selectAlarmPageTest() {

        // given
        String readYn = "N";
        String userEmail = "user01";

        // when
        Alarm alarm = alarmService.selectAlarmList(readYn, userEmail);

        // then
        Assertions.assertNotNull(alarm);
    }

    @Test
    @DisplayName("알람 등록 테스트")
    void insertAlarmTest() {

        // given
        AlarmDTO alarmDTO = new AlarmDTO("user01", "새 메시지가 등록되었습니다.", "N", LocalDateTime.now());

        // when
        Map<String, Object> result = new HashMap<>();

        try {
            alarmService.insertAlarm(alarmDTO);
            result.put("result", true);
            System.out.println("alarmDTO : " + alarmDTO);
        } catch (Exception e) {
            alarmService.insertAlarm(alarmDTO);
            result.put("result", false);
        }

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get("result"), true);

    }

    @Test
    @DisplayName("알람 수정 테스트")
    void modifyAlarmTest() {

        // given
        int alarmNo = 2;
        String readYn = "Y";

        // when
        Map<String, Object> result = new HashMap<>();
        result.put("result", alarmService.modifyAlarm(alarmNo, readYn));

        // then
        Assertions.assertNotNull(result);

    }



}
