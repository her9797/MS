package com.ms.back.Room.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private int msgId;

    @Column(name = "sender_id")
    private String userId;

    @Column(name = "sender_name")
    private String userName;

    @Column(name = "msg_content")
    private String msgContent;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected Message() {}
}
