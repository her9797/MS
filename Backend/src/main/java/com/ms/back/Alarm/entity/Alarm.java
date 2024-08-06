package com.ms.back.Alarm.entity;

import jakarta.persistence.*;
import lombok.Getter;

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

    protected Alarm() {}
}
