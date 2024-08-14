package com.ms.back.Chatting.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JoinedUserDTO {

    private int joinedCode;

    private RoomDTO roodId;

    private String userId;

    private String joinedStatus;

    private LocalDateTime createdAt;

    public JoinedUserDTO(RoomDTO roodId, String userId, String joinedStatus, LocalDateTime createdAt) {
        this.roodId = roodId;
        this.userId = userId;
        this.joinedStatus = joinedStatus;
        this.createdAt = createdAt;
    }

    public JoinedUserDTO(String userId, String joinedStatus, LocalDateTime createdAt) {
        this.userId = userId;
        this.joinedStatus = joinedStatus;
        this.createdAt = createdAt;
    }
}
