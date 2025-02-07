package com.ms.back.chatting.entity;

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

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "joined_status")
    private String joinedStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JoinColumn(name = "room_id") // Room 엔티티와의 연관 관계
    @JsonIgnore // 직렬화 시 무시
    private int roomId;

    protected JoinedUser() {}

    @Builder
    public JoinedUser(int roomId, String userEmail, String joinedStatus, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.userEmail = userEmail;
        this.joinedStatus = joinedStatus;
        this.createdAt = createdAt;
    }

    public void markAsPatched() {
        this.joinedStatus = "N";
    }



}
