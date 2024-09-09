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

    private int roodId;

    private String userEmail;

    private String joinedStatus;

    private LocalDateTime createdAt;

    public JoinedUserDTO(int roodId, String userEmail, String joinedStatus, LocalDateTime createdAt) {
        this.roodId = roodId;
        this.userEmail = userEmail;
        this.joinedStatus = joinedStatus;
        this.createdAt = createdAt;
    }


    public JoinedUserDTO(String userEmail, String joinedStatus, LocalDateTime createdAt) {
        this.userEmail = userEmail;
        this.joinedStatus = joinedStatus;
        this.createdAt = createdAt;
    }

    public JoinedUserDTO(int roodId, String userEmail) {
        this.roodId = roodId;
        this.userEmail = userEmail;
    }
}
