package com.ms.back.chatting.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JoinedUserDTO {

    private int joinedCode;

    private int roomId;

    private String userEmail;

    private String joinedStatus;

    private LocalDateTime createdAt;

    public JoinedUserDTO(int roomId, String userEmail, String joinedStatus, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.userEmail = userEmail;
        this.joinedStatus = joinedStatus;
        this.createdAt = createdAt;
    }


    public JoinedUserDTO(String userEmail, String joinedStatus, LocalDateTime createdAt) {
        this.userEmail = userEmail;
        this.joinedStatus = joinedStatus;
        this.createdAt = createdAt;
    }

    public JoinedUserDTO(int roomId, String userEmail) {
        this.roomId = roomId;
        this.userEmail = userEmail;
    }
}
