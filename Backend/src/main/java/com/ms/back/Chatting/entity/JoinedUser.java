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

//    @Column(name = "rood_id")
//    private int roodId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "joined_status")
    private String joinedStatus;;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "room_id") // Room 엔티티와의 연관 관계
    @JsonIgnore // 직렬화 시 무시
    private Room room;

    @Builder
    public JoinedUser(Room room, String userId, String joinedStatus, LocalDateTime createdAt) {
        this.room = room;
        this.userId = userId;
        this.joinedStatus = joinedStatus;
        this.createdAt = createdAt;
    }
    protected JoinedUser() {}
}
