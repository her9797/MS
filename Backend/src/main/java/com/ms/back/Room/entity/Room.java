package com.ms.back.Room.entity;

import com.ms.back.User.entity.User;
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
    private GroupStatus groupStatus;

    protected Room() {}
}
