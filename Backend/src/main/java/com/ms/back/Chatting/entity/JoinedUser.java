package com.ms.back.Chatting.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class JoinedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joined_code")
    private int joinedCode;

    @Column(name = "rood_id")
    private int roodId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "joined_status")
    private String joinedStatus;;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected JoinedUser() {}
}
