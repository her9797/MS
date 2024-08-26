package com.ms.back.chatting.repository.Room;

import com.ms.back.chatting.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
