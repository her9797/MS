package com.ms.back.alarm.service;

import com.ms.back.alarm.dto.AlarmDTO;
import com.ms.back.alarm.entity.Alarm;
import com.ms.back.alarm.repository.AlarmRepository;
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

    public Map<String, Object> modifyAlarm(int alarmNo, String readYn) {

        Map<String, Object> result = new HashMap<>();

        Alarm alarmEntity = alarmRepository.findByAlarmNo(alarmNo);

        if (alarmEntity != null) {

            AlarmDTO alarm = modelMapper.map(alarmEntity, AlarmDTO.class);

            alarm.setReadYn(readYn);

            Alarm updateAlarm = modelMapper.map(alarm, Alarm.class);
            alarmRepository.save(updateAlarm);

            result.put("result", true);
        } else {
            result.put("result", false);
        }
        return result;
    }
}
