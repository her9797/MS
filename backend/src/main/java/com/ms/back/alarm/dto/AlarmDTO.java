package com.ms.back.alarm.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlarmDTO {

    private int alarmNo;

    private String userEmail;

    private String alarmMsg;

    private String readYn;

    private LocalDateTime createdDate;

    public AlarmDTO(String readYn) {
        this.readYn = readYn;
    }

    public AlarmDTO(String userEmail, String alarmMsg, String readYn, LocalDateTime createdDate) {
        this.userEmail = userEmail;
        this.alarmMsg = alarmMsg;
        this.readYn = readYn;
        this.createdDate = createdDate;
    }
}
