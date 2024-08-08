package com.ms.back.Alarm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlarmDTO {

    private int alarmNo;

    private String userId;

    private String alarmMsg;

    private String readYn;

    private LocalDateTime createdDate;

    public AlarmDTO(String readYn) {
        this.readYn = readYn;
    }

    public AlarmDTO(String userId, String alarmMsg, String readYn, LocalDateTime createdDate) {
        this.userId = userId;
        this.alarmMsg = alarmMsg;
        this.readYn = readYn;
        this.createdDate = createdDate;
    }
}
