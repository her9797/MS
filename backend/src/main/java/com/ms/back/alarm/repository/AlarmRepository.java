package com.ms.back.alarm.repository;

import com.ms.back.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Integer> {

    // 쿼리 메서드 사용 - 파라미터로 userEmail & readYn
    // 주의 사항 : 파라미터 값을 줄 땐, 쿼리 메서드 순서와 동일하게 작성해야 정상적으로 작동
    Alarm findByUserEmailAndReadYn(String userEmail, String readYn);

    Alarm findByAlarmNo(int alarmNo);

}
