package com.ms.back.Chatting.repository;

import com.ms.back.Chatting.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface MsgRepository extends JpaRepository<Message, Integer> {

    @Query(value = "SELECT * FROM message WHERE room_id = :roomId ORDER BY created_at asc", nativeQuery = true)
    List<Message> findMessageByRoomId(@Param("roomId") int roomId);
}
