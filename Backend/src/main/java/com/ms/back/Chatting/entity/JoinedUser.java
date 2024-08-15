package com.ms.back.Chatting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class JoinedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joined_code")
    private int joinedCode;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "joined_status")
    private String joinedStatus;;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JoinColumn(name = "room_id") // Room 엔티티와의 연관 관계
    @JsonIgnore // 직렬화 시 무시
    private int roomId;

    protected JoinedUser() {}

    @Builder
    public JoinedUser(int roomId, String userId, String joinedStatus, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.userId = userId;
        this.joinedStatus = joinedStatus;
        this.createdAt = createdAt;
    }

    @Builder
    public JoinedUser(int joinedCode, String userId, String joinedStatus, LocalDateTime createdAt, int roomId) {
        this.joinedCode = joinedCode;
        this.userId = userId;
        this.joinedStatus = joinedStatus;
        this.createdAt = createdAt;
        this.roomId = roomId;
    }

}
