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

    private String senderName;

    private String msgContent;

    private LocalDateTime createdAt;

}
