package com.ms.back.chatting.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MsgDTO {

    private int msgId;

    private int roomId;

    private String userEmail;

    private String userName;

    private String msgContent;

    private LocalDateTime createdAt;

    private String topic;

    public MsgDTO(int roomId, String userEmail, String userName, String msgContent, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.msgContent = msgContent;
        this.createdAt = createdAt;
    }

}
