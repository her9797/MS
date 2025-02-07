package com.ms.back.chatting.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;

    @Column(name = "group_status")
    @Enumerated(EnumType.STRING)    // 문자열로 저장
    private GroupStatus groupStatus;

    protected Room() {}
}
