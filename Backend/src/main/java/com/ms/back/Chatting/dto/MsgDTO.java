package com.ms.back.Chatting.dto;

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

    private String userId;

    private String userName;

    private String msgContent;

    private LocalDateTime createdAt;

    public MsgDTO(int roomId, String userId, String userName, String msgContent, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.userId = userId;
        this.userName = userName;
        this.msgContent = msgContent;
        this.createdAt = createdAt;
    }
}
