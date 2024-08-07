package com.ms.back.Alarm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

    public AlarmDTO(String userId, String alarmMsg, String readYn) {
        this.userId = userId;
        this.alarmMsg = alarmMsg;
        this.readYn = readYn;
    }
}
