package com.ms.back.Alarm.service;

import com.ms.back.Alarm.dto.AlarmDTO;
import com.ms.back.Alarm.entity.Alarm;
import com.ms.back.Alarm.repository.AlarmRepository;
import com.ms.back.Comment.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AlarmService {

    private final AlarmRepository alarmRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public AlarmService(AlarmRepository alarmRepository, ModelMapper modelMapper) {
        this.alarmRepository = alarmRepository;
        this.modelMapper = modelMapper;
    }

    /** 알람 조회 */
    public Alarm selectAlarmList(String readYn, String userId) {

        return alarmRepository.findByUserIdAndReadYn(userId, readYn);

    }

    /** 알람 등록 */
    public Map<String, Object> insertAlarm(AlarmDTO alarmDTO) {

        Map<String, Object> result = new HashMap<>();

        try {
            Alarm alarmEntity = modelMapper.map(alarmDTO, Alarm.class);
            alarmRepository.save(alarmEntity);

            result.put("result", true);
        } catch (Exception e) {

            log.error(e.getMessage());
            result.put("result", false);
        }
        return result;

    }
}
