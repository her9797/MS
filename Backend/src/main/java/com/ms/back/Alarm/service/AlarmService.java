package com.ms.back.Alarm.service;

import com.ms.back.Alarm.entity.Alarm;
import com.ms.back.Alarm.repository.AlarmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlarmService {

    private final AlarmRepository alarmRepository;

    @Autowired
    public AlarmService(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    public Alarm selectAlarmList(String readYn, String userId) {

        return alarmRepository.findByUserIdAndReadYn(userId, readYn);

    }
}
