package com.ms.back.Room.dto;

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

    private String userId;

    private String joinedStatus;;

    private LocalDateTime createdAt;

}
