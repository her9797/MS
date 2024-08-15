package com.ms.back.Chatting.repository.JoinedUser;

import com.ms.back.Chatting.entity.JoinedUser;
import com.ms.back.Chatting.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinedUserRepositoryCustom {

    List<Room> findRoomsByUserId(String userId);


}
