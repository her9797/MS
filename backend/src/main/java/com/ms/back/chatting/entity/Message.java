package com.ms.back.chatting.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private int msgId;

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "sender_id")
    private String userId;

    @Column(name = "sender_name")
    private String userName;

    @Column(name = "msg_content")
    private String msgContent;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected Message() {}

    @Builder
    public Message(int roomId, String userId, String userName, String msgContent, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.userId = userId;
        this.userName = userName;
        this.msgContent = msgContent;
        this.createdAt = createdAt;
    }
}
