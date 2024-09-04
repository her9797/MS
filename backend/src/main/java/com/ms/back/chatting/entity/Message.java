package com.ms.back.chatting.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "message", indexes = {
@Index(name = "idx_msg_room", columnList = "msg_id, room_id") // 복합 인덱스 설정
})
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private int msgId;

    @Column(name = "room_id", nullable = false)
    private int roomId;

    @Column(name = "sender_id", nullable = false)
    private String userId;

    @Column(name = "sender_name", nullable = false)
    private String userName;

    @NotNull // 빈 문자열을 보낼수도 있지 않을까 하여, NotNull
    @Column(name = "msg_content")
    private String msgContent;

    @Column(name = "created_at", nullable = false)
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
