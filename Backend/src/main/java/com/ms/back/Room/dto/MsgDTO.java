package com.ms.back.Room.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MsgDTO {

    private int msgId;

    private String userId;

    private String senderName;

    private String msgContent;

    private LocalDateTime createdAt;

}
