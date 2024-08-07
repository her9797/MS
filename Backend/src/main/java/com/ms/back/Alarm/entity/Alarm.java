package com.ms.back.Alarm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.back.Common.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Alarm {

    @Id
    @Column(name = "alarm_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alarmNo;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "alarm_msg", nullable = false)
    private String alarmMsg;

    @Column(name = "read_yn", nullable = true)
    private String readYn;

    @Column(name = "created_date", updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    protected Alarm() {}
}
