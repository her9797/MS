package com.ms.back.Chatting.repository;

import com.ms.back.Chatting.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
